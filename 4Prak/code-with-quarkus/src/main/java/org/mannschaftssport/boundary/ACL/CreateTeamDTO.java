package org.mannschaftssport.boundary.ACL;

import java.util.HashMap;
import java.util.Map;

public class CreateTeamDTO {
    Map<String,String> attributes;

    public CreateTeamDTO(){
        attributes = new HashMap<>();
    }
}
