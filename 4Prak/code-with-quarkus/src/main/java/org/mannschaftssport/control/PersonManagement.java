package org.mannschaftssport.control;

import org.mannschaftssport.boundary.ACL.PlayerDTO;
import org.mannschaftssport.boundary.ACL.SpielerpassDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Map;

@ApplicationScoped
public class PersonManagement implements PersonInterface{
    @Inject
    PersonService personService;


    @Override
    public PlayerDTO addPlayer(PlayerDTO playerDTO, SpielerpassDTO spielerpassDTO) {
        return personService.addPlayer(playerDTO,spielerpassDTO);
    }

    @Override
    public PlayerDTO addAttributes(long id, Map<String, String> attributes) {
        return personService.addAttributes(id,attributes);
    }

    @Override
    public PlayerDTO getPlayerByID(long id) {
        return personService.getPlayerByID(id);
    }

    @Override
    public Collection<PlayerDTO> getAllPlayer() {
        return personService.getAllPlayer();
    }
}
