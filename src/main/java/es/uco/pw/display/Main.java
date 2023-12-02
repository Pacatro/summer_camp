package es.uco.pw.display;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.common.userType.UserType;
import es.uco.pw.business.managers.AssistantManager;
import es.uco.pw.business.managers.UserManager;
import es.uco.pw.business.user.UserDTO;

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
        ArrayList<CampamentDTO> list = manager.getCampaments("pedro@example.com");

        for(CampamentDTO c : list)
            System.out.println(c.getId());

        System.out.println(list.size());
    }

}
