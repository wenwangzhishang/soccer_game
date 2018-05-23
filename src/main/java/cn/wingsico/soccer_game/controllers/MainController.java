package cn.wingsico.soccer_game.controllers;

import cn.wingsico.soccer_game.Utils;
import cn.wingsico.soccer_game.dao.*;
import cn.wingsico.soccer_game.services.JudgeService;
import cn.wingsico.soccer_game.services.PitchService;
import cn.wingsico.soccer_game.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
  private final PlayerRepo playerRepo;
//  private final TeamRepo teamRepo;
//  private final JudgeRepo judgeRepo;
//  private final PitchRepo pitchRepo;

  private JudgeService judgeService;
  private TeamService teamService;
  private PitchService pitchService;

  @Autowired
  MainController(PlayerRepo playerRepo, /* TeamRepo teamRepo, JudgeRepo judgeRepo, PitchRepo pitchRepo, */ JudgeService judgeService, PitchService pitchService, TeamService teamService) {
//    this.judgeRepo = judgeRepo;
//    this.pitchRepo = pitchRepo;
//    this.teamRepo = teamRepo;
    this.playerRepo = playerRepo;
    this.judgeService = judgeService;
    this.pitchService = pitchService;
    this.teamService = teamService;
  }

  /**
   * 查询所有球队
   * @return List<Team>
   */
  @GetMapping(value = "/teams")
  public List<Team> getTeamList() {
    return teamService.findAll();
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
    return pitchService.findAll();
  }

  /**
   * 查询所有裁判
   * @return List<Judge>
   */
  @GetMapping(value = "/judges")
  public List<Judge> getJudgeList() {
    return judgeService.findAll();
  }

  /**
   * 登记所有裁判
   * @return void
   */
  @PutMapping(value = "/judges")
  public ResMessage registerJudges() {
    judgeService.register();
    return Utils.potResMessage(1, "judges: ok");
  }

  /**
   * 登记所有队伍
   * @return void
   */
  @PutMapping(value = "/teams")
  public ResMessage registerTeams() {
    teamService.register();
    return Utils.potResMessage(1, "teams: ok");
  }

  /**
   * 登记所有场地
   * @return void
   */
  @PutMapping(value = "/pitches")
  public ResMessage registerPitches() {
    pitchService.register();
    return Utils.potResMessage(1, "pitches: ok");
  }


}
