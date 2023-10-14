package classes;

import java.time.LocalDate;
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
            opt = this.scanner.nextInt();
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
        int opt;
        do{
            System.out.println();
            System.out.println("Elija una opcion:");
            System.out.println("1) Dar de alta asistente");
            System.out.println("2) Modificar asistente");
            System.out.println("3) Listar asistentes registrados");
            System.out.println("4) Cancelar");
            System.out.print("> ");
            opt = this.scanner.nextInt();
            System.out.println();

            switch(opt){
                case 1:
                    System.out.println("Creando asistente...");
                break;

                case 2:
                    System.out.println("Modificando asistente...");
                break;

                case 3:
                    System.out.println("Lista de asistentes: ");
                break;

                case 4:
                    System.out.println("Volviendo al menu principal...");
                break;

                default:
                    System.out.println("Elija una opcion correcta");
            }

        }while(opt != 4);
    }

    public void campamentsManager() throws Exception{
        Campaments_Manager manager = new Campaments_Manager();
        int opt, subopt;
        do{
            System.out.println();
            System.out.println("Elija una opcion:");
            System.out.println("1) Crear actividad");
            System.out.println("2) Crear monitor");
            System.out.println("3) Crear campamento");
            System.out.println("4) Asociar monitor a actividad");
            System.out.println("5) Asociar actividad a campamento");
            System.out.println("6) Asociar monitor a campamento");
            System.out.println("7) Cancelar");
            System.out.print("> ");
            opt = this.scanner.nextInt();
            System.out.println();

            switch(opt){
                case 1:
                    System.out.println("Creando actividad...");

                    System.out.print("Nombre de la actividad: ");
                    String activName = scanner.nextLine();

                    Level level = Level.CHILD;
                    do{
                        System.out.println();
                        System.out.println("Indique el nivel:");
                        System.out.println("1) Infantil");
                        System.out.println("2) Juvenil");
                        System.out.println("3) Adolescente");
                        System.out.print("> ");
                        subopt = this.scanner.nextInt();
                        System.out.println();

                        if(subopt == 1){
                            level = Level.CHILD;
                        }else if(subopt == 2){
                            level = Level.YOUTH;
                        }else if(subopt == 3){
                            level = Level.TEENAGER;
                        }else{
                            System.out.println("Elija una opcion correcta");
                        }

                    }while(subopt != 1 && subopt != 2);

                    Schendule schendule = Schendule.MORNING;
                    do{
                        System.out.println();
                        System.out.println("Indique el horario:");
                        System.out.println("1) Mañana");
                        System.out.println("2) Tarde");
                        System.out.print("> ");
                        subopt = this.scanner.nextInt();
                        System.out.println();

                        if(subopt == 1){
                            schendule = Schendule.MORNING;
                        }else if(subopt == 2){
                            schendule = Schendule.AFTERNOON;
                        }else{
                            System.out.println("Elija una opcion correcta");
                        }

                    }while(subopt != 1 && subopt != 2);

                    System.out.print("Máximos participantes de la actividad: ");
                    int max_participants = scanner.nextInt();

                    System.out.print("Número de monitores: ");
                    int num_monitors = scanner.nextInt();

                    manager.createActivity(this.activities, activName, level, schendule, max_participants, num_monitors);

                break;

                case 2:
                    System.out.println("Creando monitor...");

                    System.out.print("ID del monitor: ");
                    int monId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nombre del monitor: ");
                    String monName = scanner.nextLine();

                    System.out.print("Apellido del monitor: ");
                    String surname = scanner.nextLine();

                    System.out.print("¿Es un monitor de atención especial? (true/false): ");
                    boolean isEspecial = scanner.nextBoolean();

                    manager.createMonitor(this.monitors, monId, monName, surname, isEspecial);
                break;

                case 3:
                    System.out.println("Creando campamento...");

                    System.out.print("ID del campamento: ");
                    int campId = scanner.nextInt();

                    System.out.print("Fecha de inicio (AAAA-MM-DD): ");
                    String initDateStr = scanner.next();
                    LocalDate initDate = LocalDate.parse(initDateStr);

                    System.out.print("Fecha de finalización (AAAA-MM-DD): ");
                    String finalDateStr = scanner.next();
                    LocalDate finalDate = LocalDate.parse(finalDateStr);

                    manager.createCampaments(this.campaments, campId, initDate, finalDate);
                break;

                case 4:
                    System.out.println("Asociando monitor - actividad...");

                    manager.associateMonitorsToActivities(this.activities, this.monitors);

                case 5:
                    System.out.println("Asociando actividad - campamento...");

                    manager.associateActivitiesToCampaments(this.campaments, this.activities);
                break;

                case 6:
                    System.out.println("Asociando monitor - campamento...");

                    manager.associateMonitorsToCampaments(this.campaments, this.monitors);
                break;

                case 7:
                    System.out.println("Volviendo al menu principal...");
                break;

                default:
                    System.out.println("Elija una opcion correcta");
            }

        }while(opt != 7);
    }

    public void inscriptionsManager() throws Exception{
        InscriptionsManager manager = new InscriptionsManager();

        int opt, subopt;
        do{
            System.out.println();
            System.out.println("Elija el tipo de inscripcion a crear:");
            System.out.println("1) Completa");
            System.out.println("2) Parcial");
            System.out.println("3) Cancelar");
            System.out.print("> ");
            opt = this.scanner.nextInt();
            System.out.println();

            if(opt == 3){
                System.out.println("Volviendo al menu principal...");
                return;
            }

            System.out.println("Creando inscripcion...");
            System.out.println();

            System.out.print("Indique el id del campamento: ");
            int campId = this.scanner.nextInt();
            
            Campament campament = new Campament();
            boolean flag = false;
            for(int i = 0; i < this.campaments.size() && !flag; i++){
                if(this.campaments.get(i).getId() == campId){
                    campament = (this.campaments.get(i));
                    flag = true;
                }
            }

            if(!flag){
                System.out.println("Error, no se encontro el campamento");
                return;
            }

            
            System.out.print("Indique el id del asistente: ");
            int assisId = this.scanner.nextInt();

            Assistant assistant = new Assistant();
            flag = false;
            for(int i = 0; i < this.assistants.size() && !flag; i++){
                if(this.assistants.get(i).getId() == assisId){
                    assistant = (this.assistants.get(i));
                    flag = true;
                }
            }

            if(!flag){
                System.out.println("Error, no se encontro el assistente");
                return;
            }


            Schendule schendule = Schendule.MORNING;
            do{
                System.out.println();
                System.out.println("Indique el horario:");
                System.out.println("1) Mañana");
                System.out.println("2) Tarde");
                System.out.print("> ");
                subopt = this.scanner.nextInt();
                System.out.println();

                if(subopt == 1){
                    schendule = Schendule.MORNING;
                }else if(subopt == 2){
                    schendule = Schendule.AFTERNOON;
                }else{
                    System.out.println("Elija una opcion correcta");
                }

            }while(subopt != 1 && subopt != 2);
            
            
            switch(opt){
                case 1:
                    manager.enrollComplete(campament, assistant, schendule, completeInscriptions);
                break;

                case 2:
                    manager.enrollParcial(campament, assistant, schendule, parcialInscriptions);
                break;

                default:
                    System.out.println("Elija una opcion correcta");
            }

        }while(opt != 3);
    }
}
