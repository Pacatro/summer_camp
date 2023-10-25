import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.util.Properties;

import classes.Activity;
import classes.Assistant;
import classes.Campament;
import classes.Monitor;
import data.DataBase;
import factory.CompleteInscription;
import factory.ParcialInscription;
import menu.Menu;


public class Main {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/properties.txt"));
        DataBase DB = new DataBase(properties);
        Scanner scanner = new Scanner(System.in);

        ArrayList<Monitor> monitors = DB.importMonitors();
        ArrayList<Activity> activities = DB.importActivities(monitors);
        ArrayList<Assistant> assistants = DB.importAssistants();
        ArrayList<Campament> campaments = DB.importCampaments(activities, monitors);
        ArrayList<CompleteInscription> completeInscriptions = DB.importCompleteInscriptions(campaments, assistants);
        ArrayList<ParcialInscription> parcialInscriptions = DB.importParcialInscriptions(campaments, assistants);

        Menu menu = new Menu(scanner, monitors, activities, assistants, campaments, completeInscriptions, parcialInscriptions);

        menu.mainMenu();

        DB.exportMonitors(monitors);
        DB.exportActivities(activities);
        DB.exportAssistants(assistants);
        DB.exportCampaments(campaments);
        DB.exportCompleteInscriptions(completeInscriptions);
        DB.exportParcialInscriptions(parcialInscriptions);

    }

}
