package cn.wingsico.soccer_game.dao;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class ShotRank {
  private Integer rank;

  private String playerName;

  private String teamName;

  private Integer shotCount;
}
