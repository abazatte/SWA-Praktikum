package de.hsos.swa.ab1.applicationLogic;

import de.hsos.swa.ab1.businessLogic.Ware;

public interface SucheWare {
    public Ware[] sucheWare(String warenname);
    public Ware sucheWare(long warennummer);
}
