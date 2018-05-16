package cn.wingsico.soccer_game.services;

import cn.wingsico.soccer_game.dao.Judge;

import java.util.List;

public interface JudgeService {
  List<Judge> findAll();
  void register();
}
