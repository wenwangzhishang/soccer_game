package cn.wingsico.soccer_game.controllers;

import cn.wingsico.soccer_game.dao.Match;
import cn.wingsico.soccer_game.dao.MatchPagingRepo;
import cn.wingsico.soccer_game.dao.MatchRepo;
import cn.wingsico.soccer_game.dao.MatchResultMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MatchController {

  private final MatchRepo matchRepo;
  private final MatchResultMessageRepo matchResultMessageRepo;
  private final MatchPagingRepo matchPagingRepo;


  @Autowired
  MatchController(MatchRepo matchRepo, MatchResultMessageRepo matchResultMessageRepo, MatchPagingRepo matchPagingRepo) {
    this.matchRepo = matchRepo;
    this.matchResultMessageRepo = matchResultMessageRepo;
    this.matchPagingRepo = matchPagingRepo;
  }

  @GetMapping(value = "/matches/{day}")
  public Map getCycleMatchesByDay(@PathVariable Integer day) {
    Integer matchCountOfDay = 24;
    Map<String, Object> res = new HashMap<>();

    Pageable pageable = new PageRequest(day - 1 , matchCountOfDay ,Sort.Direction.ASC, "holdDay");
    Page<Match> rs =  matchPagingRepo.findAll(pageable);

    res.put("currentDay", day);
    res.put("totalDay", rs.getTotalPages());
    res.put("data", rs.getContent());

    return res;
  }
}
