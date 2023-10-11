import classes.Campament;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties(null);
        properties.load(new FileInputStream("src/properties.txt"));

        BufferedReader file = new BufferedReader(new FileReader(new File(properties.getProperty("activities"))));

        String line;

        while((line = file.readLine()) != null){
            System.out.println(line);
        }
        
    }
}
