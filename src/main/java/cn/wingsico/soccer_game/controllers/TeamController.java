package cn.wingsico.soccer_game.controllers;

import cn.wingsico.soccer_game.dao.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
  @Autowired
  private TeamRepo teamRepository;


}
