package cn.wingsico.soccer_game.dao;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
  private String sex;

  @ManyToOne
  private Team team;
}
