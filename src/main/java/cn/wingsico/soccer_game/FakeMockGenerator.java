package cn.wingsico.soccer_game;

import cn.wingsico.soccer_game.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Component
@Order(value = 1)
public class FakeMockGenerator implements CommandLineRunner {
  private final PlayerRepo playerRepo;
  private final TeamRepo teamRepo;
  private final JudgeRepo judgeRepo;
  private final PitchRepo pitchRepo;
  private final MatchRepo matchRepo;
  private final ShotResultRepo shotResultRepo;
  private final MatchResultMessageRepo matchResultMessageRepo;
  private final Logger logger;
  private ArrayList<Team> teams = new ArrayList<>();
  private ArrayList<Player> players = new ArrayList<>();
  private ArrayList<Pitch> pitches = new ArrayList<>();

  @Autowired
  FakeMockGenerator(PlayerRepo playerRepo, TeamRepo teamRepo, JudgeRepo judgeRepo, PitchRepo pitchRepo, MatchRepo matchRepo, ShotResultRepo shotResultRepo, MatchResultMessageRepo matchResultMessageRepo) {
    this.playerRepo = playerRepo;
    this.teamRepo = teamRepo;
    this.logger = LoggerFactory.getLogger(getClass());
    this.judgeRepo = judgeRepo;
    this.pitchRepo = pitchRepo;
    this.matchRepo = matchRepo;
    this.shotResultRepo = shotResultRepo;
    this.matchResultMessageRepo = matchResultMessageRepo;
  }

  @Transactional
  @Override
  public void run(String ...args) {
    logger.info("正在初始化mock数据...");

    boolean isNeedToGenerate = teamRepo.count() == 0;

    if (!isNeedToGenerate) {
      logger.info("初始化完成");
      return;
    }

    logger.info("正在初始化数据库...");

    generateTeam();

    generatePlayer();

    generatePatch();

    generateMatch();

    logger.info("数据库初始化完成");
  }

  @Transactional
  protected void generateTeam() {
    for (int i = 0; i < 72; ++i) {
      Team team = new Team();
      team.setName(Utils.getRandomTeamName());
      team.setType(i / 18 + 1);

      teams.add(team);
      teamRepo.save(team);
    }
  }

  @Transactional
  protected void generatePlayer() {
    for (int i = 0; i < 72; ++i) {
      Team team = teams.get(i);
      for (int j = 0; j < 11; ++j) {
        Player player = new Player();
        String sex = (i / 18+ 1) == 2 ? "female" : "male";

        player.setSex(sex);
        player.setName(Utils.getRandomName(sex));
        player.setTeam(team);
        team.getPlayers().add(player);

        players.add(player);

        playerRepo.save(player);
      }
    }
  }

  private void generatePatch() {
    for (int i = 0; i < 12; ++i) {
      Pitch pitch = new Pitch();
      pitch.setName("Pitch " + (i + 1));
      pitch.setJudge(generateJudge(1));
      pitch.setAssistJudge1(generateJudge(0));
      pitch.setAssistJudge2(generateJudge(0));

      pitches.add(pitch);

      pitchRepo.save(pitch);
    }
  }

  private Judge generateJudge(Integer type) {
    Judge judge = new Judge();
    String sex = Utils.getRandomSex();
    judge.setType(type);
    judge.setSex(sex);
    judge.setName(Utils.getRandomName(sex));

    judgeRepo.save(judge);
    return judge;
  }

  private ArrayList<Pitch> getDoublePitches() {
    ArrayList<Pitch> arrayList = new ArrayList<>(pitches);
    arrayList.addAll(pitches);

    return arrayList;
  }

  private void generateMatch() {
    Random random = new Random();
    Integer matchType = 0;
    Integer holdDay = 1;
    Integer pitchRemaining = 24;

    ArrayList<Pitch> doublePitches = getDoublePitches();

    for (int i = 1; i <= 4; ++i) {
      ArrayList<Team> teams = new ArrayList<>(teamRepo.findTeamByType(i));

      for (int j = 0; j < teams.size(); ++j) {
        for (int k = j + 1; k < teams.size(); ++k) {
          if (pitchRemaining == 0) {
            pitchRemaining = 24;
            doublePitches = getDoublePitches();
            holdDay++;
          }

          Team team1 = teams.get(j);
          Team team2 = teams.get(k);

          Pitch pitch = doublePitches.get(random.nextInt(pitchRemaining));
          doublePitches.remove(pitch);
          pitchRemaining--;

          Match match = new Match();
          match.setPitch(pitch);
          match.setTeam1(team1);
          match.setTeam2(team2);
          match.setHoldDay(holdDay);
          match.setType(matchType);

          MatchResultMessage matchResult = generateMatchResult(match);
          match.setMatchResultMessage(matchResult);

          matchRepo.save(match);
        }
      }
    }
  }

  private MatchResultMessage generateMatchResult(Match match) {
    MatchResultMessage matchResultMessage = new MatchResultMessage();
    matchResultMessageRepo.save(matchResultMessage);

    Team team1 = match.getTeam1();
    Team team2 = match.getTeam2();

    Integer totalCount1 = generateShotResult(team1, matchResultMessage);
    Integer totalCount2 = generateShotResult(team2, matchResultMessage);

    if (totalCount1 > totalCount2) {
      matchResultMessage.setMatchResult(MatchResultMessage.MatchResult.WIN);
    } else if (totalCount1.equals(totalCount2)) {
      matchResultMessage.setMatchResult(MatchResultMessage.MatchResult.DRAW);
    } else {
      matchResultMessage.setMatchResult(MatchResultMessage.MatchResult.LOSE);
    }

    matchResultMessage.setDuration(Utils.getRandomDuration());
    matchResultMessageRepo.save(matchResultMessage);

    return matchResultMessage;
  }

  private Integer generateShotResult(Team team, MatchResultMessage matchResultMessage) {
    List<Player> players = playerRepo.findPlayersByTeam(team);
    Integer totalCount = 0;

    for (Player player:players) {
      ShotResult shotResult = new ShotResult();
      shotResult.setPlayer(player);
      shotResult.setMatchResultMessage(matchResultMessage);

      Integer count = Utils.getRandomShotCount();
      totalCount += count;

      shotResult.setShotCount(count);
      matchResultMessage.getShotResults().add(shotResult);

      shotResultRepo.save(shotResult);
    }

    return totalCount;
  }

}
