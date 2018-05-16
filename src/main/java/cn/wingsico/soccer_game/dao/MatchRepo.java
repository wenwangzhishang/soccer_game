package cn.wingsico.soccer_game.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepo extends JpaRepository<Match, Integer> {
}
