package de.hsos.swa.ab06.entity;

import javax.enterprise.context.Dependent;

@Dependent
public class Kunde {
    private Long kundenNummer;
    private String vorname;
    private String nachname;
    private Adresse adresse;

    public Kunde(){
        this.adresse = new Adresse();
    }
    public Kunde(Long kundenNummer, String vorname, String nachname, Adresse adresse) {
        this.kundenNummer = kundenNummer;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
    }

    public Long getKundenNummer() {
        return kundenNummer;
    }

    public void setKundenNummer(Long kundenNummer) {
        this.kundenNummer = kundenNummer;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
