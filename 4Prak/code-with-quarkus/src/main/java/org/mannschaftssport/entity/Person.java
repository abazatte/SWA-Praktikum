package org.mannschaftssport.entity;

import org.mannschaftssport.boundary.ACL.ManagerDTO;
import org.mannschaftssport.boundary.ACL.PlayerDTO;

import java.util.Map;

public class Person {
    private int id; //fdsaf
    private Map<String, String> attributes;

    public Person(int id, Map<String, String> attributes){
        this.id = id;
        this.attributes = attributes;
    }

    public Person(ManagerDTO manager){
        this.id = manager.id;
        this.attributes = manager.attributes;
    }

    public Person(PlayerDTO player){
        this.id = player.id;
        this.attributes = player.attributes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
