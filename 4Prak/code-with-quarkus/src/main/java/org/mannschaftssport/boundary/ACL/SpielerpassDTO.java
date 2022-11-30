package org.mannschaftssport.boundary.ACL;

import javax.ws.rs.core.Link;

public class SpielerpassDTO {
    public int id;
    public String name;

    public SpielerpassDTO(){

    }

    public SpielerpassDTO(int id, String name){
        this.id = id;
        this.name = name;
    }
}
