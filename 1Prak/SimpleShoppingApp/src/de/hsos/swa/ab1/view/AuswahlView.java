package de.hsos.swa.ab1.view;

public class AuswahlView {

    private void sayAuswahlItems(){
        sayHelp();
    }

    public void sayHelp(){
        System.out.println("""
                InputHelper:
                (1) auswaehlen
                (b) zurueck
                (e) exit""");
    }

    public AuswahlView(){
        sayAuswahlItems();
    }
}
