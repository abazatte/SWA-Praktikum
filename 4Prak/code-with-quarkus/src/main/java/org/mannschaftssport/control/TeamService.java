package org.mannschaftssport.control;

import org.mannschaftssport.boundary.ACL.ManagerDTO;
import org.mannschaftssport.boundary.ACL.PlayerDTO;
import org.mannschaftssport.boundary.ACL.TeamDTO;
import org.mannschaftssport.entity.Team;
import org.mannschaftssport.entity.TeamCatalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Map;

@ApplicationScoped
public class TeamService {
    @Inject
    TeamCatalog teamRepository;

    public Collection<TeamDTO> getAllTeams() {
        return teamRepository.getAllTeams();
    }

    public TeamDTO getTeamByID(long id) {
        return teamRepository.getTeamByID(id);
    }

    public TeamDTO createTeam(TeamDTO team) {
        return teamRepository.createTeam(team);
    }

    public TeamDTO updateTeam(long id, Map<String, String> attribs) {
        return teamRepository.updateTeam(id,attribs);
    }

    public Boolean deleteTeamByID(long id) {
        return teamRepository.deleteTeamByID(id);
    }


    public ManagerDTO getManagerFromTeam(long teamId) {
        return teamRepository.getManagerFromTeam(teamId);
    }

    public ManagerDTO setManagerToTeam(long teamId, ManagerDTO managerDTO) {
        return teamRepository.setManagerToTeam(teamId,managerDTO);
    }

    public Collection<PlayerDTO> setPlayersToTeam(Collection<PlayerDTO> players, long id) {
        return teamRepository.setPlayersToTeam(players,id);
    }
}
