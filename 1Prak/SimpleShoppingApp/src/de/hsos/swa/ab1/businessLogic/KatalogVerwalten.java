package de.hsos.swa.ab1.businessLogic;

import java.sql.SQLException;

public interface KatalogVerwalten {

    public void addArtikel(Ware ware, Produktinformation produktinformation);
    public void removeArtikel(int warennummer);
    public void updateArtikel(int warennummerAlt, Ware wareNeu);
    public void dropAll();
}
