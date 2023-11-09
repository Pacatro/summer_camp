package es.uco.pw.display;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import es.uco.pw.business.activity.ActivityDTO;
import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.common.level.Level;
import es.uco.pw.business.common.schedule.Schedule;
import es.uco.pw.business.managers.AssistantManager;
import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.business.managers.InscriptionsManager;
import es.uco.pw.business.monitor.MonitorDTO;

/**
 * Represents a menu for managing the system's assistants, campaments, and inscriptions.
 */
public class Menu {
    private Scanner scanner;

    /**
     * Initializes a new Menu instance with the required data.
     *
     * @param scanner             A Scanner object for user input.
     */
    public Menu(Scanner scanner){this.scanner = scanner;}

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
                    ArrayList<AssistantDTO> assistants = manager.print();

                    for(AssistantDTO a : assistants){
                        System.out.println("ID: " + a.getId());
                        System.out.println("Nombre: " + a.getName());
                        System.out.println("Apellido: " + a.getSurname());
                        System.out.println("Fecha: " + a.getDate());
                        System.out.println("Atención: " + a.getAtention());
                        System.out.println();
                    }

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

                    }while(subopt < 1 || subopt > 3);
                    System.out.println("Introduzca el número máximo de participantes: ");
                

                    int numMaxParticipants = 0;
                    do{
                    try{
                        max_participants = Integer.parseInt(this.scanner.nextLine());
                        if(max_participants < 0){
                            System.out.println("El número de participantes no puede ser negativo.");
                            max_participants = 0;
                        }
                    }catch (NumberFormatException e){
                        System.out.println("El número de participantes debe ser un número entero.");
                        max_participants = 0;
                    }
                    }while(numMaxParticipants != 0);

                    if(manager.createCampaments(campId, initDate, finalDate, campLevel, max_participants)){
                        System.out.println("Campamento creado correctamente.");
                    }else{
                     System.out.println("No se ha podido crear el campamento.");
                    }
                break;

                case 4:
                    System.out.println("Asociando monitor - actividad...");

                    ArrayList<ActivityDTO> activities = manager.getAllActivities();

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

                    ArrayList<MonitorDTO> monitors = manager.getAllMonitorsNotEspecial();

                    System.out.println("\nSelecciona un monitor:\n");
        
                    for (int j = 0; j < monitors.size(); j++) {
                        System.out.println("Monitor: " + j + ") " + monitors.get(j).getName() + " " + monitors.get(j).getSurname());
                    }
        
                    int selectedMonitorIndex = Integer.parseInt(this.scanner.nextLine());
                    
                    while (!(selectedMonitorIndex >= 0 && selectedMonitorIndex < monitors.size()
                            && monitors.get(selectedMonitorIndex).isEspecial() == false)) {
                        System.out.println("Índice de monitor no válido.");
                        System.out.print("Selecciona un monitor: ");
                        selectedMonitorIndex = Integer.parseInt(this.scanner.nextLine());
                    }

                    MonitorDTO monitor = monitors.get(selectedMonitorIndex);
                        

                    manager.associateMonitorsToActivities(monitor.getID(), activity.getname());

                break;
                case 5:
                    System.out.println("Asociando actividad - campamento...");

                    ArrayList<CampamentDTO> campaments = manager.getAllCampaments();
                    activities = manager.getAllActivities();

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
                    ActivityDTO selectedActivity = activities.get(selectedActivityIndex2);

                    if(manager.associateActivitiesToCampaments(selectedCampament.getId(),selectedActivity.getname())){
                        System.out.println("Actividad asociada correctamente.");
                    }else{
                        System.out.println("No se ha podido asociar la actividad.");
                    }
                
                break;

                case 6:
                    campaments = manager.getAllCampaments();
                    monitors = manager.getAllMonitors();
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
                    MonitorDTO selectedMonitor = monitors.get(selectedMonitorIndex3);

                    if(manager.associateMonitorsToCampaments(selectedCampament2.getId(), selectedMonitor.getID())){
                        System.out.println("Monitor asociado correctamente.");
                    }else{
                        System.out.println("No se ha podido asociar el monitor.");
                    }
                    
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
                    try {
                        manager.enrollComplete(campament, assistant, schedule);
                    } catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                        break;
                    }

                    System.out.println("Inscripcion parcial creada con exito.");
                break;

                case 2:
                    try {
                        manager.enrollParcial(campament, assistant);
                    } catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                        break;
                    }

                    System.out.println("Inscripcion completa creada con exito.");
                break;

                default:
                    System.out.println("Elija una opcion correcta");
            }

        }while(opt != 3);
    }
}
