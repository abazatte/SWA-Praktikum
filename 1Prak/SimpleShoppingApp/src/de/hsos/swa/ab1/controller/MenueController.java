package de.hsos.swa.ab1.controller;

import de.hsos.swa.ab1.applicationLogic.EinkauferIn;
import de.hsos.swa.ab1.view.MenueView;

import java.util.Scanner;

public class MenueController {
    MenueView view;
    EinkauferIn einkauferIn;
    WarenkorbController warenkorbController;
    BezahlenController bezahlenController;
    SuchenStartController suchenStartController;
    Scanner scan = new Scanner(System.in);
    public void initialize() {
        System.out.println("MenueController");
        view = new MenueView();
        handleInput();
    }

    private void handleInput(){
        String input = scan.next();
        while (true){
            switch (input) {
                case "1" -> warenkorbController.initialize();
                case "2" -> bezahlenController.initialize();
                case "3" -> suchenStartController.initialize();
                case "e" -> System.exit(0);
                default -> {
                    view.sayHelp();
                    input = scan.next();
                }
            }
        }
    }

    public MenueController(){
        einkauferIn = new EinkauferIn();
        warenkorbController = new WarenkorbController(einkauferIn, this);
        bezahlenController = new BezahlenController(einkauferIn, this);
        suchenStartController = new SuchenStartController(einkauferIn, this);
    }
}
