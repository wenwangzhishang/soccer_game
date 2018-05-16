package cn.wingsico.soccer_game.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepo extends JpaRepository<Player, Integer> {
  List<Player> findPlayersByTeam(Team team);
}
