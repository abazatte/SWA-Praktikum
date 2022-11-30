package org.mannschaftssport.control;

import org.mannschaftssport.boundary.ACL.PlayerDTO;
import org.mannschaftssport.boundary.ACL.SpielerpassDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;

@ApplicationScoped
public class PersonManagement implements PersonInterface{
    @Override
    public PlayerDTO addPlayer(PlayerDTO playerDTO, SpielerpassDTO spielerpassDTO) {
        return null;
    }

    @Override
    public PlayerDTO addAttributes(long id, Map<String, String> attributes) {
        return null;
    }
}
