package cn.wingsico.soccer_game.dao;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Player {
  private Integer id;
  private String name;

}
