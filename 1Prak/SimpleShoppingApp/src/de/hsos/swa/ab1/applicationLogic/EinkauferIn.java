package de.hsos.swa.ab1.applicationLogic;

import de.hsos.swa.ab1.businessLogic.Produktinformation;
import de.hsos.swa.ab1.businessLogic.Ware;

public class EinkauferIn implements HoleWarenkorb,SucheWare,PruefeWare,WaehleWare{

    WarenSuchenUndPruefen warenSuchenUndPruefen = new WarenSuchenUndPruefen();
    WarenkorbVerwalten warenkorbVerwalten = new WarenkorbVerwalten();

    @Override
    public Ware[] sucheWare(String warenname) {
        return warenSuchenUndPruefen.sucheWare(warenname);
    }

    @Override
    public Ware sucheWare(long warennummer) {
        return warenSuchenUndPruefen.sucheWare(warennummer);
    }

    @Override
    public Produktinformation holeDetailinformation(Ware ware) {

        return warenSuchenUndPruefen.holeDetailinformation(ware);
    }

    @Override
    public Ware[] holeWarenkorb(long warenkorbnummer) {
        return warenkorbVerwalten.holeWarenkorb(warenkorbnummer);
    }

    @Override
    public void wareZuWarenkorbhinzufuegen(Ware ware) {
        warenkorbVerwalten.wareZuWarenkorbHinzufuegen(ware);
    }
}
