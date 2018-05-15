package cn.wingsico.soccer_game;

import cn.wingsico.soccer_game.dao.Player;
import cn.wingsico.soccer_game.dao.PlayerRepo;
import cn.wingsico.soccer_game.dao.Team;
import cn.wingsico.soccer_game.dao.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Component
public class FakeMockGenerator {
  private final PlayerRepo playerRepo;
  private final TeamRepo teamRepo;
  private final Logger logger;
  private ArrayList<Team> teams = new ArrayList<>();
  private ArrayList<Player> players = new ArrayList<Player>();

  @Autowired
  FakeMockGenerator(PlayerRepo playerRepo, TeamRepo teamRepo) {
    this.playerRepo = playerRepo;
    this.teamRepo = teamRepo;
    this.logger = LoggerFactory.getLogger(getClass());
  }

  @Transactional
  public void run() {
    logger.info("正在初始化mock数据...");

    boolean isNeedToGenerate = teamRepo.count() == 0;

    if (!isNeedToGenerate) {
      logger.info("初始化完成");
      return;
    }

    logger.info("正在初始化数据库...");

    generateTeam();

    generatePlayer();

    logger.info("数据库初始化完成");
  }

  @Transactional
  protected void generateTeam() {
    for (int i = 0; i < 72; ++i) {
      Team team = new Team();
      team.setName(Utils.getRandomTeamName());
      team.setId(i);
      team.setType(i / 12 + 1);

      teams.add(team);
      teamRepo.save(team);
    }
  }

  @Transactional
  protected  void generatePlayer() {
    for (int i = 0; i < 72; ++i) {
      Team team = teams.get(i);
      for (int j = 0; j < 11; ++j) {
        Player player = new Player();
        String sex = (i / 12 + 1) == 2 ? "f" : "m";

        player.setId((i+1) * (j+1));
        player.setSex(sex);
        player.setName(Utils.getRandomName(sex));
        team.getPlayers().add(player);

        players.add(player);

        playerRepo.save(player);
      }
    }
  }
}
