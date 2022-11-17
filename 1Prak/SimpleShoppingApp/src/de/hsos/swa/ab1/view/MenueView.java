package de.hsos.swa.ab1.view;

public class MenueView {

    private void sayMenueItems() {
        sayHelp();
    }

    public void sayHelp(){
        System.out.println("""
                InputHelper:
                (1) Warenkorb
                (2) Bezahlen
                (3) Suchen
                (e) exit""");
    }

    public MenueView(){
        sayMenueItems();
    }
}
