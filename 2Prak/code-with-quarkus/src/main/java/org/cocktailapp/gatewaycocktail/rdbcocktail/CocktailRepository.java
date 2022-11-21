package org.cocktailapp.gatewaycocktail.rdbcocktail;

import org.boundary.CocktailDTO;
import org.cocktailapp.entitycocktail.Cocktail;
import org.cocktailapp.entitycocktail.CocktailKatalog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CocktailRepository implements CocktailKatalog {
    private final Map<Integer, Cocktail> cocktailMap;
    private static AtomicInteger idCounter;
    private static CocktailRepository dbConnInstance = null;

    public static CocktailRepository getInstance() {
        if (dbConnInstance == null) {
            dbConnInstance = new CocktailRepository();
        }
        return dbConnInstance;
    }

    //wenn man das hier public macht, würde dann injection funktionieren
    private CocktailRepository(){
        cocktailMap = new ConcurrentHashMap<>();
        idCounter = new AtomicInteger();
    }

    private Integer createID(){
        return idCounter.getAndIncrement();
    }


    @Override
    public CocktailDTO addCocktail(CocktailDTO cocktailDTO) {
        int id = createID();
        cocktailDTO.id = id;
        cocktailMap.put(id, new Cocktail(cocktailDTO));
        return getCocktail(id);
    }

    @Override
    public void deleteCocktail(String name) {
        int id = getCocktailID(name);
        deleteCocktail(id);
    }

    @Override
    public void deleteCocktail(int id) {
        this.cocktailMap.remove(id);
    }

    @Override
    public CocktailDTO editCocktail(String oldname, String newname, String beschreibung) {
        Integer idVonOld = -1;
        for (var entry : cocktailMap.entrySet()) {
            if (entry.getValue().getName().equals(oldname)){
                idVonOld = entry.getValue().getId();
            }
        }
        CocktailDTO oldCocktail = getCocktail(idVonOld);
        this.deleteCocktail(idVonOld);
        oldCocktail.name = newname;
        oldCocktail.anleitung = beschreibung;
        Cocktail newCocktail = new Cocktail(oldCocktail);
        this.cocktailMap.put(idVonOld, newCocktail);
        return new CocktailDTO(newCocktail);
    }

    @Override
    public CocktailDTO addZutat(String zutat, int id) {
        this.cocktailMap.get(id).addZutaten(zutat);
        return this.getCocktail(id);
    }

    @Override
    public CocktailDTO deleteZutat(String zutat, int id) {
        this.cocktailMap.get(id).removeZutat(zutat);
        return this.getCocktail(id);
    }

    @Override
    public CocktailDTO getCocktail(int id) {
        return new CocktailDTO(this.cocktailMap.get(id));
    }

    @Override
    public Collection<CocktailDTO> getCocktails() {
        Collection<CocktailDTO> dtos = new ArrayList<>();
        for (Cocktail cocktail: cocktailMap.values()) {
            dtos.add(new CocktailDTO(cocktail));
        }
        return dtos;
    }

    @Override
    public int getCocktailID(String name) {
        int idName = 0;
        for (var entry : cocktailMap.entrySet()){
            if (entry.getValue().getName().equals(name)){
                idName = entry.getValue().getId();
            }
        }
        return idName;
    }
}
