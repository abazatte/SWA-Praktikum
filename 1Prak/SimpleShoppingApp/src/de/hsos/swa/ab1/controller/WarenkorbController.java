package de.hsos.swa.ab1.controller;

import de.hsos.swa.ab1.applicationLogic.EinkauferIn;
import de.hsos.swa.ab1.businessLogic.Ware;
import de.hsos.swa.ab1.view.WarenkorbView;

import java.util.Scanner;

public class WarenkorbController {
    WarenkorbView view;
    MenueController parent;
    EinkauferIn einkauferIn;
    Scanner scan = new Scanner(System.in);

    public void initialize(){
        System.out.println("WarenkorbController");
        view = new WarenkorbView();
        handleInput();
    }

    private void handleInput(){
        String input = scan.next();
        while (true){
            switch (input){
                case "1" -> warenkorbAngucken();
                case "b" -> parent.initialize();
                case "e" -> System.exit(0);
                default -> {
                    view.sayHelp();
                    input = scan.next();
                }
            }
            view.sayHelp();
            input = scan.next();
        }
    }

    public WarenkorbController(EinkauferIn e, MenueController m){
        parent = m;
        einkauferIn = e;
    }

    private void warenkorbAngucken(){
        Ware[] waren = einkauferIn.holeWarenkorb(1);
        for (Ware ware : waren) {
            System.out.println(ware);
        }
    }
}
