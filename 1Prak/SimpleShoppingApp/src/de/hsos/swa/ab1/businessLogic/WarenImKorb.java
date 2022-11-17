package de.hsos.swa.ab1.businessLogic;

public class WarenImKorb {
    private int warenkorbNr;
    private long warenNr;

    public WarenImKorb(int warenkorbNr, long warenNr) {
        this.warenkorbNr = warenkorbNr;
        this.warenNr = warenNr;
    }

    public int getWarenkorbNr() {
        return warenkorbNr;
    }

    public void setWarenkorbNr(int warenkorbNr) {
        this.warenkorbNr = warenkorbNr;
    }

    public long getWarenNr() {
        return warenNr;
    }

    public void setWarenNr(long warenNr) {
        this.warenNr = warenNr;
    }
}

