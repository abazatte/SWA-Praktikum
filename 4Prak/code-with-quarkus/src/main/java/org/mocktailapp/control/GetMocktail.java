package org.mocktailapp.control;

import java.util.Collection;
import java.util.Optional;

import org.boundary.MocktailDTO;

public interface GetMocktail {
    MocktailDTO getMocktail(int id);
    Collection<MocktailDTO> getMocktails();
    int getMocktailID(String name);
    Optional<MocktailDTO> findById(int id);
}
