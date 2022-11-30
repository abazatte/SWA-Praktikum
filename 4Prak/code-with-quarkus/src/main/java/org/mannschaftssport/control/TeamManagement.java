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
    public TeamDTO getTeamByID(long id) {
        return teamService.getTeamByID(id);
    }

    @Override
    public TeamDTO createTeam(TeamDTO team) {
        return teamService.createTeam(team);
    }

    @Override
    public TeamDTO updateTeam(long id, Map<String, String> attribs) {
        return teamService.updateTeam(id, attribs);
    }

    @Override
    public Boolean deleteTeamByID(long id) {
        return teamService.deleteTeamByID(id);
    }

    @Override
    public ManagerDTO getManagerFromTeam(long teamId) {
        return teamService.getManagerFromTeam(teamId);
    }

    @Override
    public ManagerDTO setManagerToTeam(long teamId, ManagerDTO managerDTO) {
        return teamService.setManagerToTeam(teamId, managerDTO);
    }

    @Override
    public Collection<PlayerDTO> setPlayersToTeam(Collection<PlayerDTO> players, long id) {
        return teamService.setPlayersToTeam(players, id);
    }
}
