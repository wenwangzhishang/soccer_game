package cn.wingsico.soccer_game.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = "players")
@EqualsAndHashCode(exclude = {"players", "register", "name"})
@Entity
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  private Integer type;
  private boolean register = false;

  @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
  private Set<Player> players = new HashSet<>();
}
