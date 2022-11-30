package org.mannschaftssport.control;

import org.mannschaftssport.boundary.ACL.ManagerDTO;
import org.mannschaftssport.boundary.ACL.PlayerDTO;
import org.mannschaftssport.boundary.ACL.TeamDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.Map;

@ApplicationScoped
public class TeamManagement implements TeamInterface{
    @Override
    public Collection<TeamDTO> getAllTeams() {
        return null;
    }

    @Override
    public TeamDTO getTeamByID(long id) {
        return null;
    }

    @Override
    public TeamDTO createTeam(TeamDTO team) {
        return null;
    }

    @Override
    public TeamDTO updateTeam(long id, Map<String, String> attribs) {
        return null;
    }

    @Override
    public Boolean deleteTeamByID(long id) {
        return null;
    }

    @Override
    public ManagerDTO getManagerFromTeam(long teamId) {
        return null;
    }

    @Override
    public ManagerDTO setManagerToTeam(long teamId, ManagerDTO managerDTO) {
        return null;
    }

    @Override
    public Collection<PlayerDTO> setPlayersToTeam(Collection<PlayerDTO> players, long id) {
        return null;
    }
}
