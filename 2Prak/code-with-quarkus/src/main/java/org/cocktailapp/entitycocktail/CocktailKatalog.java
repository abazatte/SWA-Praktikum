package org.cocktailapp.entitycocktail;

import org.boundary.CocktailDTO;

import java.util.Collection;
import java.util.Optional;

public interface CocktailKatalog {
    //add
    CocktailDTO addCocktail(CocktailDTO cocktailDTO);
    //delete
    void deleteCocktail(String name);
    void deleteCocktail(int id);
    //edit
    CocktailDTO editCocktail(String oldname, String newname, String beschreibung);
    CocktailDTO addZutat(String zutat, int id);
    CocktailDTO deleteZutat(String zutat, int id);
    //get
    CocktailDTO getCocktail(int id);
    Collection<CocktailDTO> getCocktails();
    int getCocktailID(String name);
}
