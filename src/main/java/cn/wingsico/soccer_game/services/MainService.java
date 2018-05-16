package cn.wingsico.soccer_game.services;

import cn.wingsico.soccer_game.Utils;
import cn.wingsico.soccer_game.dao.Judge;
import cn.wingsico.soccer_game.dao.JudgeRepo;
import cn.wingsico.soccer_game.dao.ResMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
  @Autowired
  private JudgeRepo judgeRepo;

//  public ResMessage getJudgePotMessage() {
//    Integer status = 1;
//    String message = "ok";
//    try {
//      List<Judge> judges = judgeRepo.findAll();
//      System.out.println(judges);
//      return Utils.potResMessage(status, message, judges);
//    } catch (Exception e) {
//      status = 0;
//      message = "fail to get judges: " + e.getMessage();
//      return Utils.potResMessage(status, message);
//    }
//  }
}
