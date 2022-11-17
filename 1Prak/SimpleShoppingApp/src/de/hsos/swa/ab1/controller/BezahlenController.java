package de.hsos.swa.ab1.controller;

import de.hsos.swa.ab1.applicationLogic.EinkauferIn;
import de.hsos.swa.ab1.view.BezahlenView;

import java.util.Scanner;

public class BezahlenController {
    EinkauferIn einkauferIn;
    MenueController parent;
    BezahlenView view;
    Scanner scan = new Scanner(System.in);

    public BezahlenController(EinkauferIn e, MenueController m){
        parent = m;
        einkauferIn = e;
    }

    private void handleInput(){
        String input = scan.next();
        while (true){
            switch (input){
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
        System.out.println("BezahlenController");
        view = new BezahlenView();
        handleInput();
    }
}
