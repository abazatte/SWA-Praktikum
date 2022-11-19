package org.cocktailapp.entitycocktail;

import org.boundary.CocktailDTO;

import java.util.List;

public class Cocktail {
    private Integer id;
    private String name;
    private String anleitung;
    private List<String> zutatenList;
    public Cocktail(CocktailDTO cocktailDTO){
        this.id = cocktailDTO.id;
        this.name = cocktailDTO.name;
        this.anleitung = cocktailDTO.anleitung;
        this.zutatenList = cocktailDTO.zutatenList;
    }

    public void addZutaten(String zutat) {
        zutatenList.add(zutat);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnleitung() {
        return anleitung;
    }

    public void setAnleitung(String anleitung) {
        this.anleitung = anleitung;
    }

    public List<String> getZutatenList() {
        return zutatenList;
    }

    public void setZutatenList(List<String> zutatenList) {
        this.zutatenList = zutatenList;
    }

    public void removeZutat(String name){
        for(int i = 0; i < zutatenList.size(); i++){
            if(zutatenList.get(i).equals(name)){
                zutatenList.remove(i);
            }
        }
    }
}
