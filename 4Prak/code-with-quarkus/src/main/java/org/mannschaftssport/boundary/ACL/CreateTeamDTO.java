package org.mannschaftssport.boundary.ACL;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CreateTeamDTO {
    public Map<String,String> attributes = new ConcurrentHashMap<>();



    @Override
    public String toString() {
        return "CreateTeamDTO{" +
                "attributes=" + attributes +
                '}';
    }
}
