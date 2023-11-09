package es.uco.pw.display;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import es.uco.pw.business.activity.ActivityDTO;
import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.monitor.MonitorDTO;
import es.uco.pw.business.schedule.Schedule;
import es.uco.pw.business.level.Level;
import es.uco.pw.business.factory.CompleteInscriptionDTO;
import es.uco.pw.business.factory.ParcialInscriptionDTO;
import es.uco.pw.business.managers.AssistantManager;
import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.business.managers.InscriptionsManager;

/**
 * Represents a menu for managing the system's assistants, campaments, and inscriptions.
 */
public class Menu {
    private Scanner scanner;

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
    public Menu(Scanner scanner){}

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
            opt = Integer.parseInt(this.scanner.nextLine());
            System.out.println();

            switch(opt){
                case 1:
                    System.out.println("Creando asistente...");
                    
                    AssistantDTO assistant;

                    System.out.print("Introduzca el id del asistente: ");
                    int assistantID = Integer.parseInt(this.scanner.nextLine());

                    assistant = manager.getById(assistantID);

                    if(assistant != null){
                        System.out.println("\nEl id del asistente ya ha sido registrado previamente.");
                        break;
                    }

                    this.scanner.nextLine();
                    System.out.print("Nombre del asistente: ");
                    String assistantName = this.scanner.nextLine();

                    System.out.print("Apellido del asistente: ");
                    String assistantSurname = this.scanner.nextLine();

                    System.out.print("Introduzca el cumpleaños del asistente (AAAA-MM-DD): ");
                    String assistantBdayStr = this.scanner.nextLine();
                    LocalDate assistantBday = LocalDate.parse(assistantBdayStr);

                    System.out.print("¿El asistente necesita atencion especial? (true/false): ");
                    boolean atention = Boolean.parseBoolean(this.scanner.nextLine());
                    
                    assistant = new AssistantDTO(assistantID, assistantName, assistantSurname, assistantBday, atention);

                    // TODO: HACER MEJOR CONTROL ERRORES
                    manager.register(assistant);

                    /*
                    if(!manager.register(assistant)){
                        System.out.println("\nNo se ha podido registrar al asistente.");
                        break;
                    }
                    */

                    System.out.println("\nEl asistente ha sido registrado con exito.");

                break;

                case 2:
                    System.out.println("Modificando asistente...");

                    System.out.print("Introduzca el id del asistente a modificar: ");
                    int newAssistantID = Integer.parseInt(this.scanner.nextLine());

                    if(manager.getById(newAssistantID) == null){
                        System.out.println("\nNo existe un asistente con ese id en la lista.");
                        break;
                    }

                    this.scanner.nextLine();
                    System.out.print("Nuevo nombre del asistente: ");
                    String newAssistantName = this.scanner.nextLine();

                    System.out.print("Apellido del asistente: ");
                    String newAssistantSurname = this.scanner.nextLine();

                    System.out.print("Introduzca el cumpleaños del asistente (AAAA-MM-DD): ");
                    String newAssistantBdayStr = this.scanner.nextLine();
                    LocalDate newAssistantBday = LocalDate.parse(newAssistantBdayStr);

                    System.out.print("El asistente necesita atencion especial (true/false): ");
                    boolean newAtention = Boolean.parseBoolean(this.scanner.nextLine());

                    // TODO: HACER MEJOR CONTROL ERRORES
                    manager.modify(newAssistantID, newAssistantName, newAssistantSurname, newAssistantBday, newAtention);
                    /*
                    if(!manager.modify(newAssistantID, newAssistantName, newAssistantSurname, newAssistantBday, newAtention)){
                        System.out.println("\nEl asistente no se ha modificado correctamente.");
                        break;
                    }
                    */

                    System.out.println("\nAsistente modificado correctamente.");
                break;

                case 3:
                    System.out.println("Lista de asistentes: ");
                    // TODO: NO SE SI ESTA FUNCION ESTA ACABADA
                    manager.print();
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
        CampamentsManager manager = new CampamentsManager();
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
            opt = Integer.parseInt(this.scanner.nextLine());
            System.out.println();

            switch(opt){
                case 1:
                    System.out.println("Creando actividad...");

                    System.out.print("Nombre de la actividad: ");
                    String activName = this.scanner.nextLine();

                    Level level = Level.CHILD;
                    do{
                        System.out.println();
                        System.out.println("Indique el nivel:");
                        System.out.println("1) Infantil");
                        System.out.println("2) Juvenil");
                        System.out.println("3) Adolescente");
                        System.out.print("> ");
                        subopt = Integer.parseInt(this.scanner.nextLine());

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

                    Schedule schedule = Schedule.MORNING;
                    do{
                        System.out.println();
                        System.out.println("Indique el horario:");
                        System.out.println("1) Mañana");
                        System.out.println("2) Tarde");
                        System.out.print("> ");
                        subopt = Integer.parseInt(this.scanner.nextLine());
                        System.out.println();

                        if(subopt == 1){
                            schedule = Schedule.MORNING;
                        }else if(subopt == 2){
                            schedule = Schedule.AFTERNOON;
                        }else{
                            System.out.println("Elija una opcion correcta");
                        }

                    }while(subopt != 1 && subopt != 2);

                    System.out.print("Máximos participantes de la actividad: ");
                    int max_participants = Integer.parseInt(this.scanner.nextLine());

                    System.out.print("Número de monitores: ");
                    int num_monitors = Integer.parseInt(this.scanner.nextLine());

                    manager.createActivity(activName, level, schedule, max_participants, num_monitors);

                break;

                case 2:
                    System.out.println("Creando monitor...");

                    System.out.print("ID del monitor: ");
                    int monId = Integer.parseInt(this.scanner.nextLine());

                    System.out.print("Nombre del monitor: ");
                    String monName = this.scanner.nextLine();

                    System.out.print("Apellido del monitor: ");
                    String surname = this.scanner.nextLine();

                    System.out.print("¿Es un monitor de atención especial? (true/false): ");
                    boolean isEspecial = Boolean.parseBoolean(this.scanner.nextLine());

                    manager.createMonitor(monId, monName, surname, isEspecial);
                break;

                case 3:
                    System.out.println("Creando campamento...");

                    System.out.print("ID del campamento: ");
                    int campId = Integer.parseInt(this.scanner.nextLine());

                    System.out.print("Fecha de inicio (AAAA-MM-DD): ");
                    String initDateStr = this.scanner.nextLine();
                    LocalDate initDate = LocalDate.parse(initDateStr);

                    System.out.print("Fecha de finalización (AAAA-MM-DD): ");
                    String finalDateStr = this.scanner.nextLine();
                    LocalDate finalDate = LocalDate.parse(finalDateStr);

                    Level campLevel = Level.CHILD;

                    do{
                        System.out.println();
                        System.out.println("Indique el nivel:");
                        System.out.println("1) Infantil");
                        System.out.println("2) Juvenil");
                        System.out.println("3) Adolescente");
                        System.out.print("> ");
                        subopt = Integer.parseInt(this.scanner.nextLine());
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

                    manager.createCampaments(campId, initDate, finalDate, campLevel);
                break;

                case 4:
                    
                    /* TODO: IMPLEMENTAR ESTO USANDO LOS MANAGERS INSERTANDO EN LAS TABLAS DE RELACION DE LA BD
                    System.out.println("Asociando monitor - actividad...");

                    for (int k = 0; k < activities.size(); k++) {
                        System.out.println("Actividad: " + k + ") " + activities.get(k).getname());
                    }

                    System.out.print("Selecciona una actividad: ");
                    int selectedActivityIndex = Integer.parseInt(this.scanner.nextLine());
            
                    while (!(selectedActivityIndex >= 0 && selectedActivityIndex < activities.size())) {
                        System.out.println("Índice de actividad no válido. No se asignó ninguna actividad.");
                        System.out.print("Selecciona una actividad: ");
                        selectedActivityIndex = Integer.parseInt(this.scanner.nextLine());
                    }
            
                    ActivityDTO activity = activities.get(selectedActivityIndex);

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
            
                        selectedMonitorIndex = Integer.parseInt(this.scanner.nextLine());
                        while (!(selectedMonitorIndex >= 0 && selectedMonitorIndex < monitors.size()
                                && monitors.get(selectedMonitorIndex).isEspecial() == false)) {
                            System.out.println("Índice de monitor no válido.");
                            System.out.print("Selecciona un monitor: ");
                            selectedMonitorIndex = Integer.parseInt(this.scanner.nextLine());
                        }
                        
                    }

                    manager.associateMonitorsToActivities(selectedMonitorIndex, activity);
                    */

                break;
                case 5:
                    /* TODO: IMPLEMENTAR ESTO USANDO LOS MANAGERS INSERTANDO EN LAS TABLAS DE RELACION DE LA BD
                    System.out.println("Asociando actividad - campamento...");

                    System.out.println("Asociación de actividades a campamentos:");

                    for (int k = 0; k < campaments.size(); k++) {
                        System.out.println("Campamento: " + k + ") " + campaments.get(k).getId());
                    }

                    System.out.print("Selecciona un campamento:");
                    int selectedCampamentIndex = this.scanner.nextInt();
                    this.scanner.nextLine();

                    while (!(selectedCampamentIndex >= 0 && selectedCampamentIndex < campaments.size())) {
                        System.out.println("Índice del campamento no válido.");
                        System.out.print("Selecciona un campamento:");
                        selectedCampamentIndex = this.scanner.nextInt();
                        this.scanner.nextLine();
                    }
                    CampamentDTO selectedCampament = campaments.get(selectedCampamentIndex);
                    Level campamentLevel = selectedCampament.getLevel();
                    
                    for (int j = 0; j < activities.size(); j++) {
                        if (activities.get(j).getLevel().equals(campamentLevel)) {
                            System.out.println(j + ". " + activities.get(j).getname());
                        }
                    }

                    System.out.print("Selecciona una actividad:");
                    int selectedActivityIndex2 = this.scanner.nextInt();
                    this.scanner.nextLine();

                    while (!(selectedActivityIndex2 >= 0 && selectedActivityIndex2 < activities.size())) {
                        System.out.println("Índice de actividad no válido.");
                        System.out.print("Selecciona una actividad:");
                        selectedActivityIndex2 = this.scanner.nextInt();
                        this.scanner.nextLine();
                    }

                    manager.associateActivitiesToCampaments(this.campaments, this.activities, selectedActivityIndex2, selectedCampament);
                    */
                break;

                case 6:
                    /* TODO: IMPLEMENTAR ESTO USANDO LOS MANAGERS INSERTANDO EN LAS TABLAS DE RELACION DE LA BD

                    System.out.println("Asociando monitor - campamento...");
                    for (int k = 0; k < campaments.size(); k++) {
                        System.out.println("Campamento: " + k + ")" + campaments.get(k).getId());
                    }
            
                    System.out.print("Selecciona un campamento:");
                    int selectedCampamentIndex3 = this.scanner.nextInt();
                    this.scanner.nextLine();
            
                    while (!(selectedCampamentIndex3 >= 0 && selectedCampamentIndex3 < campaments.size())) {
                        System.out.println("Índice del campamento no válido.");
                        System.out.println("Selecciona un campamento:");
                    }
            
                    CampamentDTO selectedCampament2= campaments.get(selectedCampamentIndex3);
            
                    for (int j = 0; j < monitors.size(); j++) {
                        System.out.println(j + ". " + monitors.get(j).getName());
                    }
            
                    System.out.print("Selecciona un monitor:");
                    int selectedMonitorIndex3 = this.scanner.nextInt();
                    this.scanner.nextLine();
            
                    while (!(selectedMonitorIndex3 >= 0 && selectedMonitorIndex3 < monitors.size())) {
                        System.out.println("Índice de monitor no válido.");
                        System.out.print("Selecciona un monitor:");
                        selectedMonitorIndex = this.scanner.nextInt();
                        this.scanner.nextLine();
                    }
                    
                    manager.associateMonitorsToCampaments(this.campaments, this.monitors, selectedCampamentIndex3, selectedCampament2);
                    */
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
            opt = Integer.parseInt(this.scanner.nextLine());
            System.out.println();

            if(opt == 3){
                System.out.println("Volviendo al menu principal...");
                return;
            }

            System.out.println("Creando inscripcion...");
            System.out.println();

            System.out.print("Indique el id del campamento: ");
            int campId = Integer.parseInt(this.scanner.nextLine());
            
            CampamentsManager campamentsManager = new CampamentsManager();

            CampamentDTO campament = campamentsManager.getById(campId);
            
            System.out.print("Indique el id del asistente: ");
            int assisId = Integer.parseInt(this.scanner.nextLine());

            AssistantManager assistantManager = new AssistantManager();
            AssistantDTO assistant = assistantManager.getById(assisId);

            Schedule schedule = Schedule.MORNING;
            
            do{
                System.out.println();
                System.out.println("Indique el horario:");
                System.out.println("1) Mañana");
                System.out.println("2) Tarde");
                System.out.print("> ");
                subopt = Integer.parseInt(this.scanner.nextLine());
                System.out.println();

                if(subopt == 1){
                    schedule = Schedule.MORNING;
                }else if(subopt == 2){
                    schedule = Schedule.AFTERNOON;
                }else{
                    System.out.println("Elija una opcion correcta");
                }

            }while(subopt != 1 && subopt != 2);
            
            
            switch(opt){
                case 1:
                    manager.enrollComplete(campament, assistant, schedule);
                break;

                case 2:
                    manager.enrollParcial(campament, assistant);
                break;

                default:
                    System.out.println("Elija una opcion correcta");
            }

        }while(opt != 3);
    }
}
