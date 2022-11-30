package org.mannschaftssport.boundary.ACL;

import org.mannschaftssport.entity.Person;

import javax.ws.rs.core.Link;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerDTO {
    public long id;
    public String type;
    public Map<String, String> attributes;
    public Map<String, Link> links;

    public PlayerDTO(){
        this.attributes = new ConcurrentHashMap<>();
        this.links = new ConcurrentHashMap<>();
    }

    public PlayerDTO(long id, String type, Map<String, String> attributes, Map<String, Link> links){
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
