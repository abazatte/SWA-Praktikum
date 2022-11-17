package de.hsos.swa.ab1.view;

public class AdminPanelView {
    private void sayAdminPanelItems(){
        sayHelp();
    }

    public void sayHelp(){
        System.out.println("""
                InputHelper:
                (1) Ware adden
                (2) Ware loeschen
                (3) Ware aendern
                (4) alle Tabellen loeschen
                (b) zurueck
                (e) exit""");
    }

    public AdminPanelView(){
        sayAdminPanelItems();
    }
}
