package org.mannschaftssport.boundary.ACL;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AddPersonDTO {

    public int id;
    public Map<String, String> attributes = new ConcurrentHashMap<>();
}
