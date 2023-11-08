package es.uco.pw.display;

import java.util.Scanner;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.time.LocalDate;

import es.uco.pw.business.activity.ActivityDTO;
import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.level.Level;
import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.business.managers.InscriptionsManager;
import es.uco.pw.business.monitor.MonitorDTO;
import es.uco.pw.business.schendule.Schendule;
import es.uco.pw.data.dao.inscription.ParcialInscriptionDAO;


public class Main {
    public static void main(String[] args) throws Exception {
        // Properties properties = new Properties();
        // properties.load(new FileInputStream("src/properties.txt"));
        // DataBase DB = new DataBase(properties);
        // Scanner scanner = new Scanner(System.in);

        // ArrayList<MonitorDTO> monitors = DB.importMonitors();
        // ArrayList<ActivityDTO> activities = DB.importActivities(monitors);
        // ArrayList<AssistantDTO> assistants = DB.importAssistants();
        // ArrayList<CampamentDTO> campaments = DB.importCampaments(activities, monitors);
        // ArrayList<CompleteInscriptionDTO> completeInscriptions = DB.importCompleteInscriptions(campaments, assistants);
        // ArrayList<ParcialInscriptionDTO> parcialInscriptions = DB.importParcialInscriptions(campaments, assistants);

        // Menu menu = new Menu(scanner, monitors, activities, assistants, campaments, completeInscriptions, parcialInscriptions);

        // menu.mainMenu();

        // DB.exportMonitors(monitors);
        // DB.exportActivities(activities);
        // DB.exportAssistants(assistants);
        // DB.exportCampaments(campaments);
        // DB.exportCompleteInscriptions(completeInscriptions);
        // DB.exportParcialInscriptions(parcialInscriptions);

        // CampamentDTO campamentDTO = new CampamentDTO(4, LocalDate.of(2024, 9, 15), LocalDate.of(2024, 11, 12), Level.CHILD);
        // AssistantDTO assistantDTO = new AssistantDTO(7, "Mayte", "Alba", LocalDate.of(2003, 9, 15), false);

        // InscriptionsManager iManager = new InscriptionsManager();

        // iManager.enrollParcial(campamentDTO, assistantDTO);

        CampamentsManager cManager = new CampamentsManager();
        cManager.associateMonitorsToActivities(100, "baloncesto");
    }

}
