package cn.wingsico.soccer_game.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepo extends JpaRepository<Team, Integer> {
  List<Team> findTeamByType(Integer type);
}
