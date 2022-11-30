package org.mannschaftssport.boundary.ACL;

import org.mannschaftssport.entity.Person;

import javax.json.bind.annotation.JsonbProperty;
import javax.ws.rs.core.Link;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ManagerDTO {
    public long id;
    public String type;
    public Map<String, String> attributes;
    @JsonbProperty("links")
    public Map<String, Link> links;

    public ManagerDTO(){
        this.attributes = new ConcurrentHashMap<>();
        this.links = new ConcurrentHashMap<>();
    }

    public ManagerDTO(long id, String type, Map<String, String> attributes, Map<String, Link> links){
        this.id = id;
        this.type = type;
        this.attributes = attributes;
        this.links = links;
    }

    public ManagerDTO(Person manager, Map<String, Link> links){
        this.id = manager.getId();
        this.type = manager.getType();
        this.attributes = manager.getAttributes();
        this.links = links;
    }
    public void addLink(String name, Link link) {
        this.links.put(name, link);
    }
}
