package es.uco.pw.business.activity;
import java.util.ArrayList;

import es.uco.pw.business.level.Level;
import es.uco.pw.business.schendule.Schendule;
import es.uco.pw.business.monitor.MonitorDTO;

/**
 * Represents an activity of the system.
 */
public class ActivityDTO {
    
    /* Atributos */
    private String name;

    private Level level;

    private Schendule schendule;

    private int max_participants;

    private int num_monitors;

    private ArrayList<MonitorDTO> monitors;

    /* Constructores */

    public ActivityDTO(){
        this.name = "";
        this.level = Level.CHILD;
        this.schendule = Schendule.MORNING;
        this.max_participants = 0;
        this.num_monitors = 0;
        this.monitors = new ArrayList<MonitorDTO>();
    }

    public ActivityDTO(String name, Level level, Schendule schendule, int max_participants, int num_monitors){
        this.name = name;
        this.level = level;
        this.schendule = schendule;
        this.max_participants = max_participants;
        this.num_monitors = num_monitors;
        this.monitors = new ArrayList<MonitorDTO>();
    }

    /* Metodos */

    public String getname(){
        return name;
    }

    public Level getLevel(){
        return level;
    }

    public Schendule getSchendule(){
        return schendule;
    }

    public int getMaxParticipants(){
        return max_participants;
    }

    public int getNumMonitors(){
        return num_monitors;
    }

    public ArrayList<MonitorDTO> getMonitors(){
        return monitors;
    }

    public void setname(String name){
        this.name = name;
    }

    public void setLevel(Level level){
        this.level = level;
    }

    public void setSchendule(Schendule schendule){
        this.schendule = schendule;
    }

    public void setMaxParticipants(int max_participants){
        this.max_participants = max_participants;
    }

    public void setNumMonitors(int num_monitors){
        this.num_monitors = num_monitors;
    }

    public String toString(){
        String activityInfo = "Actividad " + this.name + " de nivel " + this.level + " en el horario de "
                                + this.schendule + ", el maximo de participantes es " + this.max_participants
                                + " y los monitores son " + this.num_monitors + ": " + monitors.toString();
        return activityInfo;
    }

    //TOOD hacer asociar monitor como dao
    /*public boolean associateMonitor(Monitor newMonitor){
        
        if(this.monitors.size() < this.num_monitors){
            this.monitors.add(newMonitor);
            return true;
        }
        
        return false;
    } */

}