package cn.wingsico.soccer_game.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface MatchRepo extends JpaRepository<Match, Integer> {
  List<Match> findMatchesByHoldDay(Integer holdDay);
}
