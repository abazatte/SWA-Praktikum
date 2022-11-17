package de.hsos.swa.ab1.controller;

import de.hsos.swa.ab1.applicationLogic.EinkauferIn;
import de.hsos.swa.ab1.applicationLogic.HoleWarenkorb;
import de.hsos.swa.ab1.businessLogic.Ware;
import de.hsos.swa.ab1.view.SuchenStartView;

import java.util.Scanner;

public class SuchenStartController {
    HoleWarenkorb einkauferIn;
    MenueController parent;
    SuchenStartView view;
    Scanner scan = new Scanner(System.in);
    SuchController suchController;
    PruefController pruefController;
    AuswahlController auswahlController;
    AdminPanelController adminPanelController;
    Ware ware;

    public SuchenStartController(EinkauferIn e, MenueController m){
        parent = m;
        einkauferIn = e;
        suchController = new SuchController((EinkauferIn) einkauferIn, this);
        pruefController = new PruefController((EinkauferIn) einkauferIn, this);
        auswahlController = new AuswahlController((EinkauferIn) einkauferIn, this);
        adminPanelController = new AdminPanelController(this);
    }

    private void handleInput(){
        String input = scan.next();
        while (true){
            switch (input){
                case "1" -> suchController.initialize();
                case "2" -> {
                    if (ware != null) {
                        pruefController.setWare(ware);
                        pruefController.initialize();
                    }
                    else System.out.println("Pruefen nicht möglich");
                    view.sayHelp();
                    input = scan.next();
                }
                case "3" -> {
                    if (ware != null){
                        auswahlController.setWare(ware);
                        auswahlController.initialize();
                    } else System.out.println("Auswaehlen nicht moeglich");
                    view.sayHelp();
                    input = scan.next();
                }
                case "4" -> adminPanelController.initialize();
                case "b" -> parent.initialize();
                case "e" -> System.exit(0);
                default -> {
                    view.sayHelp();
                    input = scan.next();
                }
            }
        }
    }

    public void initialize() {
        System.out.println("SuchenStartController");
        view = new SuchenStartView();
        handleInput();
    }

    public void setWare(Ware ware){
        this.ware = ware;
    }
}
