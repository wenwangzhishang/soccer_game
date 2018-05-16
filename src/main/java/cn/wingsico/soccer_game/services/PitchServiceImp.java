package cn.wingsico.soccer_game.services;

import cn.wingsico.soccer_game.dao.PitchRepo;
import cn.wingsico.soccer_game.dao.Pitch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PitchServiceImp implements PitchService{
  private final PitchRepo pitchRepo;
  private List<Pitch> pitches;

  @Autowired
  PitchServiceImp(PitchRepo pitchRepo) {
    this.pitchRepo = pitchRepo;
    this.pitches = pitchRepo.findAll();
  }

  @Override
  public List<Pitch> findAll() {
    if (pitches.size() == 0) {
      pitches = pitchRepo.findAll();
    }
    return pitches;
  }

  @Override
  public void register () {
    if (pitches.size() == 0) {
      pitches = pitchRepo.findAll();
    }
    for(Pitch pitch:pitches) {
      pitch.setRegister(true);
      pitchRepo.save(pitch);
    }
  }
}
