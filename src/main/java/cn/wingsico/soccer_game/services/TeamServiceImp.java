package cn.wingsico.soccer_game.services;

import cn.wingsico.soccer_game.dao.TeamRepo;
import cn.wingsico.soccer_game.dao.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImp implements TeamService{
  private final TeamRepo teamRepo;
  private List<Team> teams;

  @Autowired
  TeamServiceImp(TeamRepo teamRepo) {
    this.teamRepo = teamRepo;
    this.teams = teamRepo.findAll();
  }

  @Override
  public List<Team> findAll() {
    if (teams.size() == 0) {
      teams = teamRepo.findAll();
    }
    return teams;
  }

  @Override
  public void register () {
    if (teams.size() == 0) {
      teams = teamRepo.findAll();
    }
    for(Team team:teams) {
      team.setRegister(true);
      teamRepo.save(team);
    }
  }
}

