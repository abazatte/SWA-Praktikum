package org.mocktailapp.entity;

import org.boundary.MocktailDTO;

import java.util.Collection;

public interface MocktailKatalog {
    void addMocktail(MocktailDTO mocktail);
    void editMocktail(String oldname, String newName, String beschreibung);
    void addZutat(String zutat, int id);
    void deleteZutat(String zutat, int id);
    void deleteMocktail(String name);
    void deleteMocktail(int id);
    MocktailDTO getMocktail(int id);
    int getMocktailID(String name);
    Collection<MocktailDTO> getMocktails();
}
