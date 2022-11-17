package de.hsos.swa.ab1.warenkorb;

import de.hsos.swa.ab1.businessLogic.Ware;

public interface WarenkorbStaender {
    public Ware[] holeWarenkorb();
    public Ware[] holeWarenkorb(long warenkorbnummer);
}
