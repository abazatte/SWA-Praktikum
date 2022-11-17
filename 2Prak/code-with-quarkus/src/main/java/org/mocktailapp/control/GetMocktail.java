package org.mocktailapp.control;

import java.util.Collection;
import java.util.Optional;

import org.mocktailapp.entity.Mocktail;

public interface GetMocktail {
    Mocktail getMocktail(int id);
    Collection<Mocktail> getMocktails();
    int getMocktailID(String name);
    Optional<Mocktail> findById(int id);
}
