package de.hsos.swa.ab1.view;

public class SuchView {

    private void saySuchItems(){
        sayHelp();
    }

    public void sayHelp(){
        System.out.println("""
                InputHelper:
                (1) Suche Ware nach Namen
                (2) Suche Ware nach Nummer
                (b) zurueck
                (e) exit""");
    }

    public SuchView(){
        saySuchItems();
    }
}
