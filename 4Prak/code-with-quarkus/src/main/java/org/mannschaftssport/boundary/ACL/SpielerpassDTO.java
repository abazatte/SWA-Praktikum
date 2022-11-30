package org.mannschaftssport.boundary.ACL;

import javax.ws.rs.core.Link;

public class SpielerpassDTO {
    public long id;
    public String name;

    public SpielerpassDTO(){

    }

    public SpielerpassDTO(long id, String name){
        this.id = id;
        this.name = name;
    }
}
