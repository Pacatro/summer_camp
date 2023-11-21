package es.uco.pw.display;

import java.util.Scanner;

import es.uco.pw.business.common.userType.UserType;
import es.uco.pw.business.user.UserDTO;
import es.uco.pw.data.dao.user.UserDAO;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu(scanner);

        //menu.mainMenu();

        UserDTO user = new UserDTO("juan@example.com", "Juan", "password789", UserType.ASSISTANT);

        UserDAO dao = new UserDAO();

        dao.insert(user);
    }

}
