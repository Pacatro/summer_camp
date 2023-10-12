import java.util.ArrayList;

import classes.Activity;
import classes.Assistant;
import classes.Campament;
import classes.DataBase;
import classes.Monitor;


public class Main {
    public static void main(String[] args) throws Exception {
        DataBase DB = new DataBase();

        ArrayList<Monitor> monitors = DB.importMonitors();
        ArrayList<Activity> activities = DB.importActivities(monitors);
        ArrayList<Assistant> assistants = DB.importAssistants();
        ArrayList<Campament> campaments = DB.importCampaments(activities, monitors);
        
        System.out.println(monitors);
        System.out.println();
        System.out.println(activities);
        System.out.println();
        System.out.println(assistants);
        System.out.println();
        System.out.println(campaments);

    }
}