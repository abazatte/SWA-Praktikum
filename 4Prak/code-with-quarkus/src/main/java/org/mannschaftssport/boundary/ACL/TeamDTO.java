package org.mannschaftssport.boundary.ACL;

import io.quarkus.logging.Log;
import org.jboss.logging.Logger;
import org.mannschaftssport.entity.Person;
import org.mannschaftssport.entity.Team;
import org.mannschaftssport.gateway.repo.TeamRepository;

import javax.json.bind.annotation.JsonbProperty;
import javax.ws.rs.core.Link;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TeamDTO {
    public long id;
    public ManagerDTO coach = new ManagerDTO();
    public Collection<PlayerDTO> players = new ArrayList<>();
    @JsonbProperty("links")
    public Map<String, Link> links = new HashMap<>();
    public Map<String, String> attributes = new ConcurrentHashMap<>();

    private static final Logger LOG = Logger.getLogger(TeamDTO.class);


    public TeamDTO(){
    }

    public TeamDTO(long id, ManagerDTO coach, Collection<PlayerDTO> players, Map<String, String> attributes){
        this.id = id;
        this.coach = coach;
        this.players = players;
        this.attributes = attributes;
    }

    public TeamDTO(CreateTeamDTO createTeamDTO){
        this.attributes = createTeamDTO.attributes;
        this.players = new ArrayList<>();
    }
    public TeamDTO(int id, Map<String,String> attributes){

    }

    public TeamDTO(Team team){
        //LOG.info(team.getId() + "hi teamdto");
        this.id = team.getId();
        if(team.getCoach() != null){
            this.coach = new ManagerDTO(team.getCoach());
        }
        if(!team.getPlayers().isEmpty()){
            collectionConverter(team.getPlayers());
        }
        attributeConverter(team.getAttributes());
    }

    public TeamDTO(long id, Map<String,String> attributes){
        this.id = id;
        this.coach = new ManagerDTO();
        this.attributes = attributes;
    }

    private void collectionConverter(Collection<Person> players){
        for (Person player: players) {
            this.players.add(new PlayerDTO(player));
        }
    }

    private void attributeConverter(Map<String,String> attributes){
        try{
            this.attributes.putAll(attributes);
        } catch(RuntimeException r){
            LOG.info("in Teamdto gestorben");
            LOG.info(r.getMessage());
        }
    }

    public void addLink(String name, Link link) {
        this.links.put(name, link);
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
                "id=" + id +
                ", coach=" + coach +
                ", players=" + players +
                ", links=" + links +
                ", attributes=" + attributes +
                '}';
    }
}
