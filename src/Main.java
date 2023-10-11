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
import classes.Assistant;


public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<Activity> activities = new ArrayList<Activity>();
        ArrayList<Monitor> monitors = new ArrayList<Monitor>();
        ArrayList<Assistant> assistants = new ArrayList<Assistant>();

        Properties properties = new Properties(null);
        properties.load(new FileInputStream("src/properties.txt"));

        BufferedReader file_act = new BufferedReader(new FileReader(new File(properties.getProperty("activities"))));
        BufferedReader file_mon = new BufferedReader(new FileReader(new File(properties.getProperty("monitors"))));
        BufferedReader file_asis = new BufferedReader(new FileReader(new File(properties.getProperty("assistants"))));

        String line;

        while((line = file_mon.readLine()) != null){
            String[] elements = line.split(" ");

            Monitor auxMonitor = new Monitor();

            auxMonitor.setID(Integer.parseInt(elements[0]));
            auxMonitor.setName(elements[1]);
            auxMonitor.setSurname(elements[2]);
            auxMonitor.setisEspecial(Boolean.parseBoolean(elements[3]));

            monitors.add(auxMonitor);
        }

        while((line = file_act.readLine()) != null){
            String[] elements = line.split(" ");
            
            Activity auxActivity = new Activity();
            auxActivity.setname(elements[0]);
            auxActivity.setMaxParticipants(Integer.parseInt(elements[3]));
            auxActivity.setNumMonitors(Integer.parseInt(elements[4]));

            if(elements[1].equals("CHILD")){
                auxActivity.setLevel(Level.CHILD);
            }else if(elements[1].equals("YOUTH")){
                auxActivity.setLevel(Level.YOUTH);
            }else if(elements[1].equals("TEENAGER")){
                auxActivity.setLevel(Level.TEENAGER);
            }

            if(elements[2].equals("MORNING")){
                auxActivity.setSchendule(Schendule.MORNING);
            }else if(elements[2].equals("AFTERNOON")){
                auxActivity.setSchendule(Schendule.AFTERNOON);
            }

            for(int i = 5; i < elements.length; i++){
                for(Monitor monitor: monitors){
                    if(monitor.getID() == (Integer.parseInt(elements[i]))){
                        auxActivity.associateMonitor(monitor);
                    }
                }
            }
            
            activities.add(auxActivity);
        }

        while((line = file_asis.readLine()) != null){
            String[] elements = line.split(" ");

            Assistant auxAssistant = new Assistant();

            auxAssistant.setId(Integer.parseInt(elements[0]));
            auxAssistant.setName(elements[1]);
            auxAssistant.setSurname(elements[2]);
            //No se cómo se escribe la fecha en un fichero
            auxAssistant.setAtention(Boolean.parseBoolean(elements[4]));

            assistants.add(auxAssistant);
        }
        
        System.out.println(monitors);
        System.out.println(activities);
        System.out.println(assistants);


        file_act.close();
        file_mon.close();
        file_asis.close();

    }
}
