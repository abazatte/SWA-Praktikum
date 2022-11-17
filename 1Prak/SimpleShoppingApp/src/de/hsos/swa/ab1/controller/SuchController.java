package de.hsos.swa.ab1.controller;

import de.hsos.swa.ab1.applicationLogic.EinkauferIn;
import de.hsos.swa.ab1.applicationLogic.SucheWare;
import de.hsos.swa.ab1.businessLogic.Ware;
import de.hsos.swa.ab1.view.SuchView;

import java.util.Scanner;

public class SuchController {
    SuchView view;
    SuchenStartController parent;
    Scanner scan = new Scanner(System.in);
    SucheWare einkauferin;
    Ware ware;

    public void initialize() {
        System.out.println("SuchController");
        view = new SuchView();
        einkauferin = new EinkauferIn();
        handleInput();
    }

    private void handleInput(){
        String input = scan.next();
        while (true){
            switch (input){
                case "1" -> {
                    System.out.println("Bitte einen Warennamen eingeben: ");
                    String s = scan.next();
                    Ware[] waren = einkauferin.sucheWare(s);
                    for (int i = 0; i < waren.length; i++){
                        System.out.println(waren[i]);
                    }
                    System.out.println("Welche Ware soll für genauere Informationen ausgewählt werden?");
                    int i = scan.nextInt()-1;
                    ware = waren[i];
                    parent.setWare(ware);
                    view.sayHelp();
                    input = scan.next();
                }
                case "2" ->{
                    System.out.println("Bitte eine Warennummer eingeben: ");
                    int tmp = scan.nextInt();
                    Ware waren = einkauferin.sucheWare(tmp);
                    System.out.println(waren.toString());
                    parent.setWare(waren);
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

    public SuchController(EinkauferIn e, SuchenStartController m){
        parent = m;
        einkauferin = e;
    }
}
