package org.mocktailapp.control;

import org.mocktailapp.entity.MocktailKatalog;
import org.mocktailapp.gateway.rdb.MocktailRepository;

public class EditMocktailService {
    MocktailKatalog mocktailKatalog = MocktailRepository.getInstance();

    public void editMocktail(String oldname, String newname,String beschreibung){
        mocktailKatalog.editMocktail(oldname, newname, beschreibung);
    }
    void addZutat(String zutat, int id){
        mocktailKatalog.addZutat(zutat, id);
    }
    void deleteZutat(String zutat, int id){
        mocktailKatalog.deleteZutat(zutat, id);
    }
}
