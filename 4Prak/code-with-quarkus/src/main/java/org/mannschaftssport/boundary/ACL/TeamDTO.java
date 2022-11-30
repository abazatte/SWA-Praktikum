package org.mannschaftssport.boundary.ACL;

import org.mannschaftssport.entity.Team;

import javax.ws.rs.core.Link;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeamDTO {
    public long id;
    public ManagerDTO coach;
    public List<PlayerDTO> players;
    public Map<String, Link> links;

    public TeamDTO(){
        players = new ArrayList<>();
    }

    public TeamDTO(long id, ManagerDTO coach, List<PlayerDTO> players, Map<String, Link> links){
        this.id = id;
        this.coach = coach;
        this.players = players;
        this.links = links;
    }

    public TeamDTO(Team team, Map<String, Link> links){
        this.id = team.getId();
        this.coach = team.getCoach();
        this.players = team.getPlayers();
        this.links = links;
    }
}
