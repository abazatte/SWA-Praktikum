package org.mocktailapp.entity;

import java.util.Collection;

public interface MocktailKatalog {
    void addMocktail(Mocktail mocktail);
    void editMocktail(String oldname, String newName, String beschreibung);
    void addZutat(String zutat, int id);
    void deleteZutat(String zutat, int id);
    void deleteMocktail(String name);
    void deleteMocktail(int id);
    Mocktail getMocktail(int id);
    int getMocktailID(String name);
    Collection<Mocktail> getMocktails();
}
