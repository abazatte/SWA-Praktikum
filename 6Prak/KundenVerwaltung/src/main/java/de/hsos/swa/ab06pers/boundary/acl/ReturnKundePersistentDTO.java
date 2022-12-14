package de.hsos.swa.ab06pers.boundary.acl;

import de.hsos.swa.ab06.boundary.rs.AdresseDTO;
import de.hsos.swa.ab06.entity.Kunde;

public class ReturnKundePersistentDTO {
    public Long kundenNummer;
    public String vorname;
    public String nachname;
    public AdresseDTO adresse;
    public ReturnKundePersistentDTO(){
        adresse = new AdresseDTO();
    }

    public ReturnKundePersistentDTO(Kunde kunde) {
        this.kundenNummer = kunde.getKundenNummer();
        this.vorname = kunde.getVorname();
        this.nachname = kunde.getNachname();
        this.adresse = new AdresseDTO(kunde.getAdresse());
    }
}
