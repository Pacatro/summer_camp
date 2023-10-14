package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import factory.CompleteInscription;
import factory.ParcialInscription;

/**
 * Represents a menu for managing the system's assistants, campaments, and inscriptions.
 */
public class Menu {
    private Scanner scanner;
    private ArrayList<Monitor> monitors;
    private ArrayList<Activity> activities;
    private ArrayList<Assistant> assistants;
    private ArrayList<Campament> campaments;
    private ArrayList<CompleteInscription> completeInscriptions;
    private ArrayList<ParcialInscription> parcialInscriptions;

    /**
     * Initializes a new Menu instance with the required data.
     *
     * @param scanner             A Scanner object for user input.
     * @param monitors            The list of monitors in the system.
     * @param activities          The list of activities in the system.
     * @param assistants          The list of assistants in the system.
     * @param campaments          The list of campaments in the system.
     * @param completeInscriptions The list of complete inscriptions in the system.
     * @param parcialInscriptions  The list of parcial inscriptions in the system.
     */
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
        AssistantManager manager = new AssistantManager();
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
                    
                    Assistant assistant;

                    System.out.print("Introduzca el id del asistente: ");
                    int assistantID = scanner.nextInt();

                    assistant = manager.search(assistantID, assistants);

                    if(assistant != null){
                        System.out.println("\nEl id del asistente ya ha sido registrado previamente.");
                        break;
                    }

                    scanner.nextLine();
                    System.out.print("Nombre del asistente: ");
                    String assistantName = scanner.nextLine();

                    System.out.print("Apellido del asistente: ");
                    String assistantSurname = scanner.nextLine();

                    System.out.print("Introduzca el cumpleaños del asistente (AAAA-MM-DD): ");
                    String assistantBdayStr = scanner.nextLine();
                    LocalDate assistantBday = LocalDate.parse(assistantBdayStr);

                    System.out.print("¿El asistente necesita atencion especial? (true/false): ");
                    boolean atention = scanner.nextBoolean();
                    
                    assistant = new Assistant(assistantID, assistantName, assistantSurname, assistantBday, atention);

                    if(!manager.register(assistant, this.assistants)){
                        System.out.println("\nNo se ha podido registrar al asistente.");
                        break;
                    }

                    System.out.println("\nEl asistente ha sido registrado con exito.");

                break;

                case 2:
                    System.out.println("Modificando asistente...");

                    System.out.print("Introduzca el id del asistente a modificar: ");
                    int newAssistantID = scanner.nextInt();

                    if(manager.search(newAssistantID, this.assistants) == null){
                        System.out.println("\nNo existe un asistente con ese id en la lista.");
                        break;
                    }

                    scanner.nextLine();
                    System.out.print("Nuevo nombre del asistente: ");
                    String newAssistantName = scanner.nextLine();

                    System.out.print("Apellido del asistente: ");
                    String newAssistantSurname = scanner.nextLine();

                    System.out.print("Introduzca el cumpleaños del asistente (AAAA-MM-DD): ");
                    String newAssistantBdayStr = scanner.nextLine();
                    LocalDate newAssistantBday = LocalDate.parse(newAssistantBdayStr);

                    System.out.print("El asistente necesita atencion especial (true/false): ");
                    boolean newAtention = scanner.nextBoolean();

                    if(!manager.modify(newAssistantID, newAssistantName, newAssistantSurname, newAssistantBday, newAtention, this.assistants)){
                        System.out.println("\nEl asistente no se ha modificado correctamente.");
                        break;
                    }

                    System.out.println("\nAsistente modificado correctamente.");
                break;

                case 3:
                    System.out.println("Lista de asistentes: ");
                    manager.print(this.assistants);
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

                    Level campLevel = Level.CHILD;

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
                            campLevel = Level.CHILD;
                        }else if(subopt == 2){
                            campLevel = Level.YOUTH;
                        }else if(subopt == 3){
                            campLevel = Level.TEENAGER;
                        }else{
                            System.out.println("Elija una opcion correcta");
                        }

                    }while(subopt != 1 && subopt != 2);

                    manager.createCampaments(this.campaments, campId, initDate, finalDate, campLevel);
                break;

                case 4:
                    System.out.println("Asociando monitor - actividad...");

                    for (int k = 0; k < activities.size(); k++) {
                        System.out.println("Actividad: " + k + ") " + activities.get(k).getname());
                    }

                    System.out.print("Selecciona una actividad: ");
                    int selectedActivityIndex = scanner.nextInt();
            
                    while (!(selectedActivityIndex >= 0 && selectedActivityIndex < activities.size())) {
                        System.out.println("Índice de actividad no válido. No se asignó ninguna actividad.");
                        System.out.print("Selecciona una actividad: ");
                        selectedActivityIndex = scanner.nextInt();
                    }
            
                    Activity activity = activities.get(selectedActivityIndex);

                    int numMonitorsNeeded = activity.getNumMonitors();
                    System.out.println("Número de monitores a asociar a esta actividad: " + numMonitorsNeeded);

                    int selectedMonitorIndex = 0;

                    for (int i = 0; i < numMonitorsNeeded; i++) {
                        System.out.println("\nSelecciona un monitor:\n");
            
                        for (int j = 0; j < monitors.size(); j++) {
                            if (!monitors.get(j).isEspecial()) {
                                System.out.println(j + ". " + monitors.get(j).getName());
                            }
                        }
            
                        selectedMonitorIndex = scanner.nextInt();
                        while (!(selectedMonitorIndex >= 0 && selectedMonitorIndex < monitors.size()
                                && monitors.get(selectedMonitorIndex).isEspecial() == false)) {
                            System.out.println("Índice de monitor no válido.");
                            System.out.print("Selecciona un monitor: ");
                            selectedMonitorIndex = scanner.nextInt();
                        }
                        
                    }

                    manager.associateMonitorsToActivities(this.activities, this.monitors, selectedMonitorIndex, activity);

                break;
                case 5:
                    System.out.println("Asociando actividad - campamento...");

                    // System.out.println("Asociación de actividades a campamentos:");

                    for (int k = 0; k < campaments.size(); k++) {
                        System.out.println("Campamento: " + k + ") " + campaments.get(k).getId());
                    }

                    System.out.println("Selecciona un campamento:");
                    int selectedCampamentIndex = scanner.nextInt();
                    scanner.nextLine();

                    while (!(selectedCampamentIndex >= 0 && selectedCampamentIndex < campaments.size())) {
                        System.out.println("Índice del campamento no válido.");
                        System.out.println("Selecciona un campamento:");
                        selectedCampamentIndex = scanner.nextInt();
                        scanner.nextLine();
                    }
                    Campament selectedCampament = campaments.get(selectedCampamentIndex);
                    Level campamentLevel = selectedCampament.getLevel();

                    for (int j = 0; j < activities.size(); j++) {
                        if (activities.get(j).getLevel().equals(campamentLevel)) {
                            System.out.println(j + ". " + activities.get(j).getname());
                        }
                    }

                    System.out.println("Selecciona una actividad:");
                    int selectedActivityIndex2 = scanner.nextInt();
                    scanner.nextLine();

                    while (!(selectedActivityIndex2 >= 0 && selectedActivityIndex2 < activities.size())) {
                        System.out.println("Índice de actividad no válido.");
                        System.out.println("Selecciona una actividad:");
                        selectedActivityIndex2 = scanner.nextInt();
                        scanner.nextLine();
                    }

                    manager.associateActivitiesToCampaments(this.campaments, this.activities, selectedActivityIndex2, selectedCampament);
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
