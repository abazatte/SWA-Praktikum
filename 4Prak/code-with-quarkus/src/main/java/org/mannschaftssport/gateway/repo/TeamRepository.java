package org.mannschaftssport.gateway.repo;

import org.mannschaftssport.boundary.ACL.ManagerDTO;
import org.mannschaftssport.boundary.ACL.PlayerDTO;
import org.mannschaftssport.boundary.ACL.TeamDTO;
import org.mannschaftssport.entity.Person;
import org.mannschaftssport.entity.Team;
import org.mannschaftssport.entity.TeamCatalog;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class TeamRepository implements TeamCatalog {
    private final Map<Integer, Team> teamMap;
    private static AtomicInteger idCounter;

    public TeamRepository(){
        teamMap = new ConcurrentHashMap<>();
        idCounter = new AtomicInteger();
    }

    private Integer createID(){
        return idCounter.getAndIncrement();
    }

    @Override
    public Collection<TeamDTO> getAllTeams() {
        Collection<TeamDTO> dtos = new ArrayList<>();
        for (Team team: teamMap.values()){
            dtos.add(new TeamDTO(team, null));
        }
        return dtos;
    }

    @Override
    public TeamDTO getTeamByID(long id) {
        return new TeamDTO(this.teamMap.get(id), null);
    }

    @Override
    public TeamDTO createTeam(TeamDTO team) {
        int id = createID();
        team.id = id;
        teamMap.put(id, new Team(team));
        return getTeamByID(id);
    }

    @Override
    public TeamDTO updateTeam(long id, Map<String, String> attribs) {
        this.teamMap.get(id).setAttributes(attribs);
        return new TeamDTO(this.teamMap.get(id), null);
    }

    @Override
    public Boolean deleteTeamByID(long id) {
        try{
            this.teamMap.remove(id);
            return true;
        } catch (NullPointerException e){
            return false;
        }
    }

    @Override
    public ManagerDTO getManagerFromTeam(long teamId) {
        return new ManagerDTO(this.teamMap.get(teamId).getCoach(), null);
    }

    @Override
    public ManagerDTO setManagerToTeam(long teamId, ManagerDTO managerDTO) {
        this.teamMap.get(teamId).setCoach(new Person(managerDTO));
        return new ManagerDTO(this.teamMap.get(teamId).getCoach(), null);
    }

    @Override
    public Collection<PlayerDTO> setPlayersToTeam(Collection<PlayerDTO> players, long id) {
        Collection<Person> playerList = new ArrayList<>();
        for (PlayerDTO player: players) {
            playerList.add(new Person(player));
        }
        this.teamMap.get(id).setPlayers(playerList);
        return players;
    }
}
