package org.mannschaftssport.entity;

import org.mannschaftssport.boundary.ACL.PlayerDTO;
import org.mannschaftssport.boundary.ACL.SpielerpassDTO;

import java.util.Collection;
import java.util.Map;

public interface PersonCatalog {
    PlayerDTO addPlayer(PlayerDTO playerDTO, SpielerpassDTO spielerpassDTO);
    PlayerDTO addAttributes(int id, Map<String, String> attributes);
    PlayerDTO getPlayerByID(int id);
    Collection<PlayerDTO> getAllPlayer();
}
