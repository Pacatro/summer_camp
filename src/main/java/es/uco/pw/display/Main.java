package es.uco.pw.display;

import java.util.Scanner;

import es.uco.pw.business.managers.AssistantManager;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu(scanner);

        //menu.mainMenu();

        AssistantManager manager = new AssistantManager();

        System.out.println(manager.getCampaments("maria@example.com"));
    }

}
