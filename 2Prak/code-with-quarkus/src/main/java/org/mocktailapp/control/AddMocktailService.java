package org.mocktailapp.control;

import org.mocktailapp.entity.MocktailKatalog;
import org.mocktailapp.gateway.rdb.MocktailRepository;
import org.mocktailapp.entity.Mocktail;

public class AddMocktailService {
    MocktailKatalog mocktailKatalog = MocktailRepository.getInstance();


    public void addMocktail(Mocktail mocktail){
        mocktailKatalog.addMocktail(mocktail);
    } 
}
