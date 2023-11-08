package es.uco.pw.data.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.business.activity.ActivityDTO;
import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.monitor.MonitorDTO;
import es.uco.pw.business.level.Level;
import es.uco.pw.business.schendule.Schedule;
import es.uco.pw.business.factory.CompleteInscriptionDTO;
import es.uco.pw.business.factory.ParcialInscriptionDTO;
import es.uco.pw.business.factory.EarlyRegInscriptionFactory;
import es.uco.pw.business.factory.LateRegInscriptionFactory;


/**
 * Handle the operations dedicated to the files that will be used as a database. 
 */
public class DataBase {
    public Properties properties;

    public DataBase(Properties properties) {this.properties = properties;}

    public ArrayList<MonitorDTO> importMonitors() throws Exception{
        BufferedReader file = new BufferedReader(new FileReader(new File(this.properties.getProperty("monitors"))));

        ArrayList<MonitorDTO> monitors = new ArrayList<MonitorDTO>();

        String line;

        while((line = file.readLine()) != null){
            String[] elements = line.split(" ");

            MonitorDTO auxMonitor = new MonitorDTO();

            auxMonitor.setID(Integer.parseInt(elements[0]));
            auxMonitor.setName(elements[1]);
            auxMonitor.setSurname(elements[2]);
            auxMonitor.setisEspecial(Boolean.parseBoolean(elements[3]));

            monitors.add(auxMonitor);
        }

        file.close();

        return monitors;
        
    }

    public ArrayList<ActivityDTO> importActivities(ArrayList<MonitorDTO> monitors) throws Exception{
        BufferedReader file = new BufferedReader(new FileReader(new File(this.properties.getProperty("activities"))));

        ArrayList<ActivityDTO> activities = new ArrayList<ActivityDTO>();

        // String line;

        // while((line = file.readLine()) != null){
        //     String[] elements = line.split(" ");
            
        //     ActivityDTO auxActivity = new ActivityDTO();
        //     auxActivity.setname(elements[0]);
        //     auxActivity.setMaxParticipants(Integer.parseInt(elements[3]));
        //     auxActivity.setNumMonitors(Integer.parseInt(elements[4]));

        //     if(elements[1].equals("CHILD")){
        //         auxActivity.setLevel(Level.CHILD);
        //     }else if(elements[1].equals("YOUTH")){
        //         auxActivity.setLevel(Level.YOUTH);
        //     }else if(elements[1].equals("TEENAGER")){
        //         auxActivity.setLevel(Level.TEENAGER);
        //     }

        //     if(elements[2].equals("MORNING")){
        //         auxActivity.setSchendule(Schendule.MORNING);
        //     }else if(elements[2].equals("AFTERNOON")){
        //         auxActivity.setSchendule(Schendule.AFTERNOON);
        //     }

        //     for(int i = 5; i < elements.length; i++){
        //         boolean flag = false;
        //         for(int j = 0; j < monitors.size() && !flag; j++){
        //             if(monitors.get(j).getID() == (Integer.parseInt(elements[i]))){
        //                 auxActivity.associateMonitor(monitors.get(j)); //TODO hacer funcion en DAO
        //                 flag = true;
        //             }
        //         }
        //     }
            
        //     activities.add(auxActivity);
        // }

        // file.close();

        return activities;

    }

    public ArrayList<AssistantDTO> importAssistants() throws Exception{
        BufferedReader file = new BufferedReader(new FileReader(new File(this.properties.getProperty("assistants"))));

        ArrayList<AssistantDTO> assistants = new ArrayList<AssistantDTO>();

        String line;

        while((line = file.readLine()) != null){
            String[] elements = line.split(" ");

            AssistantDTO auxAssistant = new AssistantDTO();

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

    public ArrayList<CampamentDTO> importCampaments(ArrayList<ActivityDTO> activities, ArrayList<MonitorDTO> monitors) throws Exception{
        BufferedReader file = new BufferedReader(new FileReader(new File(this.properties.getProperty("campaments"))));

        ArrayList<CampamentDTO> campaments = new ArrayList<CampamentDTO>();

        String line;

        // while((line = file.readLine()) != null){
        //     String[] elements = line.split(" ");

        //     CampamentDTO auxCampament = new CampamentDTO();

        //     auxCampament.setId(Integer.parseInt(elements[0]));
        //     auxCampament.setInitDate(LocalDate.parse(elements[1]));
        //     auxCampament.setFinalDate(LocalDate.parse(elements[2]));
        //     auxCampament.setMaxAssistants(Integer.parseInt(elements[3]));

        //     if(elements[4].equals("CHILD")){
        //         auxCampament.setLevel(Level.CHILD);
        //     }else if(elements[4].equals("YOUTH")){
        //         auxCampament.setLevel(Level.YOUTH);
        //     }else if(elements[4].equals("TEENAGER")){
        //         auxCampament.setLevel(Level.TEENAGER);
        //     }

        //     int i = 5;

        //     while(i < elements.length && !elements[i].equals("/")){
        //         boolean flag = false;
        //         for(int j = 0; j < activities.size() && !flag; j++){
        //             if(elements[i].equals(activities.get(j).getname())){
        //                 auxCampament.associateActivity(activities.get(j));
        //                 flag = true;
        //             }
        //         }

        //         i++;
        //     }

        //     i++;

        //     while(i < elements.length && !(auxCampament.getActivities().isEmpty())){
        //         boolean flag = false;
        //         for(int j = 0; j < monitors.size() && !flag; j++){
        //             if((Integer.parseInt(elements[i])) == (monitors.get(j).getID())){
        //                 auxCampament.associateMonitor(monitors.get(j));
        //                 flag = true;
        //             }
        //         }

        //         i++;
        //     }

        //     campaments.add(auxCampament);

        // }

        // file.close();

        return campaments;
    }

    public ArrayList<CompleteInscriptionDTO> importCompleteInscriptions(ArrayList<CampamentDTO> campaments, ArrayList<AssistantDTO> assistants) throws Exception{
        BufferedReader file = new BufferedReader(new FileReader(new File(this.properties.getProperty("completeinscriptions"))));

        ArrayList<CompleteInscriptionDTO> inscriptions = new ArrayList<CompleteInscriptionDTO>();

        String line;

        while((line = file.readLine()) != null){
            String elements[] = line.split(" ");

            CompleteInscriptionDTO auxCompleteInscription;

            boolean flag = false;
            CampamentDTO auxCampament = new CampamentDTO();
            for(int i = 0; i < campaments.size() && !flag; i++){
                if(campaments.get(i).getId() == (Integer.parseInt(elements[1]))){
                    auxCampament = campaments.get(i);
                    flag = true;
                }
            }

            flag = false;
            AssistantDTO auxAssistant = new AssistantDTO();
            for(int i = 0; i < assistants.size() && !flag; i++){
                if(assistants.get(i).getId() == (Integer.parseInt(elements[0]))){
                    auxAssistant = assistants.get(i);
                    flag = true;
                }
            }

            Schedule auxSchendule = Schedule.MORNING;
            if(elements[5].equals("MORNING")){
                auxSchendule = Schedule.MORNING;
            }else if(elements[5].equals("AFTERNOON")){
                auxSchendule = Schedule.AFTERNOON;
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

    public ArrayList<ParcialInscriptionDTO> importParcialInscriptions(ArrayList<CampamentDTO> campaments, ArrayList<AssistantDTO> assistants) throws Exception{
        BufferedReader file = new BufferedReader(new FileReader(new File(this.properties.getProperty("parcialinscriptions"))));

        ArrayList<ParcialInscriptionDTO> inscriptions = new ArrayList<ParcialInscriptionDTO>();

        String line;

        while((line = file.readLine()) != null){
            String elements[] = line.split(" ");

            ParcialInscriptionDTO auxParcialInscription;

            boolean flag = false;
            CampamentDTO auxCampament = new CampamentDTO();
            for(int i = 0; i < campaments.size() && !flag; i++){
                if(campaments.get(i).getId() == (Integer.parseInt(elements[1]))){
                    auxCampament = campaments.get(i);
                    flag = true;
                }
            }

            flag = true;
            AssistantDTO auxAssistant = new AssistantDTO();
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

    public void exportMonitors(ArrayList<MonitorDTO> monitors) throws Exception{
        BufferedWriter file = new BufferedWriter(new FileWriter(new File(this.properties.getProperty("monitors"))));

        for(MonitorDTO monitor: monitors){
            file.write(monitor.getID() + " " + monitor.getName() + " " + monitor.getSurname() + " " + monitor.isEspecial() + "\n");
        }

        file.close();
    }

    public void exportActivities(ArrayList<ActivityDTO> activities) throws Exception{
        BufferedWriter file = new BufferedWriter(new FileWriter(new File(this.properties.getProperty(("activities")))));

        for(ActivityDTO activity: activities){
            String line = activity.getname() + " " + activity.getLevel() + " " + activity.getSchendule() + " " + activity.getMaxParticipants() + " " + activity.getNumMonitors();

            for(MonitorDTO monitor: activity.getMonitors()){
                line += (" " + (monitor.getID()));
            }
            
            file.write(line + "\n");
        }

        file.close();
    }

    public void exportAssistants(ArrayList<AssistantDTO> assistants) throws Exception{
        BufferedWriter file = new BufferedWriter(new FileWriter(new File(this.properties.getProperty("assistants"))));

        for(AssistantDTO assistant: assistants){
            file.write(assistant.getId() + " " + assistant.getName() + " " + assistant.getSurname() + " " + assistant.getDate() + " " + assistant.getAtention() + "\n");
        }

        file.close();
    }

    public void exportCampaments(ArrayList<CampamentDTO> campaments) throws Exception{
        BufferedWriter file = new BufferedWriter(new FileWriter(new File(this.properties.getProperty("campaments"))));

        for(CampamentDTO campament: campaments){
            String line = campament.getId() + " " + campament.getInitDate() + " " + campament.getFinalDate() + " " + campament.getMaxAssistants() + " " + campament.getLevel();

            for(ActivityDTO activity: (campament.getActivities())){
                line += (" " + activity.getname());
            }

            line += " /";

            for(MonitorDTO monitor: (campament.getMonitors())){
                line += (" " + monitor.getID());
            }

            file.write(line + "\n");
        }

        file.close();
    }

    public void exportCompleteInscriptions(ArrayList<CompleteInscriptionDTO> inscriptions) throws Exception{
        BufferedWriter file = new BufferedWriter(new FileWriter(new File(this.properties.getProperty("completeinscriptions"))));

        for(CompleteInscriptionDTO inscription: inscriptions){
            file.write(inscription.getIdParticipant() + " " + inscription.getIdCampament() + " " + inscription.getDate() + " " + inscription.getPrice() + " " + inscription.getCancellation() + " " + inscription.getSchendule() + "\n");
        }

        file.close();
    }

    public void exportParcialInscriptions(ArrayList<ParcialInscriptionDTO> inscriptions) throws Exception{
        BufferedWriter file = new BufferedWriter(new FileWriter(new File(this.properties.getProperty("parcialinscriptions"))));

        for(ParcialInscriptionDTO inscription: inscriptions){
            file.write(inscription.getIdParticipant() + " " + inscription.getIdCampament() + " " + inscription.getDate() + " " + inscription.getPrice() + " " + inscription.getCancellation() + " " + inscription.getSchendule() + "\n");
        }

        file.close();
    }
}
