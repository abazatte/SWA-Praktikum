package org.mannschaftssport.control;

import org.mannschaftssport.boundary.ACL.ManagerDTO;
import org.mannschaftssport.boundary.ACL.PlayerDTO;
import org.mannschaftssport.boundary.ACL.TeamDTO;

import java.util.Collection;
import java.util.Map;

public interface TeamInterface {
    Collection<TeamDTO> getAllTeams();
    TeamDTO getTeamByID(long id);
    TeamDTO createTeam(TeamDTO team);
    TeamDTO updateTeam(long id, Map<String,String> attribs);
    Boolean deleteTeamByID(long id);
    ManagerDTO getManagerFromTeam(long teamId);
    ManagerDTO setManagerToTeam(long teamId, ManagerDTO managerDTO);
    Collection<PlayerDTO> setPlayersToTeam(Collection<PlayerDTO> players, long id);


}
