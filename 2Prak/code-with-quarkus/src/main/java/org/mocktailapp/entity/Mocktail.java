package org.mocktailapp.entity;


import java.util.ArrayList;
import java.util.List;

public class Mocktail {
    private Integer id;
    private String name;
    private List<String> zutatenList;
    private String anleitung;

    public Mocktail(){
        
    }

    public Mocktail(String name, String anleitung){
        this.id = 0;
        this.name = name;
        zutatenList = new ArrayList<>();
        this.anleitung = anleitung;
    }

    public Mocktail(String name, String anleitung, List<String> zutatenList, Integer id){
        this.id = id;
        this.name = name;
        this.zutatenList = zutatenList;
        this.anleitung = anleitung;
    }

    public Mocktail(String name, String anleitung, List<String> zutatenList){
        this.id = 0;
        this.name = name;
        this.zutatenList = zutatenList;
        this.anleitung = anleitung;
    }
    public void addZutaten(String zutat){
        zutatenList.add(zutat);
    }
    public String getName(){
        return name;
    }
    public String getAnleitung(){
        return anleitung;
    }
    public void setName(String name){
        this.name = name;
    }
    //settet die anleitungs
    public void setAnleitung(String anleitung){
        this.anleitung = anleitung;
        
    }
    public void removeZutat(String name){
       for(int i = 0; i < zutatenList.size(); i++){
            if(zutatenList.get(i).equals(name)){
                zutatenList.remove(i);
            }
       }   
    }

    public void setZutatenList(List<String> zutatenList){
        this.zutatenList = zutatenList;
    }

    public List<String> getZutatenList(){
        return zutatenList;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }
}
