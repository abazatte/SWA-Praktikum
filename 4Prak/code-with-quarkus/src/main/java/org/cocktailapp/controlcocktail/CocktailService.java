package org.cocktailapp.controlcocktail;

import org.boundary.CocktailDTO;
import org.cocktailapp.entitycocktail.CocktailKatalog;
import org.cocktailapp.gatewaycocktail.rdbcocktail.CocktailRepository;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;

import javax.inject.Inject;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

public class CocktailService {
    //hier inject
    CocktailKatalog cocktailKatalog = CocktailRepository.getInstance();
    private AtomicLong testCounter = new AtomicLong(0);

    //add
    @CircuitBreaker(requestVolumeThreshold = 4)
    public CocktailDTO addCocktail(CocktailDTO cocktailDTO){
        maybeFail();
        return cocktailKatalog.addCocktail(cocktailDTO);
    }

    private void maybeFail() {
        // introduce some artificial failures
        final Long invocationNumber = testCounter.getAndIncrement();
        if (invocationNumber % 4 > 1) { // alternate 2 successful and 2 failing invocations
            throw new RuntimeException("Service failed.");
        }
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
