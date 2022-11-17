package de.hsos.swa.ab1.view;

public class WarenkorbView {

    private void sayWarenkorbItems(){
        sayHelp();
    }

    public void sayHelp(){
        System.out.println("""
                InputHelper:
                (1) Warenkorb anschauen
                (b) zurueck
                (e) exit""");
    }
    public WarenkorbView(){
        sayWarenkorbItems();
    }
}
