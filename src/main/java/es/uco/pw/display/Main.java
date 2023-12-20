package es.uco.pw.display;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.Properties;

import es.uco.pw.business.managers.CampamentsManager;

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

        // CampamentsManager camp = new CampamentsManager(sql_properties, config_properties);
        // ArrayList<Integer> campaments = new ArrayList<Integer>();
        // ArrayList<Integer> num_incrip_c = new ArrayList<Integer>();
        // ArrayList<Integer> num_incrip_p = new ArrayList<Integer>();

        //     camp.getNumInscriptionsAll(campaments, num_incrip_c, num_incrip_p);

        //     for(int i = 0; i < campaments.size(); i++){
        //         System.out.println(campaments.get(i) + " " + num_incrip_c.get(i) + " " + num_incrip_p.get(i));
        //     }
    }

}
