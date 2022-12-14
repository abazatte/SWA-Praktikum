package de.hsos.swa.ab06pers.boundary.acl;

import de.hsos.swa.ab06pers.entity.KundePersistent;

public class ReturnKundePersistentDTO {
    public Long kundenNummer;
    public String vorname;
    public String nachname;
    public AdressePersistentDTO adresse;
    public ReturnKundePersistentDTO(){
        adresse = new AdressePersistentDTO();
    }

    public ReturnKundePersistentDTO(KundePersistent kunde) {
        this.kundenNummer = kunde.getKundenNummer();
        this.vorname = kunde.getVorname();
        this.nachname = kunde.getNachname();
        this.adresse = new AdressePersistentDTO(kunde.getAdresse());
    }
}
