package de.hsos.swa.ab1.controller;

import de.hsos.swa.ab1.applicationLogic.EinkauferIn;
import de.hsos.swa.ab1.applicationLogic.WaehleWare;
import de.hsos.swa.ab1.businessLogic.Ware;
import de.hsos.swa.ab1.view.AuswahlView;

import java.util.Scanner;

public class AuswahlController {
    AuswahlView view;
    SuchenStartController parent;
    Scanner scan = new Scanner(System.in);
    Ware ware;
    WaehleWare einkauferIn;
    public void initialize(){
        System.out.println("AuswahlController");
        view = new AuswahlView();
        handleInput();
    }

    public void setWare(Ware ware) {
        this.ware = ware;
    }

    private void handleInput(){
        String input = scan.next();
        while (true){
            switch (input){
                case "1" -> {
                    einkauferIn.wareZuWarenkorbhinzufuegen(ware);
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
    public AuswahlController(EinkauferIn e, SuchenStartController m){
        parent = m;
        einkauferIn = e;
    }
}
