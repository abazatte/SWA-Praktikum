package org.mannschaftssport.control;

import org.mannschaftssport.boundary.ACL.ManagerDTO;
import org.mannschaftssport.boundary.ACL.PlayerDTO;
import org.mannschaftssport.boundary.ACL.TeamDTO;

import java.util.Collection;
import java.util.Map;

public interface TeamInterface {
    Collection<TeamDTO> getAllTeams();
    TeamDTO getTeamByID(int id);
    TeamDTO createTeam(TeamDTO team);
    TeamDTO updateTeam(int id, Map<String,String> attribs);
    Boolean deleteTeamByID(int id);
    ManagerDTO getManagerFromTeam(int teamId);
    ManagerDTO setManagerToTeam(int teamId, ManagerDTO managerDTO);
    Collection<PlayerDTO> setPlayersToTeam(Collection<PlayerDTO> players, int id);


}
