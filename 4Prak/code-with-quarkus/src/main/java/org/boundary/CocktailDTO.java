package org.boundary;

import org.cocktailapp.entitycocktail.Cocktail;

import java.util.List;

public class CocktailDTO {
    public Integer id;
    public String name;
    public String anleitung;
    public List<String> zutatenList;

    public CocktailDTO(Cocktail cocktail){
        this.id = cocktail.getId();
        this.name = cocktail.getName();
        this.anleitung = cocktail.getAnleitung();
        this.zutatenList = cocktail.getZutatenList();
    }

    public CocktailDTO(){

    }
}
