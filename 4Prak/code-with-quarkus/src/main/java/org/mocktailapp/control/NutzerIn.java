package org.mocktailapp.control;

import java.util.Collection;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import org.boundary.MocktailDTO;
import org.mocktailapp.control.*;

@ApplicationScoped
public class NutzerIn implements AddMocktail, DeleteMocktail, EditMocktail, GetMocktail {
    AddMocktailService addMocktailService = new AddMocktailService();
    DeleteMocktailService deleteMocktailService = new DeleteMocktailService();
    EditMocktailService editMocktailService = new EditMocktailService();
    GetMocktailService getMocktailService = new GetMocktailService(); 

    @Override
    public Optional<MocktailDTO> findById(int id){
        return Optional.ofNullable(this.getMocktail(id));
    }

    @Override
    public MocktailDTO getMocktail(int id) {
        return this.getMocktailService.getMocktail(id);
    }

    @Override
    public Collection<MocktailDTO> getMocktails() {
        return this.getMocktailService.getMocktails();
    }

    @Override
    public int getMocktailID(String name) {
        return this.getMocktailService.getMocktailID(name);
    }

    @Override
    public void editMocktail(String oldname, String newname, String beschreibung) {
        editMocktailService.editMocktail(oldname, newname, beschreibung);
    }

    @Override
    public void addZutat(String zutat, int id) {
        editMocktailService.addZutat(zutat, id);
    }

    @Override
    public void deleteMocktail(String name) {
        deleteMocktailService.deleteMocktail(name);
    }

    @Override
    public void deleteMocktail(int id) {
        deleteMocktailService.deleteMocktail(id);
    }

    @Override
    public void deleteZutat(String zutat, int id) {
        editMocktailService.deleteZutat(zutat, id);
    }

    @Override
    public void addMocktail(MocktailDTO mocktail) {
        addMocktailService.addMocktail(mocktail);
    }
}
