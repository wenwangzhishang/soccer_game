package cn.wingsico.soccer_game.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
public class Pitch {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @OneToOne
  private Judge judge;

  @OneToOne
  private Judge assistJudge1;

  @OneToOne
  private Judge assistJudge2;
}
