package org.mannschaftssport.boundary.ACL;

import org.mannschaftssport.entity.Person;

import javax.json.bind.annotation.JsonbProperty;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Link;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ManagerDTO {
    public int id;
    public Map<String, String> attributes = new ConcurrentHashMap<>();;
    @JsonbProperty("links")
    public Map<String, Link> links = new ConcurrentHashMap<>();;

    public ManagerDTO(){}

    public ManagerDTO(int id, Map<String, String> attributes){
        this.id = id;
        this.attributes = attributes;
    }

    public ManagerDTO(Person manager){
        this.id = manager.getId();
        this.attributes = manager.getAttributes();
    }

    public ManagerDTO(PUTManagerDTO wahnsinn){
        this.id = wahnsinn.id;
        this.attributes = wahnsinn.attributes;
    }
    public void addLink(String name, Link link) {
        this.links.put(name, link);
    }
}
