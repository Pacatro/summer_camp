package es.uco.pw.display;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu(scanner);

        menu.mainMenu();

        // menu.mainMenu();

        // DB.exportMonitors(monitors);
        // DB.exportActivities(activities);
        // DB.exportAssistants(assistants);
        // DB.exportCampaments(campaments);
        // DB.exportCompleteInscriptions(completeInscriptions);
        // DB.exportParcialInscriptions(parcialInscriptions);

        /*CampamentDTO campamentDTO = new CampamentDTO(4, LocalDate.of(2024, 9, 15), LocalDate.of(2024, 11, 12), Level.CHILD);
        AssistantDTO assistantDTO = new AssistantDTO(7, "Mayte", "Alba", LocalDate.of(2003, 9, 15), false);

        InscriptionsManager iManager = new InscriptionsManager();

        iManager.enrollParcial(campamentDTO, assistantDTO);

        CampamentsManager cManager = new CampamentsManager();
        cManager.createActivity("Baloncesto", Level.CHILD, Schedule.MORNING, 20, 10);*/
    }

}
