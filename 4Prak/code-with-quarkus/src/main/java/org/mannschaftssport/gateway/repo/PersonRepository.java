package org.mannschaftssport.gateway.repo;

import org.mannschaftssport.boundary.ACL.PlayerDTO;
import org.mannschaftssport.boundary.ACL.SpielerpassDTO;
import org.mannschaftssport.entity.Person;
import org.mannschaftssport.entity.PersonCatalog;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class PersonRepository implements PersonCatalog {
    private final Map<Integer, Person> personMap;

    public PersonRepository(){
        personMap = new ConcurrentHashMap<>();
    }


    @Override
    public PlayerDTO addPlayer(PlayerDTO playerDTO, SpielerpassDTO spielerpassDTO) {
        this.personMap.put((int) spielerpassDTO.id, new Person(playerDTO));
        return this.getPlayerByID(spielerpassDTO.id);
    }

    @Override
    public PlayerDTO addAttributes(int id, Map<String, String> attributes) {
        this.personMap.get(id).setAttributes(attributes);
        return this.getPlayerByID(id);
    }

    @Override
    public PlayerDTO getPlayerByID(int id) {
        return new PlayerDTO(this.personMap.get(id));
    }

    @Override
    public Collection<PlayerDTO> getAllPlayer() {
        Collection<PlayerDTO> dtos = new ArrayList<>();
        for (Person player: personMap.values()) {
            dtos.add(new PlayerDTO(player));
        }
        return dtos;
    }
}
