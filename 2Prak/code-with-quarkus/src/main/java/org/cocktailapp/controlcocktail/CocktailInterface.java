package org.cocktailapp.controlcocktail;

import java.util.Collection;
import java.util.Optional;

import org.boundary.CocktailDTO;

public interface CocktailInterface {
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
    Optional<CocktailDTO> findById(int id);
}
