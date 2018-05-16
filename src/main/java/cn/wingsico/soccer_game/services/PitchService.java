package cn.wingsico.soccer_game.services;

import cn.wingsico.soccer_game.dao.Pitch;

import java.util.List;

public interface PitchService {
  List<Pitch> findAll();
  void register();
}
