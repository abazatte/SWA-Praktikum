package de.hsos.swa.ab06.boundary.rs;

import de.hsos.swa.ab06.entity.Adresse;

public class AdresseDTO {
    public String plz;
    public String ort;
    public String strasse;
    public String hausnr;

    public AdresseDTO(){}
    public AdresseDTO(Adresse adresse){
        this.plz = adresse.getPlz();
        this.ort = adresse.getOrt();
        this.strasse = adresse.getStrasse();
        this.hausnr = adresse.getHausnr();
    }
}
