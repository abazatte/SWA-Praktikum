package de.hsos.swa.ab1.controller;

import de.hsos.swa.ab1.applicationLogic.EinkauferIn;
import de.hsos.swa.ab1.applicationLogic.PruefeWare;
import de.hsos.swa.ab1.businessLogic.Produktinformation;
import de.hsos.swa.ab1.businessLogic.Ware;
import de.hsos.swa.ab1.view.PruefView;

import java.util.Scanner;

public class PruefController {
    PruefView view;
    SuchenStartController parent;
    Scanner scan = new Scanner(System.in);
    PruefeWare einkauferIn;
    Ware ware;

    public void initialize() {
        System.out.println("PruefController");
        view = new PruefView();
        handleInput();
    }

    private void handleInput(){
        String input = scan.next();
        while (true){
            switch (input){
                case "1" -> {
                    //System.out.println("Für welches Produkt moechten Sie die Produktinformationen haben.");
                    //String s = scan.next();
                    System.out.println("Informationen fuer Ware:");
                    System.out.println(ware);
                    Produktinformation produktinformation = einkauferIn.holeDetailinformation(ware);
                    //TODO: hier ändern
                    if (produktinformation != null) System.out.println(produktinformation);
                    else System.out.println("Keine Informationen da.");
                    view.sayHelp();
                    input = scan.next();
                }
                case "b" -> parent.initialize();
                case "e" -> System.exit(0);
                default -> {
                    view.sayHelp();
                    input = scan.next();
                }
            }
        }
    }

    public PruefController(EinkauferIn e, SuchenStartController m) {
        parent = m;
        einkauferIn = e;
    }

    public void setWare(Ware ware) {
        this.ware = ware;
    }
}
