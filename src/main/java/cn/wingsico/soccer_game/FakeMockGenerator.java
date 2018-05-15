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
  public void run() throws Exception {
    logger.info("正在初始化mock数据...");

    boolean isNeedToGenerate = teamRepo.count() == 0;

    if (!isNeedToGenerate) {
      logger.info("初始化完成");
      return;
    }

    logger.info("正在初始化数据库...");



  }

  @Transactional
  protected void generateTeam() {
    for (int i = 0; i < 72; ++i) {
      Team team = new Team();

    }
  }
}
