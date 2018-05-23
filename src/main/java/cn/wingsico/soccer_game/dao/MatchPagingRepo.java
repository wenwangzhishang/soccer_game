package cn.wingsico.soccer_game.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface MatchPagingRepo extends PagingAndSortingRepository<Match, Integer> {
}
