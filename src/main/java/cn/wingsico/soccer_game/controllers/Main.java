package cn.wingsico.soccer_game.controllers;

import cn.wingsico.soccer_game.Utils;
import cn.wingsico.soccer_game.dao.*;
import cn.wingsico.soccer_game.services.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Main {
  private final PlayerRepo playerRepo;
  private final TeamRepo teamRepo;
  private final JudgeRepo judgeRepo;
  private final PitchRepo pitchRepo;

  private JudgeService judgeService;

  @Autowired
  Main(PlayerRepo playerRepo, TeamRepo teamRepo, JudgeRepo judgeRepo, PitchRepo pitchRepo, JudgeService judgeService) {
    this.judgeRepo = judgeRepo;
    this.pitchRepo = pitchRepo;
    this.teamRepo = teamRepo;
    this.playerRepo = playerRepo;
    this.judgeService = judgeService;
  }

  /**
   * 查询所有球队
   * @return List<Team>
   */
  @GetMapping(value = "/teams")
  public List<Team> getTeamList() {
    return teamRepo.findAll();
  }

  /**
   * 查询所有球员
   * @return List<Player>
   */
  @GetMapping(value = "/players")
  public List<Player> getPlayerList() {
    return playerRepo.findAll();
  }

  /**
   * 查询所有场地
   * @return List<Pitch>
   */
  @GetMapping(value = "/pitches")
  public List<Pitch> getPitchList() {
    return pitchRepo.findAll();
  }

  /**
   * 查询所有裁判
   * @return List<Judge>
   */
  @GetMapping(value = "/judges")
  public List<Judge> getJudgeList() {
    return judgeService.findAll();
  }

  @PutMapping(value = "/judges")
  public ResMessage registerJudges() {
    judgeService.register();
    return Utils.potResMessage(1, "ok");
  }


}
