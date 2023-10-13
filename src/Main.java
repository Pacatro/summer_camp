import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import classes.Activity;
import classes.Assistant;
import classes.Campament;
import classes.DataBase;
import classes.Monitor;
import factory.CompleteInscription;
import factory.ParcialInscription;


public class Main {
    public static void main(String[] args) throws Exception {
        DataBase DB = new DataBase();
        Properties properties = new Properties(null);
        properties.load(new FileInputStream("src/properties.txt"));

        ArrayList<Monitor> monitors = DB.importMonitors(properties);
        ArrayList<Activity> activities = DB.importActivities(properties, monitors);
        ArrayList<Assistant> assistants = DB.importAssistants(properties);
        ArrayList<Campament> campaments = DB.importCampaments(properties, activities, monitors);
        ArrayList<CompleteInscription> completeInscriptions = DB.importCompleteInscriptions(properties, campaments, assistants);
        ArrayList<ParcialInscription> parcialInscriptions = DB.importParcialInscriptions(properties, campaments, assistants);
        
        System.out.println(monitors);
        System.out.println();
        System.out.println(activities);
        System.out.println();
        System.out.println(assistants);
        System.out.println();
        System.out.println(campaments);
        System.out.println();
        System.out.println(completeInscriptions);
        System.out.println();
        System.out.println(parcialInscriptions);

        DB.exportMonitors(properties, monitors);
        DB.exportActivities(properties, activities);
        DB.exportAssistants(properties, assistants);
        DB.exportCampaments(properties, campaments);

    }
}