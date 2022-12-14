package de.hsos.swa.ab06pers.boundary.acl;

import de.hsos.swa.ab06pers.entity.AdressePersistent;

public class AdressePersistentDTO {
    public String plz;
    public String ort;
    public String strasse;
    public String hausnr;

    public AdressePersistentDTO(){}
    public AdressePersistentDTO(AdressePersistent adresse){
        this.plz = adresse.getPlz();
        this.ort = adresse.getOrt();
        this.strasse = adresse.getStrasse();
        this.hausnr = adresse.getHausnr();
    }
}
