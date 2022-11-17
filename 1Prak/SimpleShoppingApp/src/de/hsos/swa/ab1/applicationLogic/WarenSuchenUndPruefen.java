package de.hsos.swa.ab1.applicationLogic;

import de.hsos.swa.ab1.businessLogic.Katalog;
import de.hsos.swa.ab1.businessLogic.Produktinformation;
import de.hsos.swa.ab1.businessLogic.SuchAlgorithmus;
import de.hsos.swa.ab1.businessLogic.Ware;
import de.hsos.swa.ab1.databaseLogic.WarenRepository;

public class WarenSuchenUndPruefen {
    Katalog katalog;
    public WarenSuchenUndPruefen(){
        katalog = WarenRepository.getInstance();
    }
    public Ware[] sucheWare(String name){
        return katalog.suchen(name);
    }
    public Ware sucheWare(Long warennummer){
        return katalog.suchen(warennummer);
    }

    public Produktinformation holeDetailinformation(Ware ware){ return katalog.gebeProduktinformationen(ware);};

}
