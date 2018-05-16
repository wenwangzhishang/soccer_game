package cn.wingsico.soccer_game.dao;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "Matches")
public class Match {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne
  private Team team1;

  @OneToOne
  private Team team2;

  private Date holdDate;

  @OneToOne(cascade = {CascadeType.MERGE})
  private MatchResultMessage matchResultMessage;

  @OneToOne
  private Pitch pitch;

}
