package org.cocktailapp.controlcocktail;

import org.boundary.CocktailDTO;
import org.cocktailapp.entitycocktail.CocktailKatalog;
import org.cocktailapp.gatewaycocktail.rdbcocktail.CocktailRepository;

import javax.inject.Inject;
import java.util.Collection;

public class CocktailService {
    CocktailKatalog cocktailKatalog = CocktailRepository.getInstance();

    //add
    public CocktailDTO addCocktail(CocktailDTO cocktailDTO){
        return cocktailKatalog.addCocktail(cocktailDTO);
    }

    //edit
    public CocktailDTO editCocktail(String oldname, String newname, String beschreibung){
        return cocktailKatalog.editCocktail(oldname, newname, beschreibung);
    }

    public CocktailDTO addZutat(String zutat, int id){
        return cocktailKatalog.addZutat(zutat, id);
    }

    public CocktailDTO deleteZutat (String zutat, int id){
        return cocktailKatalog.deleteZutat(zutat, id);
    }

    //get
    public CocktailDTO getCocktail(int id){
        return cocktailKatalog.getCocktail(id);
    }

    public int getCocktailID(String name){
        return cocktailKatalog.getCocktailID(name);
    }

    public Collection<CocktailDTO> getCocktails(){
        return cocktailKatalog.getCocktails();
    }

    //delete
    public void deleteCocktail(String name) {
        cocktailKatalog.deleteCocktail(name);
    }

    public void deleteCocktail(int id){
        cocktailKatalog.deleteCocktail(id);
    }
}
