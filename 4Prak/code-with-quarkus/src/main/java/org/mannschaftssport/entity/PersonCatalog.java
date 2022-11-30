package org.mannschaftssport.entity;

import org.mannschaftssport.boundary.ACL.PlayerDTO;
import org.mannschaftssport.boundary.ACL.SpielerpassDTO;

import java.util.Map;

public interface PersonCatalog {
    PlayerDTO addPlayer(PlayerDTO playerDTO, SpielerpassDTO spielerpassDTO);
    PlayerDTO addAttributes(long id, Map<String, String> attributes);
}
