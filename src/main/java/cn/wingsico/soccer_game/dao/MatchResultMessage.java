package cn.wingsico.soccer_game.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Data;
import cn.wingsico.soccer_game.ResultConverter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@ToString(exclude = "shotResults")
@EqualsAndHashCode(exclude = "shotResults")
public class MatchResultMessage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToMany(mappedBy = "matchResultMessage")
  private Set<ShotResult> shotResults = new HashSet<>();

  @Convert(converter = ResultConverter.class)
  private MatchResult matchResult;

  private Integer duration;

  public enum MatchResult {
    WIN(1, "胜"),
    LOSE(2, "输"),
    DRAW(3, "平"),
    NULL(4, "默认");

    @Getter
    private Integer value;

    @Getter
    private String description;

    MatchResult(Integer value, String description) {
      this.value = value;
      this.description = description;
    }
  }
}
