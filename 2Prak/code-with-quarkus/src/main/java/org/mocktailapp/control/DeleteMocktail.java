package org.mocktailapp.control;

public interface DeleteMocktail {
    void deleteMocktail(String name);
    void deleteMocktail(int id); 
    void deleteZutat(String zutat, int id);
}
