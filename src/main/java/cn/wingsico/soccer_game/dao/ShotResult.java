package cn.wingsico.soccer_game.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = "matchResultMessage")
@EqualsAndHashCode(exclude = "matchResultMessage")
@JsonIgnoreProperties(value = "matchResultMessage")
public class ShotResult {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne
  private Player player;

  private Integer shotCount;

  @ManyToOne
  private MatchResultMessage matchResultMessage;
}
