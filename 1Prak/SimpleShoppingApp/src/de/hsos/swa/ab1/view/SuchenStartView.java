package de.hsos.swa.ab1.view;

public class SuchenStartView {

    private void saySuchStartItems(){
        sayHelp();
    }

    public void sayHelp(){
        System.out.println("""
                InputHelper:
                (1) Suchen
                (2) Prüfen
                (3) Auswahl
                (4) Admin Panel
                (b) zurueck
                (e) exit""");
    }

    public SuchenStartView(){
        saySuchStartItems();
    }
}
