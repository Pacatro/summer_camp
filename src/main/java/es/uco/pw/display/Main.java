package es.uco.pw.display;

import java.util.Scanner;

import es.uco.pw.business.managers.CampamentsManager;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu(scanner);

        //menu.mainMenu();

        CampamentsManager manager = new CampamentsManager();

        System.out.println(manager.getNumInscriptions(1));
    }

}
