package de.hsos.swa.ab06pers.entity;

import de.hsos.swa.ab06.entity.Adresse;
import io.smallrye.common.constraint.NotNull;

import javax.enterprise.inject.Vetoed;
import javax.persistence.*;

@Entity
@Vetoed
@Table(name = "KundePersistent")
@NamedQuery(name = "KundePersistent.findAll", query = "SELECT a FROM KundePersistent a ORDER BY a.kundenNummer", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class KundePersistent {
    @Id
    @Column(name = "kundenNr")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long kundenNummer;
    @Column
    private String vorname;
    @Column
    private String nachname;

    @OneToOne(cascade = CascadeType.MERGE)
    @MapsId
    private AdressePersistent adresse;

    public KundePersistent(){
        this.adresse = new AdressePersistent();
    }
    public KundePersistent(Long kundenNummer, String vorname, String nachname, AdressePersistent adresse) {
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

    public AdressePersistent getAdresse() {
        return adresse;
    }

    public void setAdresse(AdressePersistent adresse) {
        this.adresse = adresse;
    }

}
