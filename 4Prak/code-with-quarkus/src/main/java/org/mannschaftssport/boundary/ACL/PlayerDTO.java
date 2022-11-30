package org.mannschaftssport.boundary.ACL;

import org.mannschaftssport.entity.Person;

import javax.json.bind.annotation.JsonbProperty;
import javax.ws.rs.core.Link;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerDTO {
    public int id;
    public String type;
    public Map<String, String> attributes;
    @JsonbProperty("links")
    public Map<String, Link> links;

    public PlayerDTO(){
        this.attributes = new ConcurrentHashMap<>();
        this.links = new ConcurrentHashMap<>();
    }

    public PlayerDTO(int id, String type, Map<String, String> attributes, Map<String, Link> links){
        this.id = id;
        this.type = type;
        this.attributes = attributes;
        this.links = links;
    }

    public PlayerDTO(Person player, Map<String, Link> links){
        this.id = player.getId();
        this.type = player.getType();
        this.attributes = player.getAttributes();
        this.links = links;
    }
    public void addLink(String name, Link link) {
        this.links.put(name, link);
    }
}
