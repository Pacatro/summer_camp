import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.util.Properties;

import classes.Activity;
import classes.Assistant;
import classes.Campament;
import classes.DataBase;
import classes.Monitor;
import classes.Campaments_Manager;
import classes.DataBase;

public class Main {
    public static void main(String[] args) throws Exception {
        LocalDate now = LocalDate.now();
		LocalDate tomorrow = now.plusDays(1);
		Campament c = new Campament(1, now, tomorrow);
		c.setMaxAssistants(200);
        DataBase DB = new DataBase();
        Properties properties = new Properties(null);
        properties.load(new FileInputStream("src/properties.txt"));

        ArrayList<Monitor> monitors = DB.importMonitors(properties);
        ArrayList<Activity> activities = DB.importActivities(properties, monitors);
        ArrayList<Assistant> assistants = DB.importAssistants(properties);
        ArrayList<Campament> campaments = DB.importCampaments(properties, activities, monitors);
        
        System.out.println(monitors);
        System.out.println();
        System.out.println(activities);
        System.out.println();
        System.out.println(assistants);
        System.out.println();
        System.out.println(campaments);

        

        Campaments_Manager Manager = new Campaments_Manager();
        
        /*while (true) {
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
        }*/
    
        DB.exportMonitors(properties, monitors);
        DB.exportActivities(properties, activities);
        DB.exportAssistants(properties, assistants);
        DB.exportCampaments(properties, campaments);
    
    
    }


      
}
    
    