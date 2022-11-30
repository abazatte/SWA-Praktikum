package org.mannschaftssport.gateway.repo;

import org.mannschaftssport.boundary.ACL.PlayerDTO;
import org.mannschaftssport.boundary.ACL.SpielerpassDTO;
import org.mannschaftssport.entity.PersonCatalog;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;

@ApplicationScoped
public class PersonRepository implements PersonCatalog {
    @Override
    public PlayerDTO addPlayer(PlayerDTO playerDTO, SpielerpassDTO spielerpassDTO) {
        return null;
    }

    @Override
    public PlayerDTO addAttributes(long id, Map<String, String> attributes) {
        return null;
    }
}
