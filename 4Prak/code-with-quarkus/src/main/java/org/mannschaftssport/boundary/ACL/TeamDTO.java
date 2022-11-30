package org.mannschaftssport.boundary.ACL;

import org.mannschaftssport.entity.Person;
import org.mannschaftssport.entity.Team;

import javax.ws.rs.core.Link;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class TeamDTO {
    public long id;
    public ManagerDTO coach;
    public Collection<PlayerDTO> players;
    public Map<String, Link> links;

    public TeamDTO(){
        players = new ArrayList<>();
    }

    public TeamDTO(long id, ManagerDTO coach, Collection<PlayerDTO> players, Map<String, Link> links){
        this.id = id;
        this.coach = coach;
        this.players = players;
        this.links = links;
    }

    public TeamDTO(Team team, Map<String, Link> links){
        this.id = team.getId();
        this.coach = new ManagerDTO(team.getCoach(), links);
        this.players = new ArrayList<>();
        collectionConverter(team.getPlayers(), links);
        this.links = links;
    }

    public void collectionConverter(Collection<Person> players, Map<String, Link> links){
        for (Person player: players) {
            this.players.add(new PlayerDTO(player, links));
        }
    }
}
