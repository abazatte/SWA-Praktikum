package org.cocktailapp.controlcocktail;

import org.boundary.CocktailDTO;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class CocktailNutzerIn implements CocktailInterface{
    CocktailService cocktailService;
    private AtomicLong testCounter;

    public CocktailNutzerIn(){
        cocktailService = new CocktailService(); //das maybe auch injecten
        testCounter = new AtomicLong(0);
    }

    @CircuitBreaker(requestVolumeThreshold = 4)
    @Override
    public CocktailDTO addCocktail(CocktailDTO cocktailDTO) {
        maybeFail();
        return this.cocktailService.addCocktail(cocktailDTO);
    }
    private void maybeFail() {
        // introduce some artificial failures
        final Long invocationNumber = testCounter.getAndIncrement();
        if (invocationNumber % 4 > 1) { // alternate 2 successful and 2 failing invocations
            throw new RuntimeException("Service failed.");
        }
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
