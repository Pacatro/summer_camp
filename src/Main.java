import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import classes.Activity;
import classes.Assistant;
import classes.Campament;
import classes.DataBase;
import classes.Monitor;


public class Main {
    public static void main(String[] args) throws Exception {
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

        DB.exportMonitors(properties, monitors);
        DB.exportActivities(properties, activities);
        DB.exportAssistants(properties, assistants);
        DB.exportCampaments(properties, campaments);

    }
}