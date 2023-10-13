package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Campaments_Manager{
    
    public void createActivity(List<Activity> activities) throws Exception{
        Scanner scanner = new Scanner(System.in);
        Schendule schendule;
        Level level;
    
      
            System.out.println("Creación de una nueva actividad:");
            System.out.print("Nombre de la actividad: ");
            String name = scanner.nextLine();
    
            System.out.print("Elige una opción de nivel (infantil, juvenil, adolescente): ");
            String opcion2 = scanner.nextLine();
            if (opcion2.equalsIgnoreCase("infantil")) {
                System.out.println("Has elegido la opción 'infantil'.");
                level = Level.CHILD;
            } else if (opcion2.equalsIgnoreCase("juvenil")) {
                System.out.println("Has elegido la opción 'juvenil'.");
                level = Level.YOUTH;
            } else if (opcion2.equalsIgnoreCase("adolescente")) {
                System.out.println("Has elegido la opción 'adolescente'.");
                level = Level.TEENAGER;
            } else {
                System.out.println("Opción no válida. Debes elegir 'infantil', 'juvenil' o 'adolescente'.");
                return;
            }
    
            System.out.print("Elige una opción (mañana/tarde): ");
            String opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("mañana")) {
                System.out.println("Has elegido la opción 'mañana'.");
                schendule = Schendule.MORNING;
            } else if (opcion.equalsIgnoreCase("tarde")) {
                System.out.println("Has elegido la opción 'tarde'.");
                schendule = Schendule.AFTERNOON;
            } else {
                System.out.println("Opción no válida. Debes elegir 'mañana' o 'tarde'.");
                return;
            }
    
            System.out.print("Máximos participantes de la actividad: ");
            int max_participants = scanner.nextInt();
    
            System.out.print("Número de monitores: ");
            int num_monitors = scanner.nextInt();
    
            
    
            Activity newActivity= new Activity(name, level, schendule, max_participants, num_monitors);
            activities.add(newActivity);  
        }

    public void createMonitor(List<Monitor> monitors)throws Exception{
        Scanner scanner = new Scanner(System.in);
    
    
            System.out.println("Creación de un nuevo monitor:");
            System.out.print("ID del monitor: ");
            int id = scanner.nextInt();
            scanner.nextLine();
    
            System.out.print("Nombre del monitor: ");
            String name = scanner.nextLine();
    
            System.out.print("Apellido del monitor: ");
            String surname = scanner.nextLine();
    
            System.out.print("¿Es un monitor de atención especial? (true/false): ");
            boolean isEspecial = scanner.nextBoolean();
    
            Monitor newMonitor = new Monitor(id, name, surname, isEspecial);
            monitors.add(newMonitor);  
    
    }


    public void createCampaments(List<Campament> campaments) throws Exception{
        Scanner scanner = new Scanner(System.in);
    
        
            System.out.println("Creación de un nuevo campamento:");
            System.out.print("ID del campamento: ");
            int id = scanner.nextInt();
    
            System.out.print("Fecha de inicio (AAAA-MM-DD): ");
            String initDateStr = scanner.next();
            LocalDate initDate = LocalDate.parse(initDateStr);
    
            System.out.print("Fecha de finalización (AAAA-MM-DD): ");
            String finalDateStr = scanner.next();
            LocalDate finalDate = LocalDate.parse(finalDateStr);
    
            Campament nuevoCampamento = new Campament(id, initDate, finalDate);
            campaments.add(nuevoCampamento);  
       
    }


    public void associateMonitorsToActivities(List<Activity> activities, List<Monitor> monitors) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Asociación de monitores a actividades:");
        for (Activity activity : activities) {
            System.out.println("Actividad: " + activity.getname());
            System.out.print("Número de monitores a asociar a esta actividad: ");
            
            int numMonitorsNeeded = activity.getNumMonitors();
            System.out.println("Número de monitores a asociar a esta actividad: " + numMonitorsNeeded);

            for (int i = 0; i < numMonitorsNeeded; i++) {
                System.out.println("Selecciona un monitor:");

                // Mostrar la lista de monitores disponibles
                for (int j = 0; j < monitors.size(); j++) {
                    System.out.println(j + ". " + monitors.get(j).getName());
                }

                int selectedMonitorIndex = scanner.nextInt();

                // Asociar el monitor seleccionado a la actividad
                if (selectedMonitorIndex >= 0 && selectedMonitorIndex < monitors.size()) {
                    Monitor selectedMonitor = monitors.get(selectedMonitorIndex);
                    activity.associateMonitor(selectedMonitor);
                } else {
                    System.out.println("Índice de monitor no válido. No se asignó ningún monitor.");
                }
            }
        }

    }
}




