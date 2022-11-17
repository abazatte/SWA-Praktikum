package org.mocktailapp.gateway.rdb;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;

import org.mocktailapp.entity.Mocktail;
import org.mocktailapp.entity.MocktailKatalog;

@ApplicationScoped
public class MocktailRepository implements MocktailKatalog{
    private final Map<Integer, Mocktail> mocktailMap = new ConcurrentHashMap<>();
    private static AtomicInteger idCounter = new AtomicInteger();
    private static MocktailRepository dbConnInstance = null;
    
    public static MocktailRepository getInstance() {
        if (dbConnInstance == null) {
            dbConnInstance = new MocktailRepository();
        }
        return dbConnInstance;
    }

    private Integer createID(){
        return idCounter.getAndIncrement();
    }

    @Override
    public void addMocktail(Mocktail mocktail) {
        // TODO Auto-generated method stub
        Integer id = createID();
        mocktailMap.put(id,new Mocktail(mocktail.getName(), mocktail.getAnleitung(), mocktail.getZutatenList(), id));
    }

    @Override
    public void editMocktail(String oldname, String newName, String beschreibung) {
        // TODO Auto-generated method stub
        Integer idVonOld = -1;
        
        for (var entry : mocktailMap.entrySet()) {
            if(entry.getValue().getName().equals(oldname)){
                idVonOld = entry.getValue().getId();
            }
        }
        Mocktail oldMocktail = getMocktail(idVonOld);
        Mocktail newMocktail = new Mocktail(newName,beschreibung,oldMocktail.getZutatenList(),idVonOld);
        this.deleteMocktail(idVonOld);
        mocktailMap.put(idVonOld, newMocktail);
    }

    @Override
    public void addZutat(String zutat, int id) {
        getMocktail(id).addZutaten(zutat);
        
    }

    @Override
    public void deleteZutat(String zutat, int id) {
        getMocktail(id).removeZutat(zutat);
    }

    @Override
    public void deleteMocktail(String name) {
        int id = getMocktailID(name);
        deleteMocktail(id);
        
    }

    @Override
    public void deleteMocktail(int id) {
        this.mocktailMap.remove(id);
    }

    @Override
    public Mocktail getMocktail(int id) {
        return mocktailMap.get(id);
    }

    @Override
    public int getMocktailID(String name) {

        //Iteriert durch die Map und holt sich die ID wo der Name matcht
        int idName = 0;
        for (var entry : mocktailMap.entrySet()) {
            if(entry.getValue().getName().equals(name)){
                idName = entry.getValue().getId();
            }
        }
        return idName;
    }

    @Override
    public Collection<Mocktail> getMocktails() {
        return mocktailMap.values();
    }
}
