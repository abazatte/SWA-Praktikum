package de.hsos.swa.ab1.businessLogic;

public interface Katalog {
    public void legeSuchalgorithmusFest(SuchAlgorithmus algorithmus);
    public Ware[] suchen(String warenname);
    public Ware suchen(Long warenNummer);
    public Produktinformation gebeProduktinformationen(Ware ware);
}
