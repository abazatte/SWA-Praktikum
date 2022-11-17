package de.hsos.swa.ab1.businessLogic;

import java.math.BigDecimal;

/**
 * <a href="https://www.baeldung.com/spring-persisting-ddd-aggregates"> Quelle </a>
 * aggregate root, hier müssen checks gemacht werden
 *
 * */
public class Ware {
    private long warennummer;
    private String name;
    private BigDecimal preis;
    private String beschreibung;

    public Ware(long warennummer, String name, BigDecimal preis, String beschreibung) {
        setWarennummer(warennummer);
        setName(name);
        setPreis(preis);
        setBeschreibung(beschreibung);
    }

    public long getWarennummer() {
        return warennummer;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setWarennummer(long warennummer) throws IllegalArgumentException{
        if(warennummer < 0){
            throw new IllegalArgumentException("Warennummer darf nicht kleiner als 0 sein");
        } else {
            this.warennummer = warennummer;
        }
    }

    public void setName(String name) throws IllegalArgumentException{
        if(name.isEmpty()){
            throw new IllegalArgumentException("Bitte Namen eingeben");
        } else {
            this.name = name;
        }
    }

    public void setPreis(BigDecimal preis) throws IllegalArgumentException{
        if(preis.doubleValue() < 0.0){
            throw new IllegalArgumentException("Bitte positiven Preis eingeben" + "actual value:" + preis.doubleValue());
        } else {
            this.preis = preis;
        }
    }

    public void setBeschreibung(String beschreibung) throws IllegalArgumentException{
        if(beschreibung.isEmpty()){
            throw new IllegalArgumentException("Bitte Namen eingeben");
        } else {
            this.beschreibung = beschreibung;
        }
    }

    @Override
    public String toString() {
        return name + "{" +
                "warennummer=" + warennummer +
                ", preis=" + preis +
                ", beschreibung='" + beschreibung + '\'' +
                '}';
    }
}
