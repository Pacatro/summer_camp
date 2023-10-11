import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;
import classes.Activity;
import classes.Level;
import classes.Schendule;
import classes.Monitor;


public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<Activity> activities = new ArrayList<Activity>();
        ArrayList<Monitor> monitors = new ArrayList<Monitor>();

        Properties properties = new Properties(null);
        properties.load(new FileInputStream("src/properties.txt"));

        BufferedReader file_act = new BufferedReader(new FileReader(new File(properties.getProperty("activities"))));
        BufferedReader file_mon = new BufferedReader(new FileReader(new File(properties.getProperty("monitors"))));
        
        String line;

        while((line = file_mon.readLine()) != null){
            String[] elementos = line.split(" ");

            Monitor auxMonitor = new Monitor();

            auxMonitor.setID(Integer.parseInt(elementos[0]));
            auxMonitor.setName(elementos[1]);
            auxMonitor.setSurname(elementos[2]);
            auxMonitor.setisEspecial(Boolean.parseBoolean(elementos[3]));

            monitors.add(auxMonitor);
        }

        while((line = file_act.readLine()) != null){
            String[] elementos = line.split(" ");
            
            Activity auxActivity = new Activity();
            auxActivity.setname(elementos[0]);
            auxActivity.setMaxParticipants(Integer.parseInt(elementos[3]));
            auxActivity.setNumMonitors(Integer.parseInt(elementos[4]));

            if(elementos[1].equals("CHILD")){
                auxActivity.setLevel(Level.CHILD);
            }else if(elementos[1].equals("YOUTH")){
                auxActivity.setLevel(Level.YOUTH);
            }else if(elementos[1].equals("TEENAGER")){
                auxActivity.setLevel(Level.TEENAGER);
            }

            if(elementos[2].equals("MORNING")){
                auxActivity.setSchendule(Schendule.MORNING);
            }else if(elementos[2].equals("AFTERNOON")){
                auxActivity.setSchendule(Schendule.AFTERNOON);
            }

            for(int i = 5; i < elementos.length; i++){
                for(Monitor monitor: monitors){
                    if(monitor.getID() == (Integer.parseInt(elementos[i]))){
                        auxActivity.associateMonitor(monitor);
                    }
                }
            }
            
            activities.add(auxActivity);
        }
        
        System.out.println(monitors);
        System.out.println(activities);


        file_act.close();
        file_mon.close();

    }
}
