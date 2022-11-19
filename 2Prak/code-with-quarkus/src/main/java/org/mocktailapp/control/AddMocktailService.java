package org.mocktailapp.control;


import org.boundary.MocktailDTO;
import org.mocktailapp.entity.MocktailKatalog;
import org.mocktailapp.gateway.rdb.MocktailRepository;

public class AddMocktailService {
    MocktailKatalog mocktailKatalog = MocktailRepository.getInstance();


    public void addMocktail(MocktailDTO mocktail){
        mocktailKatalog.addMocktail(mocktail);
    } 
}
