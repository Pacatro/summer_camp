// package es.uco.pw.display;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.Scanner;

// import es.uco.pw.business.activity.ActivityDTO;
// import es.uco.pw.business.assistant.AssistantDTO;
// import es.uco.pw.business.campament.CampamentDTO;
// import es.uco.pw.business.common.level.Level;
// import es.uco.pw.business.common.schedule.Schedule;
// import es.uco.pw.business.managers.AssistantManager;
// import es.uco.pw.business.managers.CampamentsManager;
// import es.uco.pw.business.managers.InscriptionsManager;
// import es.uco.pw.business.monitor.MonitorDTO;
// import es.uco.pw.data.dao.activity.ActivityDAO;
// import es.uco.pw.data.dao.campament.CampamentDAO;
// import es.uco.pw.data.dao.monitor.MonitorDAO;
// import es.uco.pw.display.exceptions.DisplayException;

// /**
//  * Represents a menu for managing the system's assistants, campaments, and inscriptions.
//  */
// public class Menu {
//     private Scanner scanner;

//     /**
//      * Initializes a new Menu instance with the required data.
//      *
//      * @param scanner             A Scanner object for user input.
//      */
//     public Menu(Scanner scanner){this.scanner = scanner;}

//     public void mainMenu() throws Exception{
//         int opt;
//         do{
//             System.out.println();
//             System.out.println("Elija un usuario: ");
//             System.out.println("1) Gestor de asistentes");
//             System.out.println("2) Gestor de campamentos");
//             System.out.println("3) Gestor de inscripciones");
//             System.out.println("4) Salir");
//             System.out.print("> ");
//             opt = Integer.parseInt(this.scanner.nextLine());
//             System.out.println();

//             switch(opt){
//                 case 1:
//                     assistantsManager();
//                 break;

//                 case 2:
//                     campamentsManager();
//                 break;

//                 case 3:
//                     inscriptionsManager();
//                 break;

//                 case 4:
//                     System.out.println("Saliendo...");
//                 break;

//                 default:
//                     System.out.println("Elija una opcion correcta");
                
//             }
            
//         }while(opt != 4);
//     }

//     public void assistantsManager() throws Exception{
//         AssistantManager manager = new AssistantManager();
//         int opt;
//         do{
//             System.out.println();
//             System.out.println("Elija una opcion:");
//             System.out.println("1) Dar de alta asistente");
//             System.out.println("2) Modificar asistente");
//             System.out.println("3) Listar asistentes registrados");
//             System.out.println("4) Cancelar");
//             System.out.print("> ");
//             opt = Integer.parseInt(this.scanner.nextLine());
//             System.out.println();

//             switch(opt){
//                 case 1:
//                     System.out.println("Creando asistente...");
                    
//                     AssistantDTO assistant = null;

//                     System.out.print("Introduzca el id del asistente: ");
//                     int assistantID = Integer.parseInt(this.scanner.nextLine());

//                     try {
//                         assistant = manager.getById(assistantID);
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     if(assistant != null){
//                         DisplayException.handleException(new Exception("El id del asistente ya ha sido registrado previamente."));
//                         break;
//                     }

//                     //this.scanner.nextLine();
//                     System.out.print("Nombre del asistente: ");
//                     String assistantName = this.scanner.nextLine();

//                     System.out.print("Apellido del asistente: ");
//                     String assistantSurname = this.scanner.nextLine();

//                     System.out.print("Introduzca el cumpleaños del asistente (AAAA-MM-DD): ");
//                     String assistantBdayStr = this.scanner.nextLine();
//                     LocalDate assistantBday = LocalDate.parse(assistantBdayStr);

//                     System.out.print("¿El asistente necesita atencion especial? (true/false): ");
//                     boolean atention = Boolean.parseBoolean(this.scanner.nextLine());
                    
//                     assistant = new AssistantDTO(assistantID, assistantName, assistantSurname, assistantBday, atention, null);

//                     try {                   
//                         manager.register(assistant);
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     System.out.println("\nEl asistente ha sido registrado con exito.");

//                 break;

//                 case 2:
//                     System.out.println("Modificando asistente...");

//                     System.out.print("Introduzca el id del asistente a modificar: ");
//                     int newAssistantID = Integer.parseInt(this.scanner.nextLine());
                    
//                     try {
//                         assistant = manager.getById(newAssistantID);
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     System.out.print("Nuevo nombre del asistente: ");
//                     String newAssistantName = this.scanner.nextLine();

//                     System.out.print("Apellido del asistente: ");
//                     String newAssistantSurname = this.scanner.nextLine();

//                     System.out.print("Introduzca el cumpleaños del asistente (AAAA-MM-DD): ");
//                     String newAssistantBdayStr = this.scanner.nextLine();
//                     LocalDate newAssistantBday = LocalDate.parse(newAssistantBdayStr);

//                     System.out.print("El asistente necesita atencion especial (true/false): ");
//                     boolean newAtention = Boolean.parseBoolean(this.scanner.nextLine());

//                     try {
//                         manager.modify(newAssistantID, newAssistantName, newAssistantSurname, newAssistantBday, newAtention, null);
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     System.out.println("\nAsistente modificado correctamente.");
//                 break;

//                 case 3:
//                     ArrayList<AssistantDTO> assistants = new ArrayList<>();
                
//                     try {
//                         assistants = manager.getAll();
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }
                
//                     System.out.println("Lista de asistentes: ");
                    
//                     for(AssistantDTO a : assistants){
//                         System.out.println("ID: " + a.getId());
//                         System.out.println("Nombre: " + a.getName());
//                         System.out.println("Apellido: " + a.getSurname());
//                         System.out.println("Fecha: " + a.getDate());
//                         System.out.println("Atención: " + a.getAtention());
//                         System.out.println();
//                     }

//                 break;

//                 case 4:
//                     System.out.println("Volviendo al menu principal...");
//                 break;

//                 default:
//                     System.out.println("Elija una opcion correcta");
//             }

//         }while(opt != 4);
//     }

//     public void campamentsManager() throws Exception{
//         CampamentsManager manager = new CampamentsManager();
//         int opt, subopt;
//         do{
//             System.out.println();
//             System.out.println("Elija una opcion:");
//             System.out.println("1) Crear actividad");
//             System.out.println("2) Crear monitor");
//             System.out.println("3) Crear campamento");
//             System.out.println("4) Asociar monitor a actividad");
//             System.out.println("5) Asociar actividad a campamento");
//             System.out.println("6) Asociar monitor a campamento");
//             System.out.println("7) Cancelar");
//             System.out.print("> ");
//             opt = Integer.parseInt(this.scanner.nextLine());
//             System.out.println();

//             switch(opt){
//                 case 1:
//                     System.out.println("Creando actividad...");

//                     System.out.print("Nombre de la actividad: ");
//                     String activName = this.scanner.nextLine();

//                     ActivityDTO activity = null;
//                     ActivityDAO activityDAO = new ActivityDAO();

//                     try{
//                         activity = activityDAO.getById(activName);
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     if(activity != null){
//                         DisplayException.handleException(new Exception("El nombre de la actividad ya ha sido registrado previamente."));
//                         break;
//                     }

//                     Level level = Level.CHILD;
//                     do{
//                         System.out.println();
//                         System.out.println("Indique el nivel:");
//                         System.out.println("1) Infantil");
//                         System.out.println("2) Juvenil");
//                         System.out.println("3) Adolescente");
//                         System.out.print("> ");
//                         subopt = Integer.parseInt(this.scanner.nextLine());

//                         if(subopt == 1){
//                             level = Level.CHILD;
//                         }else if(subopt == 2){
//                             level = Level.YOUTH;
//                         }else if(subopt == 3){
//                             level = Level.TEENAGER;
//                         }else{
//                             System.out.println("Elija una opcion correcta");
//                         }

//                     }while(subopt != 1 && subopt != 2);

//                     Schedule schedule = Schedule.MORNING;
//                     do{
//                         System.out.println();
//                         System.out.println("Indique el horario:");
//                         System.out.println("1) Mañana");
//                         System.out.println("2) Tarde");
//                         System.out.print("> ");
//                         subopt = Integer.parseInt(this.scanner.nextLine());
//                         System.out.println();

//                         if(subopt == 1){
//                             schedule = Schedule.MORNING;
//                         }else if(subopt == 2){
//                             schedule = Schedule.AFTERNOON;
//                         }else{
//                             System.out.println("Elija una opcion correcta");
//                         }

//                     }while(subopt != 1 && subopt != 2);

//                     System.out.print("Máximos participantes de la actividad: ");
//                     int max_participants = Integer.parseInt(this.scanner.nextLine());

//                     System.out.print("Número de monitores: ");
//                     int num_monitors = Integer.parseInt(this.scanner.nextLine());

//                     try {
//                         manager.createActivity(activName, level, schedule, max_participants, num_monitors);
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     System.out.println("\nLa actividad ha sido creada correctamente.");

//                 break;

//                 case 2:
//                     System.out.println("Creando monitor...");

//                     System.out.print("ID del monitor: ");
//                     int monId = Integer.parseInt(this.scanner.nextLine());

//                     MonitorDTO monitor = null;
//                     MonitorDAO monitorDAO = new MonitorDAO();

//                     try{
//                         monitor = monitorDAO.getById(monId);
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     if(monitor != null){
//                         DisplayException.handleException(new Exception("El id del monitor ya ha sido registrado previamente."));
//                         break;
//                     }

//                     System.out.print("Nombre del monitor: ");
//                     String monName = this.scanner.nextLine();

//                     System.out.print("Apellido del monitor: ");
//                     String surname = this.scanner.nextLine();

//                     System.out.print("¿Es un monitor de atención especial? (true/false): ");
//                     boolean isEspecial = Boolean.parseBoolean(this.scanner.nextLine());

//                     try {
//                         manager.createMonitor(monId, monName, surname, isEspecial);
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     System.out.println("\nEl monitor ha sido creado correctamente.");

//                 break;

//                 case 3:
//                     System.out.println("Creando campamento...");

//                     System.out.print("ID del campamento: ");
//                     int campId = Integer.parseInt(this.scanner.nextLine());

//                     CampamentDTO campament = null;
//                     CampamentDAO campamentDAO = new CampamentDAO();

//                     try{
//                         campament = campamentDAO.getById(campId);
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     if(campament != null){
//                         DisplayException.handleException(new Exception("El id del monitor ya ha sido registrado previamente."));
//                         break;
//                     }

//                     System.out.print("Fecha de inicio (AAAA-MM-DD): ");
//                     String initDateStr = this.scanner.nextLine();
//                     LocalDate initDate = LocalDate.parse(initDateStr);

//                     System.out.print("Fecha de finalización (AAAA-MM-DD): ");
//                     String finalDateStr = this.scanner.nextLine();
//                     LocalDate finalDate = LocalDate.parse(finalDateStr);

//                     Level campLevel = Level.CHILD;

//                     do{
//                         System.out.println();
//                         System.out.println("Indique el nivel:");
//                         System.out.println("1) Infantil");
//                         System.out.println("2) Juvenil");
//                         System.out.println("3) Adolescente");
//                         System.out.print("> ");
//                         subopt = Integer.parseInt(this.scanner.nextLine());
//                         System.out.println();

//                         if(subopt == 1){
//                             campLevel = Level.CHILD;
//                         }else if(subopt == 2){
//                             campLevel = Level.YOUTH;
//                         }else if(subopt == 3){
//                             campLevel = Level.TEENAGER;
//                         }else{
//                             System.out.println("Elija una opcion correcta");
//                         }

//                     }while(subopt < 1 || subopt > 3);
                
//                     System.out.println("Introduzca el número máximo de participantes: ");
                
//                     int numMaxParticipants = 0;
//                     do{
//                         try{
//                             max_participants = Integer.parseInt(this.scanner.nextLine());
//                             if(max_participants < 0){
//                                 System.err.println("El número de participantes no puede ser negativo.");
//                                 max_participants = 0;
//                             }
//                         }catch (NumberFormatException e){
//                             System.err.println("El número de participantes debe ser un número entero.");
//                             max_participants = 0;
//                         }
//                     }while(numMaxParticipants != 0);

//                     try {
//                         manager.createCampaments(campId, initDate, finalDate, campLevel, max_participants);
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     System.out.println("\nEl campamento ha sido creado correctamente.");

//                 break;

//                 case 4:
//                     System.out.println("Asociando monitor - actividad...");
//                     ArrayList<ActivityDTO> activities = new ArrayList<>();

//                     try {
//                         activities = manager.getAllActivities();
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     System.out.println("\nActividades disponibles:");

//                     for (int k = 0; k < activities.size(); k++) {
//                         System.out.println(k + ") " + activities.get(k).getname());
//                     }

//                     System.out.print("Selecciona una actividad: ");
//                     int selectedActivityIndex = Integer.parseInt(this.scanner.nextLine());
            
//                     while (!(selectedActivityIndex >= 0 && selectedActivityIndex < activities.size())) {
//                         System.out.println("Índice de actividad no válido. No se asignó ninguna actividad.");
//                         System.out.print("Selecciona una actividad: ");
//                         selectedActivityIndex = Integer.parseInt(this.scanner.nextLine());
//                     }
            
//                     activity = activities.get(selectedActivityIndex);
//                     ArrayList<MonitorDTO> monitors = new ArrayList<>();
                    
//                     try {
//                         monitors = manager.getAllMonitorsNotEspecial();
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     System.out.println("\nMonitores disponibles:");
        
//                     for (int j = 0; j < monitors.size(); j++) {
//                         System.out.println(j + ") " + monitors.get(j).getName() + " " + monitors.get(j).getSurname());
//                     }
        
//                     System.out.print("Selecciona un monitor: ");
//                     int selectedMonitorIndex = Integer.parseInt(this.scanner.nextLine());
                    
//                     while (!(selectedMonitorIndex >= 0 && selectedMonitorIndex < monitors.size()
//                             && monitors.get(selectedMonitorIndex).isEspecial() == false)) {
//                         System.out.println("Índice de monitor no válido.");
//                         System.out.print("Selecciona un monitor: ");
//                         selectedMonitorIndex = Integer.parseInt(this.scanner.nextLine());
//                     }

//                     monitor = monitors.get(selectedMonitorIndex);
                        
//                     try {
//                         manager.associateMonitorsToActivities(monitor.getID(), activity.getname());
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     System.out.println("\nEl monitor ha sido asociado con la actividad correctamente.");

//                 break;
//                 case 5:
//                     System.out.println("Asociando actividad - campamento...");

//                     ArrayList<CampamentDTO> campaments = new ArrayList<>();

//                     try {
//                         campaments = manager.getAllCampaments();
//                         activities = manager.getAllActivities();
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     System.out.println("\nCampamentos disponibles:");

//                     for (int k = 0; k < campaments.size(); k++) {
//                         System.out.println(k + ") " + campaments.get(k).getId());
//                     }

//                     System.out.print("Selecciona un campamento: ");
//                     int selectedCampamentIndex = this.scanner.nextInt();
//                     this.scanner.nextLine();

//                     while (!(selectedCampamentIndex >= 0 && selectedCampamentIndex < campaments.size())) {
//                         System.out.println("\nÍndice del campamento no válido.");
//                         System.out.print("\nSelecciona un campamento:");
//                         selectedCampamentIndex = this.scanner.nextInt();
//                         this.scanner.nextLine();
//                     }

//                     CampamentDTO selectedCampament = campaments.get(selectedCampamentIndex);
//                     Level campamentLevel = selectedCampament.getLevel();
                    
//                     System.out.println("\nActividades disponibles:");

//                     for (int j = 0; j < activities.size(); j++) {
//                         if (activities.get(j).getLevel().equals(campamentLevel)) {
//                             System.out.println(j + ". " + activities.get(j).getname());
//                         }
//                     }

//                     System.out.print("Selecciona una actividad: ");
//                     int selectedActivityIndex2 = this.scanner.nextInt();
//                     this.scanner.nextLine();

//                     while (!(selectedActivityIndex2 >= 0 && selectedActivityIndex2 < activities.size())) {
//                         System.out.println("Índice de actividad no válido.");
//                         System.out.print("Selecciona una actividad:");
//                         selectedActivityIndex2 = this.scanner.nextInt();
//                         this.scanner.nextLine();
//                     }
//                     ActivityDTO selectedActivity = activities.get(selectedActivityIndex2);

//                     try {
//                         manager.associateActivitiesToCampaments(selectedCampament.getId(),selectedActivity.getname());
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     System.out.println("\nLa actividad ha sido asociada al campamento correctamente.");
                
//                 break;

//                 case 6:
//                     System.out.println("Asociando monitor - campamento...");

//                     try {
//                         campaments = manager.getAllCampaments();
//                         monitors = manager.getAllMonitors();
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     System.out.println("\nCampamentos disponibles:");

//                     for (int k = 0; k < campaments.size(); k++) {
//                         System.out.println(k + ") " + "Campamento " + campaments.get(k).getId());
//                     }
            
//                     System.out.print("Selecciona un campamento: ");
//                     int selectedCampamentIndex3 = this.scanner.nextInt();
//                     this.scanner.nextLine();
            
//                     if (selectedCampamentIndex3 > campaments.size()) {
//                         System.out.println("\nÍndice del campamento no válido.");
//                         break;
//                     }
            
//                     CampamentDTO selectedCampament2 = campaments.get(selectedCampamentIndex3);

//                     System.out.println("\nMonitores disponibles:");
            
//                     for (int j = 0; j < monitors.size(); j++) {
//                         System.out.println(j + ". " + monitors.get(j).getName());
//                     }
            
//                     System.out.print("Selecciona un monitor: ");
//                     int selectedMonitorIndex3 = this.scanner.nextInt();
//                     this.scanner.nextLine();
            
//                     while (!(selectedMonitorIndex3 >= 0 && selectedMonitorIndex3 < monitors.size())) {
//                         System.out.println("\nÍndice de monitor no válido.");
//                         System.out.print("\nSelecciona un monitor:");
//                         selectedMonitorIndex = this.scanner.nextInt();
//                         this.scanner.nextLine();
//                     }

//                     MonitorDTO selectedMonitor = monitors.get(selectedMonitorIndex3);

//                     try {
//                         manager.associateMonitorsToCampaments(selectedCampament2.getId(), selectedMonitor.getID());
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     System.out.println("\nEl monitor ha sido asociado al campamento con exito.");

//                 break;

//                 case 7:
//                     System.out.println("Volviendo al menu principal...");
//                 break;

//                 default:
//                     System.out.println("Elija una opcion correcta");
//             }

//         }while(opt != 7);
//     }

//     public void inscriptionsManager() throws Exception{
//         InscriptionsManager manager = new InscriptionsManager();

//         int opt, subopt;
//         do{
//             System.out.println();
//             System.out.println("Elija el tipo de inscripcion a crear:");
//             System.out.println("1) Completa");
//             System.out.println("2) Parcial");
//             System.out.println("3) Cancelar");
//             System.out.print("> ");
//             opt = Integer.parseInt(this.scanner.nextLine());

//             if(opt == 3){
//                 System.out.println("Volviendo al menu principal...");
//                 return;
//             }

//             System.out.println("Creando inscripcion...");
            
//             CampamentsManager campamentsManager = new CampamentsManager();
//             CampamentDTO campament = new CampamentDTO();
//             ArrayList<CampamentDTO> campaments = new ArrayList<>();
//             ArrayList<AssistantDTO> assistants = new ArrayList<>();

//             try {
//                 campaments = campamentsManager.getAllCampaments();
//             } catch (Exception e) {
//                 DisplayException.handleException(e);
//                 break;
//             }

//             System.out.println("\nCampamentos disponibles:");

//             for(CampamentDTO c : campaments)
//                 System.out.println(c);

//             System.out.print("Indique el id del campamento: ");
//             int campId = Integer.parseInt(this.scanner.nextLine());

//             try {
//                 campament = campamentsManager.getById(campId);
//             } catch (Exception e) {
//                 DisplayException.handleException(e);
//                 break;
//             }

//             AssistantManager assistantManager = new AssistantManager();
//             AssistantDTO assistant = new AssistantDTO();

//             try {
//                 assistants = assistantManager.getAll();
//             } catch (Exception e) {
//                 DisplayException.handleException(e);
//                 break;
//             }

//             System.out.println("\nAsistentes disponibles:");
//             for(AssistantDTO a : assistants)
//                 System.out.println(a);
            
//             System.out.print("Indique el id del asistente: ");
//             int assisId = Integer.parseInt(this.scanner.nextLine());

//             try {
//                 assistant = assistantManager.getById(assisId);
//             } catch (Exception e) {
//                 DisplayException.handleException(e);
//                 break;
//             }

//             if(assistant == null){
//                 DisplayException.handleException(new Exception("El asistente con id " + assisId + " no existe."));
//                 break;
//             }

//             Schedule schedule = Schedule.MORNING;
            
//             do{
//                 System.out.println();
//                 System.out.println("Indique el horario:");
//                 System.out.println("1) Mañana");
//                 System.out.println("2) Tarde");
//                 System.out.print("> ");
//                 subopt = Integer.parseInt(this.scanner.nextLine());

//                 if(subopt == 1){
//                     schedule = Schedule.MORNING;
//                 }else if(subopt == 2){
//                     schedule = Schedule.AFTERNOON;
//                 }else{
//                     System.out.println("Elija una opcion correcta");
//                 }

//             }while(subopt != 1 && subopt != 2);
            
            
//             switch(opt){
//                 case 1:
//                     try {
//                         manager.enrollComplete(campament, assistant, schedule);
//                     } catch (Exception e) {
//                         DisplayException.handleException(e);
//                         break;
//                     }

//                     System.out.println("\nInscripcion completa creada con exito.");
//                 break;

//                 case 2:
//                     try {
//                         manager.enrollParcial(campament, assistant);
//                     } catch (Exception e) {
//                         System.err.println("Error: " + e.getMessage());
//                         break;
//                     }

//                     System.out.println("\nInscripcion parcial creada con exito.");
//                 break;

//                 default:
//                     System.out.println("Elija una opcion correcta");
//             }

//         }while(opt != 3);
//     }
// }
