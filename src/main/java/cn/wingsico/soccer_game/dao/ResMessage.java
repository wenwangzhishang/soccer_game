package cn.wingsico.soccer_game.dao;

import lombok.Data;

@Data
public class ResMessage {
  private Integer status;
  private String message;
  private Object data;
}
