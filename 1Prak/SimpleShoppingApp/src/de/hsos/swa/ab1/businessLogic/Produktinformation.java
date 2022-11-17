package de.hsos.swa.ab1.businessLogic;

public class Produktinformation {
    private long warennummer;
    private String bezeichnung;
    private Object information;

    public Produktinformation(long warennummer,String bezeichnung, Object information) {
        setWarennummer(warennummer);
        setBezeichnung(bezeichnung);
        setInformation(information);
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        if(bezeichnung.length() > 255){
            throw new IllegalArgumentException("Die Bezeichnung darf nicht länger als 255 Buchstaben sein!");
        }else{
            this.bezeichnung = bezeichnung;
        }
    }

    public Object getInformation() {
        return information;
    }

    public void setInformation(Object information) {
        this.information = information;
    }

    public long getWarennummer() {
        return warennummer;
    }

    public void setWarennummer(long warennummer) throws IllegalArgumentException{
        if(warennummer < 0){
            throw new IllegalArgumentException("Warennummer darf nicht kleiner als 0 sein");
        } else {
            this.warennummer = warennummer;
        }
    }

    @Override
    public String toString() {
        return "Produktinformation{" +
                "warennummer=" + warennummer +
                ", bezeichnung='" + bezeichnung + '\'' +
                ", information=" + information +
                '}';
    }
}
