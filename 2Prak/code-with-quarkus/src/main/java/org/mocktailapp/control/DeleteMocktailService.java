package org.mocktailapp.control;


import org.mocktailapp.entity.MocktailKatalog;
import org.mocktailapp.gateway.rdb.MocktailRepository;

public class DeleteMocktailService {
    MocktailKatalog mocktailKatalog = MocktailRepository.getInstance();

    public void deleteZutat(String zutat, int id){
        mocktailKatalog.deleteZutat(zutat, id);
    }
    public void deleteMocktail(String name){
        mocktailKatalog.deleteMocktail(name);
    }
    public void deleteMocktail(int id){
        mocktailKatalog.deleteMocktail(id);
    }
}
