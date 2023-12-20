package es.uco.pw.display;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.business.activity.ActivityDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.business.monitor.MonitorDTO;

public class Main {
    public static void main(String[] args) throws Exception {

        Properties sql_properties = new Properties();
        sql_properties.load(new FileInputStream("src/main/webapp/WEB-INF/sql.properties"));
        Properties config_properties = new Properties();
        config_properties.load(new FileInputStream("src/main/webapp/WEB-INF/config.properties"));

        CampamentsManager camp_man = new CampamentsManager(sql_properties, config_properties);
        ArrayList<CampamentDTO> campaments = camp_man.getAllCampaments();
        ArrayList<MonitorDTO> monitors = camp_man.getAllMonitors();
        ArrayList<ActivityDTO> activities = camp_man.getAllActivities();

        for(MonitorDTO mon: monitors){
            if(!mon.isEspecial()){
                for(ActivityDTO act: activities){
                    ArrayList<MonitorDTO> aux_monitors = act.getMonitors();
                    for(MonitorDTO aux_mon: aux_monitors){
                        if(aux_mon.getID() == mon.getID()){
                            for(CampamentDTO camp: campaments){
                                ArrayList<ActivityDTO> aux_activities = camp.getActivities();
                                for(ActivityDTO aux_act: aux_activities){
                                    if(aux_act.getname().equals(act.getname())){
                                        System.out.println(mon.getName() + " " + mon.getSurname() + " | " + camp.getId());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
