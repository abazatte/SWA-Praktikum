package org.mannschaftssport.control;

import org.mannschaftssport.boundary.ACL.ManagerDTO;
import org.mannschaftssport.boundary.ACL.PlayerDTO;
import org.mannschaftssport.boundary.ACL.TeamDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Map;

@ApplicationScoped
public class TeamManagement implements TeamInterface{

    @Inject
    TeamService teamService;


    @Override
    public Collection<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }

    @Override
    public TeamDTO getTeamByID(int id) {
        return teamService.getTeamByID(id);
    }

    @Override
    public TeamDTO createTeam(TeamDTO team) {
        return teamService.createTeam(team);
    }

    @Override
    public TeamDTO updateTeam(int id, Map<String, String> attribs) {
        return teamService.updateTeam(id, attribs);
    }

    @Override
    public Boolean deleteTeamByID(int id) {
        return teamService.deleteTeamByID(id);
    }

    @Override
    public ManagerDTO getManagerFromTeam(int teamId) {
        return teamService.getManagerFromTeam(teamId);
    }

    @Override
    public ManagerDTO setManagerToTeam(int teamId, ManagerDTO managerDTO) {
        return teamService.setManagerToTeam(teamId, managerDTO);
    }

    @Override
    public Collection<PlayerDTO> setPlayersToTeam(Collection<PlayerDTO> players, int id) {
        return teamService.setPlayersToTeam(players, id);
    }
}
