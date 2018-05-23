package cn.wingsico.soccer_game.dao;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.io.IOException;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "Matches")
public class Match {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne
  @JsonSerialize(using = TeamSerializer.class)
  private Team team1;

  @OneToOne
  @JsonSerialize(using = TeamSerializer.class)
  private Team team2;

  private Integer holdDay;

  /**
   * type 比赛类型、分为单循环赛 0 和淘汰赛 1
   */
  private Integer type;

  @OneToOne(cascade = {CascadeType.MERGE})
  @JsonSerialize(using = MatchResultMessageSerializer.class)
  private MatchResultMessage matchResultMessage;

  @OneToOne
  private Pitch pitch;

  static final class TeamSerializer extends JsonSerializer<Team> {
    @Override
    public void serialize(Team team, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
      Map<String, Object> potTeam = new HashMap<>();
      potTeam.put("team_name", team.getName());
      potTeam.put("team_type", team.getId());

      jsonGenerator.writeObject(potTeam);
    }
  }

  static final class MatchResultMessageSerializer extends JsonSerializer<MatchResultMessage> {
    @Override
    public void serialize(MatchResultMessage matchResultMessage, JsonGenerator jsonGenerator, SerializerProvider provider) throws  IOException {
      List<Map> shotResults = matchResultMessage.getShotResults().stream().map(shotResult -> {
        Map<String, Object> res = new HashMap<>();
        Player player = shotResult.getPlayer();
        res.put("team", player.getTeam().getName());
        res.put("shotCount", shotResult.getShotCount());
        res.put("duration", shotResult.getMatchResultMessage().getDuration());
        // 一些操作
        return res;
      }).collect(Collectors.toList());

      jsonGenerator.writeObject(shotResults);

    }
  }
}
