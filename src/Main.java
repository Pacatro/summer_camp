import java.util.ArrayList;

import classes.DataBase;
import classes.Monitor;


public class Main {
    public static void main(String[] args) throws Exception {
        DataBase DB = new DataBase();

        ArrayList<Monitor> monitors = DB.importMonitors();

        System.out.println(monitors);

    }
}
