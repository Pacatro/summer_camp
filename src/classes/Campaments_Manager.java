package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Campaments_Manager{
    
    public Activity createActivity() {
        
        Scanner scanner = new Scanner(System.in);
        Schendule schendule;
        Level level;

        System.out.println("Creación de una nueva actividad:");
        System.out.print("Nombre de la actividad: ");
        String name = scanner.nextLine();

        System.out.print("Elige una opción (infantil, juvenil, adolescente). ");
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
            return null;
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
            return null;
        }

        System.out.print("Nivel de la actividad: ");
        int max_participants = scanner.nextInt();

        System.out.print("Nivel de la actividad: ");
        int num_monitors = scanner.nextInt();

        scanner.nextLine(); 

        return new Activity (name, level, schendule, max_participants, num_monitors);
    }

    public Monitor createMonitor() {
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

        return new Monitor(id, name, surname, isEspecial);
    
    }

    public Campament createCampament() {
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

        return new Campament(id, initDate, finalDate);
    }

    public void associateMonitorToActivities(Activity actividad) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("¿Cuántos monitores deseas asociar a esta actividad? ");
        int numMonitores = scanner.nextInt();

        if (numMonitores > 0) {
            if (numMonitores <= actividad.getNumMonitors()) {
                for (int i = 0; i < numMonitores; i++) {
                    Monitor monitor = createMonitor();
                    actividad.associateMonitor(monitor);
                }
                System.out.println("Monitores asociados con éxito.");
            } else {
                System.out.println("No puedes asociar más monitores de los permitidos.");
            }
        } else {
            System.out.println("Número de monitores no válido.");
        }
    }

    

}

