package classes;

import java.util.ArrayList;
import java.util.Scanner;

import factory.CompleteInscription;
import factory.ParcialInscription;

public class Menu {
    private Scanner scanner;
    private ArrayList<Monitor> monitors;
    private ArrayList<Activity> activities;
    private ArrayList<Assistant> assistants;
    private ArrayList<Campament> campaments;
    private ArrayList<CompleteInscription> completeInscriptions;
    private ArrayList<ParcialInscription> parcialInscriptions;

    public Menu(Scanner scanner, ArrayList<Monitor> monitors, ArrayList<Activity> activities, ArrayList<Assistant> assistants,
                ArrayList<Campament> campaments, ArrayList<CompleteInscription> completeInscriptions, ArrayList<ParcialInscription> parcialInscriptions){
        
        this.scanner = scanner;
        this.monitors = monitors;
        this.activities = activities;
        this.assistants = assistants;
        this.campaments = campaments;
        this.completeInscriptions = completeInscriptions;
        this.parcialInscriptions = parcialInscriptions;
    }

    public void mainMenu() throws Exception{
        int opt;
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

            switch(opt){
                case 1:
                    assistantsManager();
                break;

                case 2:
                    campamentsManager();
                break;

                case 3:
                    inscriptionsManager();
                break;

                case 4:
                    System.out.println("Saliendo...");
                break;

                default:
                    System.out.println("Elija una opcion correcta");
                
            }
            
        }while(opt != 4);
    }

    public void assistantsManager() throws Exception{
        System.out.println("Gestor de asistentes");
    }

    public void campamentsManager() throws Exception{
        System.out.println("Gestor de campamentos");
    }

    public void inscriptionsManager() throws Exception{
        InscriptionsManager inscriptionsManager = new InscriptionsManager();

        int opt;
        do{
            System.out.println();
            System.out.println("Elija el tipo de inscripcion a crear:");
            System.out.println("1) Completa");
            System.out.println("2) Parcial");
            System.out.println("3) Cancelar");
            System.out.print("> ");
            opt = Integer.parseInt(this.scanner.nextLine());
            System.out.println();

            switch(opt){
                case 1:
                    System.out.println("Creando inscripcion completa...");
                break;

                case 2:
                    System.out.println("Creando inscripcion parcial...");
                break;

                case 3:
                    System.out.println("Volviendo al menu principal...");
                break;

                default:
                    System.out.println("Elija una opcion correcta");
            }

        }while(opt != 3);
    }
}
