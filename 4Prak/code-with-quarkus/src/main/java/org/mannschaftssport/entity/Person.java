package org.mannschaftssport.entity;

import org.mannschaftssport.boundary.ACL.ManagerDTO;
import org.mannschaftssport.boundary.ACL.PlayerDTO;

import java.util.Map;

public class Person {
    private long id;
    private String type;
    private Map<String, String> attributes;

    public Person(long id, String type, Map<String, String> attributes){
        this.id = id;
        this.type = type;
        this.attributes = attributes;
    }

    public Person(ManagerDTO manager){
        this.id = manager.id;
        this.type = manager.type;
        this.attributes = manager.attributes;
    }

    public Person(PlayerDTO player){
        this.id = player.id;
        this.type = player.type;
        this.attributes = player.attributes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
