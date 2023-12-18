package es.uco.pw.display;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.Properties;

import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.common.level.Level;
import es.uco.pw.business.managers.AssistantManager;
import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.business.managers.InscriptionsManager;
import es.uco.pw.business.managers.AssistantManager;

public class Main {
    public static void main(String[] args) throws Exception {
        // Scanner scanner = new Scanner(System.in);

        //Menu menu = new Menu(scanner);

        //menu.mainMenu();

        Properties sql_properties = new Properties();
        sql_properties.load(new FileInputStream("src/main/webapp/WEB-INF/sql.properties"));
        Properties config_properties = new Properties();
        config_properties.load(new FileInputStream("src/main/webapp/WEB-INF/config.properties"));

        // UserManager manager = new UserManager(sql_properties, config_properties);
        // UserDTO user = new UserDTO("juan@example.com", "Pepe", "password", UserType.ADMIN);
        // System.out.println(manager.update(user));

        AssistantManager manager = new AssistantManager(sql_properties, config_properties);

        AssistantDTO assistantDTO = new AssistantDTO(50, "paco", "algar", LocalDate.now(), false, "paco@paco.com");
        CampamentDTO campamentDTO = new CampamentDTO(67, LocalDate.of(2026, 10, 1), LocalDate.of(2027, 10, 1), Level.TEENAGER);

        manager.register(assistantDTO);
        CampamentsManager campamentsManager = new CampamentsManager(sql_properties, config_properties);
        campamentsManager.createCampaments(67, LocalDate.of(2026, 10, 1), LocalDate.of(2027, 10, 1), Level.TEENAGER, 100);

        InscriptionsManager inscriptionsManager = new InscriptionsManager(sql_properties, config_properties);

        inscriptionsManager.enrollParcial(campamentDTO, assistantDTO);

        System.out.println("oK");

        inscriptionsManager.cancelParcial(campamentDTO, assistantDTO);
        System.out.println("cancelled");
    }

}
