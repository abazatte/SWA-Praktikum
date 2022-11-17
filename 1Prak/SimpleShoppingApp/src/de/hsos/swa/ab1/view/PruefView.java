package de.hsos.swa.ab1.view;

public class PruefView {

    private void sayPruefItems(){
        sayHelp();
    }

    public void sayHelp(){
        System.out.println("""
                InputHelper:
                (1) Ware pruefen
                (b) zurueck
                (e) exit""");
    }

    public PruefView(){
        sayPruefItems();
    }
}
