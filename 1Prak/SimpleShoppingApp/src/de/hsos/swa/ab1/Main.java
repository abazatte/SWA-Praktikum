package de.hsos.swa.ab1;

import de.hsos.swa.ab1.businessLogic.Produktinformation;
import de.hsos.swa.ab1.businessLogic.Ware;
import de.hsos.swa.ab1.businessLogic.WarenImKorb;
import de.hsos.swa.ab1.controller.BegruesungsController;
import de.hsos.swa.ab1.databaseLogic.WarenRepository;
import de.hsos.swa.ab1.warenkorb.Warenkorb;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        WarenRepository conn;
        conn = WarenRepository.getInstance();
        beispielDatenErstellen(conn);
        conn.selectQuery("Lampe");
        new BegruesungsController().initialize();
    }

    public static void beispielDatenErstellen(WarenRepository conn) throws SQLException {
        Ware w1 = new Ware(1L,"Lampe",new BigDecimal("9.5"),"leuchtet");
        conn.insertWare(w1);
        Ware w2 = new Ware(2L,"Teller",new BigDecimal("20.5"),"ist zum Essen geeignet");
        conn.insertWare(w2);
        Ware w3 = new Ware(3L,"Socken",new BigDecimal("2.5"),"haelt die Fuesse warm");
        conn.insertWare(w3);

        Produktinformation p1 = new Produktinformation(1L, "Lampe von Tüll", null);
        conn.insertProduktinformation(p1);
        Produktinformation p2 = new Produktinformation(2L, "Teller aus Steingut", null);
        conn.insertProduktinformation(p2);
        Produktinformation p3 = new Produktinformation(3L, "Socken aus Wolle", null);
        conn.insertProduktinformation(p3);

        Warenkorb wk1 = new Warenkorb(1, "Timi");
        conn.insertWarenkorb(wk1);

        WarenImKorb wik1 = new WarenImKorb(1,1);
        conn.insertWarenImKorb(wik1);

        WarenImKorb wik2 = new WarenImKorb(1,2);
        conn.insertWarenImKorb(wik2);
    }
}