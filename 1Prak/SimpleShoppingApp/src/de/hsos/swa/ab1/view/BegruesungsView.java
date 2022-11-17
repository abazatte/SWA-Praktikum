package de.hsos.swa.ab1.view;

public class BegruesungsView {
    public void sayHello(){
        System.out.println("""
                Hallo wuenscht dir das Simple Shopping App Team.\s
                Press n to enter Menu
                Press e to exit""");
    }

    public BegruesungsView(){
        sayHello();
    }
}
