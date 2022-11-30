package org.mannschaftssport.control;

import org.mannschaftssport.boundary.ACL.PlayerDTO;
import org.mannschaftssport.boundary.ACL.SpielerpassDTO;
import org.mannschaftssport.entity.PersonCatalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Map;

@ApplicationScoped
public class PersonService{
    @Inject
    PersonCatalog personRepository;

    public PlayerDTO addPlayer(PlayerDTO playerDTO, SpielerpassDTO spielerpassDTO) {
        return personRepository.addPlayer(playerDTO, spielerpassDTO);
    }

    public PlayerDTO addAttributes(int id, Map<String, String> attributes) {
        return personRepository.addAttributes(id, attributes);
    }

    public PlayerDTO getPlayerByID(int id) {
        return personRepository.getPlayerByID(id);
    }

    public Collection<PlayerDTO> getAllPlayer() {
        return personRepository.getAllPlayer();
    }
}
