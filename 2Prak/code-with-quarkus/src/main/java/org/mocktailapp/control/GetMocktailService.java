package org.mocktailapp.control;

import org.mocktailapp.entity.MocktailKatalog;
import org.mocktailapp.gateway.rdb.MocktailRepository;
import org.mocktailapp.entity.Mocktail;
import java.util.Collection;

public class GetMocktailService {
    MocktailKatalog mocktailKatalog = MocktailRepository.getInstance();
    
    public Mocktail getMocktail(int id) {
       return mocktailKatalog.getMocktail(id);
    }
    public int getMocktailID(String name){
        return mocktailKatalog.getMocktailID(name);
    }
    public Collection<Mocktail> getMocktails(){
        return mocktailKatalog.getMocktails();
    }
}
