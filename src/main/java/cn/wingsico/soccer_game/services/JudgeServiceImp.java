package cn.wingsico.soccer_game.services;

import cn.wingsico.soccer_game.dao.Judge;
import cn.wingsico.soccer_game.dao.JudgeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgeServiceImp implements JudgeService {
  private final JudgeRepo judgeRepo;
  private List<Judge> judges;

  @Autowired
  JudgeServiceImp(JudgeRepo judgeRepo) {
    this.judgeRepo = judgeRepo;
    this.judges = judgeRepo.findAll();
  }

  @Override
  public List<Judge> findAll() {
    if (judges.size() == 0) {
      judges = judgeRepo.findAll();
    }
    return judges;
  }

  @Override
  public void register () {
    if (judges.size() == 0) {
      judges = judgeRepo.findAll();
    }
    for(Judge judge:judges) {
      judge.setRegister(true);
      judgeRepo.save(judge);
    }
  }
}
