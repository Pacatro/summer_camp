package es.uco.pw.display;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu(scanner);

        menu.mainMenu();
    }

}
