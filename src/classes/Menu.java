package classes;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public Menu(Scanner scanner) {this.scanner = scanner;}

    public void mainMenu() throws Exception{
        int opt = 4;
        do{
            System.out.println();
            System.out.println("Elija un usuario: ");
            System.out.println("1) Gestor de asistentes");
            System.out.println("2) Gestor de campamentos");
            System.out.println("3) Gestor de inscripciones");
            System.out.println("4) Salir");
            System.out.print("> ");
            opt = Integer.parseInt(this.scanner.nextLine());
            System.out.println();

            if(opt < 1 || opt > 4){
                
            }

            switch(opt){
                case 1:
                    System.out.println("Gestor de asistentes");
                break;

                case 2:
                    System.out.println("Gestor de campamentos");
                break;

                case 3:
                    System.out.println("Gestor de inscripciones");
                break;

                case 4:
                    System.out.println("Saliendo...");
                break;

                default:
                    System.out.println("Elija una opcion correcta");
                
            }
            
        }while(opt != 4);
    }
}
