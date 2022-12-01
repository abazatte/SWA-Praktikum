package org.mannschaftssport.gateway.repo;

import org.jboss.logging.Logger;
import org.mannschaftssport.boundary.ACL.ManagerDTO;
import org.mannschaftssport.boundary.ACL.PlayerDTO;
import org.mannschaftssport.boundary.ACL.TeamDTO;
import org.mannschaftssport.boundary.rest.TeamResource;
import org.mannschaftssport.entity.Person;
import org.mannschaftssport.entity.Team;
import org.mannschaftssport.entity.TeamCatalog;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class TeamRepository implements TeamCatalog {
    private final Map<Integer, Team> teamMap;
    private static AtomicInteger idCounter;
    private static final Logger LOG = Logger.getLogger(TeamRepository.class);


    public TeamRepository(){
        teamMap = new HashMap<>();
        idCounter = new AtomicInteger();
    }

    private Integer createID(){
        return idCounter.getAndIncrement();
    }

    @Override
    public Collection<TeamDTO> getAllTeams() {
        Collection<TeamDTO> dtos = new ArrayList<>();
        for (Team team: teamMap.values()){
            dtos.add(new TeamDTO(team));
        }
        return dtos;
    }

    @Override
    public TeamDTO getTeamByID(int id) {
       // LOG.info(id +"teamDTO vom neuem team1 :" );
        Team team = this.teamMap.get(id);
        //LOG.info("supeadf team  " + team);
        //LOG.info(teamDTO);
        return new TeamDTO(team);
    }

    @Override
    public TeamDTO createTeam(TeamDTO teamDTO) {
        int id = createID();
        teamDTO.id = id;
        LOG.info("teamdto ID "+teamDTO.id);
        Team newTeam = new Team(teamDTO);
        teamMap.put(id, newTeam);
        return getTeamByID(id);
    }

    @Override
    public TeamDTO updateTeam(int id, Map<String, String> attribs) {

        this.teamMap.get(id).setAttributes(attribs);
        return new TeamDTO(this.teamMap.get(id));
    }

    @Override
    public Boolean deleteTeamByID(int id) {
        try{
            this.teamMap.remove(id);
            return true;
        } catch (NullPointerException e){
            return false;
        }
    }

    @Override
    public ManagerDTO getManagerFromTeam(int teamId) {
        return new ManagerDTO(this.teamMap.get(teamId).getCoach());
    }

    @Override
    public ManagerDTO setManagerToTeam(int teamId, ManagerDTO managerDTO) {
        this.teamMap.get(teamId).setCoach(new Person(managerDTO));
        return new ManagerDTO(this.teamMap.get(teamId).getCoach());
    }

    @Override
    public Collection<PlayerDTO> setPlayersToTeam(Collection<PlayerDTO> players, int id) {
        Collection<Person> playerList = new ArrayList<>();
        for (PlayerDTO player: players) {
            playerList.add(new Person(player));
        }
        this.teamMap.get(id).setPlayers(playerList);
        return players;
    }
}
