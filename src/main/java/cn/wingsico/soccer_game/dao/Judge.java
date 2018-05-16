package cn.wingsico.soccer_game.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode(exclude = {"name", "register", "type", "sex"})
public class Judge {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private boolean register;

  /**
   * type: 0 assistJudge
   * type: 1 mainJudge
   */
  private Integer type;

  private String sex;
}
