package de.hsos.swa.ab1.controller;

import de.hsos.swa.ab1.view.BegruesungsView;

import java.util.Scanner;

public class BegruesungsController {
    BegruesungsView view;
    MenueController menueController;
    Scanner scan = new Scanner(System.in);

    public void initialize(){
        System.out.println("BegruesungsController");
        view = new BegruesungsView();
        handleInput();
    }

    private void handleInput(){
        String input = scan.next();
        while (true){
            switch (input){
                case "n" -> menueController.initialize();
                case "e" -> System.exit(0);
                default -> {
                    view.sayHello();
                    input = scan.next();
                }
            }
        }
    }

    public BegruesungsController(){
        menueController = new MenueController();
    }
}
