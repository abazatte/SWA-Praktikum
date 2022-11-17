package de.hsos.swa.ab1.controller;

import de.hsos.swa.ab1.businessLogic.KatalogVerwalten;
import de.hsos.swa.ab1.businessLogic.Produktinformation;
import de.hsos.swa.ab1.businessLogic.Ware;
import de.hsos.swa.ab1.databaseLogic.WarenRepository;
import de.hsos.swa.ab1.view.AdminPanelView;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminPanelController {
    SuchenStartController parent;
    AdminPanelView view;
    Scanner scan = new Scanner(System.in);
    KatalogVerwalten katalogVerwalten;


    public void initialize() {
        System.out.println("AdminPanelController");
        katalogVerwalten = WarenRepository.getInstance();
        view = new AdminPanelView();
        handleInput();
    }

    private void handleInput() {
        String input = scan.next();
        while (true) {
            try {
                switch (input) {
                    case "1" -> {
                        System.out.println("Geben Sie die Warennummer ein");
                        int warennummer = scan.nextInt();
                        System.out.println("Geben Sie den Namen ein");
                        String name = scan.next();
                        System.out.println("Geben Sie den Preis ein");
                        double preis = scan.nextDouble();
                        System.out.println("Geben Sie die Beschreibung ein");
                        String beschreibung = scan.next();
                        System.out.println("Geben Sie die Bezeichnung für Produktinformation ein");
                        String bezeichnung = scan.next();
                        katalogVerwalten.addArtikel(new Ware(warennummer, name, BigDecimal.valueOf(preis), beschreibung), new Produktinformation(warennummer, bezeichnung, null));
                    }
                    case "2" -> {
                        System.out.println("Welche Ware wollen Sie löschen?");
                        System.out.println("Bitte Warennummer eingeben!");
                        int warennummer = scan.nextInt();

                        katalogVerwalten.removeArtikel(warennummer);
                    }
                    case "3" -> {
                        System.out.println("Welche Ware wollen Sie änderen?");
                        System.out.println("Bitte Warennummer eingeben!");
                        int warennummer = scan.nextInt();
                        System.out.println("Geben Sie den Namen ein");
                        String name = scan.next();
                        System.out.println("Geben Sie den Preis ein");
                        double preis = scan.nextDouble();
                        System.out.println("Geben Sie die Beschreibung ein");
                        String beschreibung = scan.next();

                        katalogVerwalten.updateArtikel(warennummer, new Ware(warennummer, name, BigDecimal.valueOf(preis), beschreibung));
                    }
                    case "4" -> {
                        System.out.println("Wollen Sie wirklich alle Tabellen löschen? Y/N");
                        String yesNo = scan.next();
                        if (yesNo.equals("Y") || yesNo.equals("y")) {
                            katalogVerwalten.dropAll();
                        } else if (yesNo.equals("N") || yesNo.equals("n")) {
                            System.out.println("Die Tabellen wurden nicht gelöscht!");
                        }
                    }

                    case "b" -> parent.initialize();
                    case "e" -> System.exit(0);
                }
            } catch (InputMismatchException e) {
                System.out.println("Falsche eingabe");
            }
            view.sayHelp();
            input = scan.next();
        }
    }

    public AdminPanelController(SuchenStartController m) {
        parent = m;
    }
}
