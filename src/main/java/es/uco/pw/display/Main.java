package es.uco.pw.display;

import java.util.Scanner;

import es.uco.pw.business.common.userType.UserType;
import es.uco.pw.business.managers.UserManager;
import es.uco.pw.business.user.UserDTO;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu(scanner);

        //menu.mainMenu();

        UserManager manager = new UserManager();
        UserDTO user = new UserDTO("juan@example.com", "Juan", "password", UserType.ADMIN);
        System.out.println(manager.update(user));
    }

}
