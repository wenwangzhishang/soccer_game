package cn.wingsico.soccer_game.services;

import cn.wingsico.soccer_game.dao.Team;

import java.util.List;

public interface TeamService {
  List<Team> findAll();
  void register();
}
