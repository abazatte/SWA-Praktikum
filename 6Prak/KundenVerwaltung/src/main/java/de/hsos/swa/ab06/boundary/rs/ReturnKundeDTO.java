package de.hsos.swa.ab06.boundary.rs;

import de.hsos.swa.ab06.entity.Adresse;
import de.hsos.swa.ab06.entity.Kunde;

public class ReturnKundeDTO {
    public Long kundenNummer;
    public String vorname;
    public String nachname;
    public AdresseDTO adresse;
    public ReturnKundeDTO(){
        adresse = new AdresseDTO();
    }

    public ReturnKundeDTO(Kunde kunde) {
        this.kundenNummer = kunde.getKundenNummer();
        this.vorname = kunde.getVorname();
        this.nachname = kunde.getNachname();
        this.adresse = new AdresseDTO(kunde.getAdresse());
    }
}
