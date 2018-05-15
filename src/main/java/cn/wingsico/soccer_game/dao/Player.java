package cn.wingsico.soccer_game.dao;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.IOException;

@Data
@ToString(exclude = {"team"})
@EqualsAndHashCode(exclude = {"team"})
@Entity
public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private String sex;

  @ManyToOne(fetch = FetchType.EAGER)
  @JsonSerialize(using = PlayerResponse.class)
  private Team team;
}

final class PlayerResponse extends JsonSerializer<Team>{
  @Override
  public void serialize(Team team, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
    jsonGenerator.writeString(team.getName());
  }
}
