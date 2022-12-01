package org.mannschaftssport.entity;

import org.jboss.logging.Logger;
import org.mannschaftssport.boundary.ACL.PlayerDTO;
import org.mannschaftssport.boundary.ACL.TeamDTO;
import org.mannschaftssport.gateway.repo.TeamRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Team {
    private long id;
    private Person coach;
    private Collection<Person> players = new ArrayList<>();
    private Map<String, String> attributes = new ConcurrentHashMap<>();

    private static final Logger LOG = Logger.getLogger(Team.class);


    public Team(long id, Person coach, Collection<Person> players, Map<String, String> attributes){
        this.id = id;
        this.coach = coach;
        this.players = players;
        this.attributes = attributes;
    }
    public Team(TeamDTO teamDTO){
        this.id = teamDTO.id;
        //this.coach = new Person(team.coach);
        this.players = new ArrayList<>();
        collectionConverter(teamDTO.players);
        attributeConverter(teamDTO.attributes);
    }

    public void collectionConverter(Collection<PlayerDTO> players){
        for (PlayerDTO player: players) {
            this.players.add(new Person(player));
        }
    }

    private void attributeConverter(Map<String,String> attributes){
        try{
            this.attributes.putAll(attributes);
        } catch(RuntimeException r){
            LOG.info("inTeam gestorben");
            LOG.info(r.getMessage());
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getCoach() {
        return coach;
    }

    public void setCoach(Person coach) {
        this.coach = coach;
    }

    public Collection<Person> getPlayers() {
        return players;
    }

    public void setPlayers(Collection<Person> players) {
        this.players = players;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", coach=" + coach +
                ", players=" + players +
                ", attributes=" + attributes +
                '}';
    }
}
