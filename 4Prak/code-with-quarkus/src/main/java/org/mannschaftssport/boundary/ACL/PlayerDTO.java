package org.mannschaftssport.boundary.ACL;

import org.mannschaftssport.entity.Person;

import javax.json.bind.annotation.JsonbProperty;
import javax.ws.rs.core.Link;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerDTO {
    public int id;
    public Map<String, String> attributes = new ConcurrentHashMap<>();
    @JsonbProperty("links")
    public Map<String, Link> links = new ConcurrentHashMap<>();


    public PlayerDTO(){
        this.attributes = new ConcurrentHashMap<>();
        this.links = new ConcurrentHashMap<>();
    }
    public PlayerDTO(PlayerRelationshipDTO playerRelationshipDTO){
        this.id = playerRelationshipDTO.id;
        attributeConverter(playerRelationshipDTO.attributes);
    }

    public PlayerDTO(int id, Map<String, String> attributes){
        this.id = id;
        this.attributes = attributes;
    }

    public PlayerDTO(Person player){
        this.id = player.getId();
        this.attributes = player.getAttributes();
    }
    private void attributeConverter(Map<String,String> attributes){
        try{
            this.attributes.putAll(attributes);
        } catch(RuntimeException r){
        }
    }
    public void addLink(String name, Link link) {
        this.links.put(name, link);
    }
}
