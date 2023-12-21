package es.uco.pw.business.activity;
import java.util.ArrayList;

import es.uco.pw.business.common.level.Level;
import es.uco.pw.business.common.schedule.Schedule;
import es.uco.pw.business.monitor.MonitorDTO;

/**
 * Represents an activity of the system.
 */
public class ActivityDTO { 
    
    /* Atributos */
    private String name;

    private Level level;

    private Schedule schedule;

    private int max_participants;

    private int num_monitors;

    private ArrayList<MonitorDTO> monitors;

    /* Constructores */

    public ActivityDTO(){
        this.name = "";
        this.level = Level.CHILD;
        this.schedule = Schedule.MORNING;
        this.max_participants = 0;
        this.num_monitors = 0;
        this.monitors = new ArrayList<MonitorDTO>();
    }

    public ActivityDTO(String name, Level level, Schedule schedule, int max_participants, int num_monitors){
        this.name = name;
        this.level = level;
        this.schedule = schedule;
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

    public Schedule getSchedule(){
        return schedule;
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

    public void setSchedule(Schedule schedule){
        this.schedule = schedule;
    }

    public void setMaxParticipants(int max_participants){
        this.max_participants = max_participants;
    }

    public void setNumMonitors(int num_monitors){
        this.num_monitors = num_monitors;
    }

    public void addMonitor(MonitorDTO monitor){
        this.monitors.add(monitor);
    }

    public String toString(){
        String activityInfo = "Actividad " + this.name + " de nivel " + this.level + " en el horario de "
                                + this.schedule + ", el maximo de participantes es " + this.max_participants
                                + " y los monitores son " + this.num_monitors + ": " + monitors.toString();
        return activityInfo;
    }

}
