package de.hsos.swa.ab1.warenkorb;

public class Warenkorb {
    int warenkorbNr;
    String user;

    public Warenkorb(int warenkorbNr, String user){
        setWarenkorbNr(warenkorbNr);
        setUser(user);
    }

    public int getWarenkorbNr() {
        return warenkorbNr;
    }

    public String getUser() {
        return user;
    }

    public void setWarenkorbNr(int warenkorbNr) {
        this.warenkorbNr = warenkorbNr;
    }

    public void setUser(String user) throws IllegalArgumentException {
        if (user.length() > 255){
            throw new IllegalArgumentException("Name zu lang.");
        } else if (user.isEmpty()){
            throw new IllegalArgumentException("Bitte Namen eingeben");
        } else {
            this.user = user;
        }
    }

    @Override
    public String toString() {
        return "Warenkorb{" +
                "warenkorbNr=" + warenkorbNr +
                ", user='" + user + '\'' +
                '}';
    }
}
