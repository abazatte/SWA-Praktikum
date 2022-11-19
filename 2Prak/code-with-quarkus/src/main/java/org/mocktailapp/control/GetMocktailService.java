package org.mocktailapp.control;

import org.boundary.MocktailDTO;
import org.mocktailapp.entity.MocktailKatalog;
import org.mocktailapp.gateway.rdb.MocktailRepository;

import java.util.Collection;

public class GetMocktailService {
    MocktailKatalog mocktailKatalog = MocktailRepository.getInstance();
    
    public MocktailDTO getMocktail(int id) {
       return mocktailKatalog.getMocktail(id);
    }
    public int getMocktailID(String name){
        return mocktailKatalog.getMocktailID(name);
    }
    public Collection<MocktailDTO> getMocktails(){
        return mocktailKatalog.getMocktails();
    }
}
