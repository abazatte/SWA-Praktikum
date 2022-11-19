package org.cocktailapp.controlcocktail;

import org.boundary.CocktailDTO;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;

public class CocktailNutzerIn implements CocktailInterface{
    CocktailService cocktailService = new CocktailService();

    @Override
    public CocktailDTO addCocktail(CocktailDTO cocktailDTO) {
        return this.cocktailService.addCocktail(cocktailDTO);
    }

    @Override
    public void deleteCocktail(String name) {
        this.cocktailService.deleteCocktail(name);
    }

    @Override
    public void deleteCocktail(int id) {
        this.cocktailService.deleteCocktail(id);
    }

    @Override
    public CocktailDTO editCocktail(String oldname, String newname, String beschreibung) {
        return this.cocktailService.editCocktail(oldname, newname, beschreibung);
    }

    @Override
    public CocktailDTO addZutat(String zutat, int id) {
        return this.cocktailService.addZutat(zutat, id);
    }

    @Override
    public CocktailDTO deleteZutat(String zutat, int id) {
        return this.cocktailService.deleteZutat(zutat, id);
    }

    @Override
    public CocktailDTO getCocktail(int id) {
        return this.cocktailService.getCocktail(id);
    }

    @Override
    public Collection<CocktailDTO> getCocktails() {
        return this.cocktailService.getCocktails();
    }

    @Override
    public int getCocktailID(String name) {
        return this.cocktailService.getCocktailID(name);
    }

    @Override
    public Optional<CocktailDTO> findById(int id) {
        return Optional.ofNullable(this.getCocktail(id));
    }
}
