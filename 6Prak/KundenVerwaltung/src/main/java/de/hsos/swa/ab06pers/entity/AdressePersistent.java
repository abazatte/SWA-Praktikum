package de.hsos.swa.ab06pers.entity;

import javax.enterprise.inject.Vetoed;
import javax.persistence.*;

@Entity
@Vetoed
@Table(name = "adressePersistent")
@Cacheable
public class AdressePersistent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kundenNr",unique=true, nullable = false)
    private Long kundenNr;
    private String plz;
    private String ort;
    private String strasse;
    private String hausnr;
    public AdressePersistent(){}

    public AdressePersistent(String plz, String ort, String strasse, String hausnr) {
        this.plz = plz;
        this.ort = ort;
        this.strasse = strasse;
        this.hausnr = hausnr;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnr() {
        return hausnr;
    }

    public void setHausnr(String hausnr) {
        this.hausnr = hausnr;
    }

    public Long getKundenNr() {
        return kundenNr;
    }

    public void setKundenNr(Long kundenNr) {
        this.kundenNr = kundenNr;
    }
}
