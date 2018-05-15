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

@Component
@Order(value = 1)
public class FakeMockGenerator implements CommandLineRunner {
  private final PlayerRepo playerRepo;
  private final TeamRepo teamRepo;
  private final JudgeRepo judgeRepo;
  private final PitchRepo pitchRepo;
  private final Logger logger;
  private ArrayList<Team> teams = new ArrayList<>();
  private ArrayList<Player> players = new ArrayList<>();
  private ArrayList<Pitch> pitches = new ArrayList<>();

  @Autowired
  FakeMockGenerator(PlayerRepo playerRepo, TeamRepo teamRepo, JudgeRepo judgeRepo, PitchRepo pitchRepo) {
    this.playerRepo = playerRepo;
    this.teamRepo = teamRepo;
    this.logger = LoggerFactory.getLogger(getClass());
    this.judgeRepo = judgeRepo;
    this.pitchRepo = pitchRepo;
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
    for (int i = 0; i < 18; ++i) {
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
}
