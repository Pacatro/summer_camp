package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

public class DataBase {
    public ArrayList<Monitor> importMonitors() throws Exception{
        Properties properties = new Properties(null);
        properties.load(new FileInputStream("src/properties.txt"));
        
        BufferedReader file = new BufferedReader(new FileReader(new File(properties.getProperty("monitors"))));

        ArrayList<Monitor> monitors = new ArrayList<Monitor>();

        String line;

        while((line = file.readLine()) != null){
            String[] elements = line.split(" ");

            Monitor auxMonitor = new Monitor();

            auxMonitor.setID(Integer.parseInt(elements[0]));
            auxMonitor.setName(elements[1]);
            auxMonitor.setSurname(elements[2]);
            auxMonitor.setisEspecial(Boolean.parseBoolean(elements[3]));

            monitors.add(auxMonitor);
        }

        file.close();

        return monitors;
        
    }

    public ArrayList<Activity> importActivities(ArrayList<Monitor> monitors) throws Exception{
        Properties properties = new Properties(null);
        properties.load(new FileInputStream("src/properties.txt"));
        
        BufferedReader file = new BufferedReader(new FileReader(new File(properties.getProperty("activities"))));

        ArrayList<Activity> activities = new ArrayList<Activity>();

        String line;

        while((line = file.readLine()) != null){
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
                boolean flag = false;
                for(int j = 0; j < monitors.size() && !flag; j++){
                    if(monitors.get(j).getID() == (Integer.parseInt(elements[i]))){
                        auxActivity.associateMonitor(monitors.get(j));
                        flag = true;
                    }
                }
            }
            
            activities.add(auxActivity);
        }

        file.close();

        return activities;

    }

    public ArrayList<Assistant> importAssistants() throws Exception{
        Properties properties = new Properties(null);
        properties.load(new FileInputStream("src/properties.txt"));

        BufferedReader file = new BufferedReader(new FileReader(new File(properties.getProperty("assistants"))));

        ArrayList<Assistant> assistants = new ArrayList<Assistant>();

        String line;

        while((line = file.readLine()) != null){
            String[] elements = line.split(" ");

            Assistant auxAssistant = new Assistant();

            auxAssistant.setId(Integer.parseInt(elements[0]));
            auxAssistant.setName(elements[1]);
            auxAssistant.setSurname(elements[2]);
            //No se cómo se escribe la fecha en un fichero
            auxAssistant.setAtention(Boolean.parseBoolean(elements[4]));

            assistants.add(auxAssistant);
        }

        file.close();

        return assistants;

    }

    public ArrayList<Campament> importCampaments(ArrayList<Activity> activities, ArrayList<Monitor> monitors) throws Exception{
        Properties properties = new Properties(null);
        properties.load(new FileInputStream("src/properties.txt"));

        BufferedReader file = new BufferedReader(new FileReader(new File(properties.getProperty("campaments"))));

        ArrayList<Campament> campaments = new ArrayList<Campament>();

        String line;

        while((line = file.readLine()) != null){
            String[] elements = line.split(" ");

            Campament auxCampament = new Campament();

            auxCampament.setId(Integer.parseInt(elements[0]));
            auxCampament.setInitDate(LocalDate.now());
            auxCampament.setFinalDate(LocalDate.now());
            auxCampament.setMaxAssistants(Integer.parseInt(elements[3]));

            if(elements[4].equals("CHILD")){
                auxCampament.setLevel(Level.CHILD);
            }else if(elements[4].equals("YOUTH")){
                auxCampament.setLevel(Level.YOUTH);
            }else if(elements[4].equals("TEENAGER")){
                auxCampament.setLevel(Level.TEENAGER);
            }

            int i = 5;

            while(i < elements.length && !elements[i].equals(",")){
                boolean flag = false;
                for(int j = 0; j < activities.size() && !flag; j++){
                    if(elements[i].equals(activities.get(j).getname())){
                        auxCampament.associateActivity(activities.get(j));
                        flag = true;
                    }
                }

                i++;
            }

            i++;

            while(i < elements.length && !(auxCampament.getActivities().isEmpty())){
                boolean flag = false;
                for(int j = 0; j < monitors.size() && !flag; j++){
                    if((Integer.parseInt(elements[i])) == (monitors.get(j).getID())){
                        auxCampament.associateMonitor(monitors.get(j));
                        flag = true;
                    }
                }

                i++;
            }

            campaments.add(auxCampament);

        }

        file.close();

        return campaments;
    }
}
