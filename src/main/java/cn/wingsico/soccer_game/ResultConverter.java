package cn.wingsico.soccer_game;

import cn.wingsico.soccer_game.dao.MatchResultMessage.MatchResult;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ResultConverter implements AttributeConverter<MatchResult, Integer> {

  @Override
  public Integer convertToDatabaseColumn(MatchResult attribute) {
    if (attribute == null) {
      return MatchResult.NULL.getValue();
    } else {
      return attribute.getValue();
    }
  }

  @Override
  public MatchResult convertToEntityAttribute(Integer dbData) {
    for (MatchResult result : MatchResult.values()) {
      if (result.getValue().equals(dbData)) {
        return result;
      }
    }

    throw new RuntimeException("Unknown database value: ".concat(dbData.toString()));
  }
}
