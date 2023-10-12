import classes.Campament;
import classes.Monitor;
import classes.Schendule;
import factory.CompleteInscriptionFactory;
import factory.EarlyRegInscription;
import classes.Campaments_Manager;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import classes.Activity;
import classes.Level;

public class Main {
    public static void main(String[] args) throws Exception {
        /*LocalDate now = LocalDate.now();
		LocalDate tomorrow = now.plusDays(1);
		Campament c = new Campament(1, now, tomorrow);
		c.setMaxAssistants(200);
        
        Monitor m = new Monitor(1, "Paco", "Algar", false);
        ArrayList <Monitor> monitors = new ArrayList<Monitor>();        
        ArrayList <Activity> activities = new ArrayList<Activity>();

        Activity a = new Activity("A1", Level.CHILD, Schendule.AFTERNOON, 1001, 1);
        a.associateMonitor(m);
        activities.add(a);

        monitors.add(m);        
        monitors.add(m);

        c.setLevel(Level.CHILD);

        c.associateActivity(a);

        c.associateMonitor(m);

        CompleteInscriptionFactory completeFactory = new CompleteInscriptionFactory();
        EarlyRegInscription e = completeFactory.createEarlyRegInscription();
        System.out.println(e);*/

        List<Activity> activities = new ArrayList<>();
        Campaments_Manager Manager = new Campaments_Manager();
        
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Menú:");
            System.out.println("1. Crear una actividad");
            System.out.println("2. Mostrar actividades");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                Manager.createActivity(activities);
                    break;
                case 2:
                    System.out.println("Lista de actividades:");
                    for (Activity actividad : activities) {
                        System.out.println(actividad);
                    }
                    break;
                case 3:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }
}
    
