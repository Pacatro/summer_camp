package display;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.util.Properties;

import business.activity.ActivityDTO;
import business.assistant.AssistantDTO;
import business.campament.CampamentDTO;
import business.monitor.MonitorDTO;
import data.database.DataBase;
import business.factory.CompleteInscriptionDTO;
import business.factory.ParcialInscriptionDTO;


public class Main {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/properties.txt"));
        DataBase DB = new DataBase(properties);
        Scanner scanner = new Scanner(System.in);

        ArrayList<MonitorDTO> monitors = DB.importMonitors();
        ArrayList<ActivityDTO> activities = DB.importActivities(monitors);
        ArrayList<AssistantDTO> assistants = DB.importAssistants();
        ArrayList<CampamentDTO> campaments = DB.importCampaments(activities, monitors);
        ArrayList<CompleteInscriptionDTO> completeInscriptions = DB.importCompleteInscriptions(campaments, assistants);
        ArrayList<ParcialInscriptionDTO> parcialInscriptions = DB.importParcialInscriptions(campaments, assistants);

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
