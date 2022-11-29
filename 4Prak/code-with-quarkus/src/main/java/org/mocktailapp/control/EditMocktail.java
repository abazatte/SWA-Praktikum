package org.mocktailapp.control;

public interface EditMocktail {
    public void editMocktail(String oldname, String newname,String beschreibung);
    void addZutat(String zutat, int id);
    void deleteZutat(String zutat, int id); 
}
