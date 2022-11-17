package de.hsos.swa.ab1.view;

public class BezahlenView {

    private void sayBezahlenItems(){
        sayHelp();
    }

    public void sayHelp(){
        System.out.println("""
                InputHelper:
                (b) zurueck
                (e) exit""");
    }

    public BezahlenView(){
        sayBezahlenItems();
    }
}
