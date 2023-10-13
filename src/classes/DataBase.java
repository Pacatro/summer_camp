package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import factory.CompleteInscription;
import factory.ParcialInscription;
import factory.EarlyRegInscriptionFactory;
import factory.LateRegInscriptionFactory;


/**
 * Handle the operations dedicated to the files that will be used as a database. 
 */
public class DataBase {
    public Properties properties;

    public DataBase(Properties properties) {this.properties = properties;}

    public ArrayList<Monitor> importMonitors() throws Exception{
        BufferedReader file = new BufferedReader(new FileReader(new File(this.properties.getProperty("monitors"))));

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
        BufferedReader file = new BufferedReader(new FileReader(new File(this.properties.getProperty("activities"))));

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
        BufferedReader file = new BufferedReader(new FileReader(new File(this.properties.getProperty("assistants"))));

        ArrayList<Assistant> assistants = new ArrayList<Assistant>();

        String line;

        while((line = file.readLine()) != null){
            String[] elements = line.split(" ");

            Assistant auxAssistant = new Assistant();

            auxAssistant.setId(Integer.parseInt(elements[0]));
            auxAssistant.setName(elements[1]);
            auxAssistant.setSurname(elements[2]);
            auxAssistant.setDate(LocalDate.parse(elements[3]));
            auxAssistant.setAtention(Boolean.parseBoolean(elements[4]));

            assistants.add(auxAssistant);
        }

        file.close();

        return assistants;

    }

    public ArrayList<Campament> importCampaments(ArrayList<Activity> activities, ArrayList<Monitor> monitors) throws Exception{
        BufferedReader file = new BufferedReader(new FileReader(new File(this.properties.getProperty("campaments"))));

        ArrayList<Campament> campaments = new ArrayList<Campament>();

        String line;

        while((line = file.readLine()) != null){
            String[] elements = line.split(" ");

            Campament auxCampament = new Campament();

            auxCampament.setId(Integer.parseInt(elements[0]));
            auxCampament.setInitDate(LocalDate.parse(elements[1]));
            auxCampament.setFinalDate(LocalDate.parse(elements[2]));
            auxCampament.setMaxAssistants(Integer.parseInt(elements[3]));

            if(elements[4].equals("CHILD")){
                auxCampament.setLevel(Level.CHILD);
            }else if(elements[4].equals("YOUTH")){
                auxCampament.setLevel(Level.YOUTH);
            }else if(elements[4].equals("TEENAGER")){
                auxCampament.setLevel(Level.TEENAGER);
            }

            int i = 5;

            while(i < elements.length && !elements[i].equals("/")){
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

    public ArrayList<CompleteInscription> importCompleteInscriptions(ArrayList<Campament> campaments, ArrayList<Assistant> assistants) throws Exception{
        BufferedReader file = new BufferedReader(new FileReader(new File(this.properties.getProperty("completeinscriptions"))));

        ArrayList<CompleteInscription> inscriptions = new ArrayList<CompleteInscription>();

        String line;

        while((line = file.readLine()) != null){
            String elements[] = line.split(" ");

            CompleteInscription auxCompleteInscription;

            boolean flag = false;
            Campament auxCampament = new Campament();
            for(int i = 0; i < campaments.size() && !flag; i++){
                if(campaments.get(i).getId() == (Integer.parseInt(elements[1]))){
                    auxCampament = campaments.get(i);
                    flag = true;
                }
            }

            flag = false;
            Assistant auxAssistant = new Assistant();
            for(int i = 0; i < assistants.size() && !flag; i++){
                if(assistants.get(i).getId() == (Integer.parseInt(elements[0]))){
                    auxAssistant = assistants.get(i);
                    flag = true;
                }
            }

            Schendule auxSchendule = Schendule.MORNING;
            if(elements[5].equals("MORNING")){
                auxSchendule = Schendule.MORNING;
            }else if(elements[5].equals("AFTERNOON")){
                auxSchendule = Schendule.AFTERNOON;
            }

            int dif = (auxCampament.getInitDate().compareTo(LocalDate.parse(elements[2])));

            if(dif > 15){
                EarlyRegInscriptionFactory eFactory = new EarlyRegInscriptionFactory();

                auxCompleteInscription = eFactory.createCompleteInscription(auxCampament, auxAssistant, auxSchendule, LocalDate.parse(elements[2]));
            }else{
                LateRegInscriptionFactory lFactory = new LateRegInscriptionFactory();

                auxCompleteInscription = lFactory.createCompleteInscription(auxCampament, auxAssistant, auxSchendule, LocalDate.parse(elements[2]));
            }

            auxCompleteInscription.setPrice(Double.parseDouble(elements[3]));

            inscriptions.add(auxCompleteInscription);
        }

        return inscriptions;
    }

    public ArrayList<ParcialInscription> importParcialInscriptions(ArrayList<Campament> campaments, ArrayList<Assistant> assistants) throws Exception{
        BufferedReader file = new BufferedReader(new FileReader(new File(this.properties.getProperty("parcialinscriptions"))));

        ArrayList<ParcialInscription> inscriptions = new ArrayList<ParcialInscription>();

        String line;

        while((line = file.readLine()) != null){
            String elements[] = line.split(" ");

            ParcialInscription auxParcialInscription;

            boolean flag = false;
            Campament auxCampament = new Campament();
            for(int i = 0; i < campaments.size() && !flag; i++){
                if(campaments.get(i).getId() == (Integer.parseInt(elements[1]))){
                    auxCampament = campaments.get(i);
                    flag = true;
                }
            }

            flag = true;
            Assistant auxAssistant = new Assistant();
            for(int i = 0; i < assistants.size() && !flag; i++){
                if(assistants.get(i).getId() == (Integer.parseInt(elements[0]))){
                    auxAssistant = assistants.get(i);
                    flag = true;
                }
            }

            int dif = (auxCampament.getInitDate().compareTo(LocalDate.parse(elements[2])));

            if(dif > 15){
                EarlyRegInscriptionFactory eFactory = new EarlyRegInscriptionFactory();

                auxParcialInscription = eFactory.createParcialInscription(auxCampament, auxAssistant, LocalDate.parse(elements[2]));
            }else{
                LateRegInscriptionFactory lFactory = new LateRegInscriptionFactory();

                auxParcialInscription = lFactory.createParcialInscription(auxCampament, auxAssistant, LocalDate.parse(elements[2]));
            }

            auxParcialInscription.setPrice(Double.parseDouble(elements[3]));

            inscriptions.add(auxParcialInscription);
        }

        return inscriptions;
    }

    public void exportMonitors(ArrayList<Monitor> monitors) throws Exception{
        BufferedWriter file = new BufferedWriter(new FileWriter(new File(this.properties.getProperty("monitors"))));

        for(Monitor monitor: monitors){
            file.write(monitor.getID() + " " + monitor.getName() + " " + monitor.getSurname() + " " + monitor.isEspecial() + "\n");
        }

        file.close();
    }

    public void exportActivities(ArrayList<Activity> activities) throws Exception{
        BufferedWriter file = new BufferedWriter(new FileWriter(new File(this.properties.getProperty(("activities")))));

        for(Activity activity: activities){
            String line = activity.getname() + " " + activity.getLevel() + " " + activity.getSchendule() + " " + activity.getMaxParticipants() + " " + activity.getNumMonitors();

            for(Monitor monitor: activity.getMonitors()){
                line += (" " + (monitor.getID()));
            }
            
            file.write(line + "\n");
        }

        file.close();
    }

    public void exportAssistants(ArrayList<Assistant> assistants) throws Exception{
        BufferedWriter file = new BufferedWriter(new FileWriter(new File(this.properties.getProperty("assistants"))));

        for(Assistant assistant: assistants){
            file.write(assistant.getId() + " " + assistant.getName() + " " + assistant.getSurname() + " " + assistant.getDate() + " " + assistant.getAtention() + "\n");
        }

        file.close();
    }

    public void exportCampaments(ArrayList<Campament> campaments) throws Exception{
        BufferedWriter file = new BufferedWriter(new FileWriter(new File(this.properties.getProperty("campaments"))));

        for(Campament campament: campaments){
            String line = campament.getId() + " " + campament.getInitDate() + " " + campament.getFinalDate() + " " + campament.getMaxAssistants() + " " + campament.getLevel();

            for(Activity activity: (campament.getActivities())){
                line += (" " + activity.getname());
            }

            line += " /";

            for(Monitor monitor: (campament.getMonitors())){
                line += (" " + monitor.getID());
            }

            file.write(line + "\n");
        }

        file.close();
    }

    public void exportCompleteInscriptions(ArrayList<CompleteInscription> inscriptions) throws Exception{
        BufferedWriter file = new BufferedWriter(new FileWriter(new File(this.properties.getProperty("completeinscriptions"))));

        for(CompleteInscription inscription: inscriptions){
            file.write(inscription.getIdParticipant() + " " + inscription.getIdCampament() + " " + inscription.getDate() + " " + inscription.getPrice() + " " + inscription.getCancellation() + " " + inscription.getSchendule() + "\n");
        }

        file.close();
    }

    public void exportParcialInscriptions(ArrayList<ParcialInscription> inscriptions) throws Exception{
        BufferedWriter file = new BufferedWriter(new FileWriter(new File(this.properties.getProperty("parcialinscriptions"))));

        for(ParcialInscription inscription: inscriptions){
            file.write(inscription.getIdParticipant() + " " + inscription.getIdCampament() + " " + inscription.getDate() + " " + inscription.getPrice() + " " + inscription.getCancellation() + " " + inscription.getSchendule() + "\n");
        }

        file.close();
    }

    
}
