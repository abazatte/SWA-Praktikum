package de.hsos.swa.ab1.applicationLogic;

import de.hsos.swa.ab1.businessLogic.Katalog;
import de.hsos.swa.ab1.businessLogic.Ware;
import de.hsos.swa.ab1.databaseLogic.WarenRepository;
import de.hsos.swa.ab1.warenkorb.WarenkorbFuerSuche;
import de.hsos.swa.ab1.warenkorb.WarenkorbStaender;

public class WarenkorbVerwalten {
    private WarenkorbStaender warenkorbStaender;
    private WarenkorbFuerSuche warenkorbFuerSuche;

    public WarenkorbVerwalten(){
        warenkorbStaender = WarenRepository.getInstance();
        warenkorbFuerSuche = WarenRepository.getInstance();
    }

    public void wareZuWarenkorbHinzufuegen(Ware ware){
        warenkorbFuerSuche.wareHinzufuegen(ware);
    }

    public Ware[] holeWarenkorb(){
        return warenkorbStaender.holeWarenkorb();
    }

    public Ware[] holeWarenkorb(long warenkorbnummer){
        return holeWarenkorb();
    }
}
