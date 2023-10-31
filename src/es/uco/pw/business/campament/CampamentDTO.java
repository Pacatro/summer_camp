package es.uco.pw.business.campament;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import es.uco.pw.business.level.Level;
import es.uco.pw.business.activity.ActivityDTO;
import es.uco.pw.business.monitor.MonitorDTO;
//import es.uco.pw.business.assistant.AssistantDTO;

/**
 * Represents a campament for the system.
 */
public class CampamentDTO{
    private int id;
    private LocalDate initDate;
    private LocalDate finalDate;
    private int maxAssistants;
    private Level level;
    private ArrayList<ActivityDTO> activities;
    private ArrayList<MonitorDTO> monitors;
    //private ArrayList<AssistantDTO> assistants;

    /**
     * Default constructor for a Campament. Initializes activity and monitor lists.
     */
    public CampamentDTO() {
        this.activities = new ArrayList<ActivityDTO>();
        this.monitors = new ArrayList<MonitorDTO>();
        //this.assistants = new ArrayList<AssistantDTO>();
    }

    /**
     * Constructor for a Campament with an ID and start and end dates.
     *
     * @param id        The unique identifier for the camp.
     * @param initDate  The start date of the camp.
     * @param finalDate The end date of the camp.
     */
    public CampamentDTO(int id, LocalDate initDate, LocalDate finalDate, Level level) {
        this.id = id;
        this.initDate = initDate;
        this.finalDate = finalDate;
        this.activities = new ArrayList<ActivityDTO>();
        this.monitors = new ArrayList<MonitorDTO>();
        //this.assistants = new ArrayList<AssistantDTO>();
    }

    public CampamentDTO(int id, LocalDate initDate, LocalDate finalDate, int maxAssistants, Level level) {
        this.id = id;
        this.initDate = initDate;
        this.finalDate = finalDate;
        this.activities = new ArrayList<ActivityDTO>();
        this.monitors = new ArrayList<MonitorDTO>();
        //this.assistants = new ArrayList<AssistantDTO>();
        this.maxAssistants = maxAssistants;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public LocalDate getInitDate() {
        return initDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public int getMaxAssistants() {
        return maxAssistants;
    }

    public Level getLevel() {
        return level;
    }

    public ArrayList<ActivityDTO> getActivities() {
        return activities;
    }

    public ArrayList<MonitorDTO> getMonitors() {
        return monitors;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInitDate(LocalDate initDate) {
        this.initDate = initDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public void setMaxAssistants(int maxAssistants) {
        this.maxAssistants = maxAssistants;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setActivities(ArrayList<ActivityDTO> activities) {
        this.activities = activities;
    }

    public void setMonitors(ArrayList<MonitorDTO> monitors) {
        this.monitors = monitors;
    }

    // public void setAssistants(ArrayList<AssistantDTO> assistants) {
    //     this.assistants = assistants;
    // }

    public String toString() {
        String campamentInfo = "El campamento con id: " + this.id + " empieza el " +
                this.initDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                " y termina el " + this.finalDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                " con maximo de " + this.maxAssistants + " asistentes.";

        return campamentInfo;
    }

    /**
     * Asociate an activity to the campament.
     * 
     * @param activity
     */
    // public void associateActivity(ActivityDTO activity) {
    //     if (activity.getLevel() != this.level) {
    //         System.out.println("El nivel de la actividad no coincide con el nivel del campamento.");
    //         return;
    //     }
    //     this.activities.add(activity);
    // }

    /**
     * Asociate a monitor to the campament.
     * 
     * @param monitor
     */
    // public void associateMonitor(MonitorDTO monitor) {
    //     for (ActivityDTO a : this.activities) {
    //         if (a.getMonitors().contains(monitor))
    //             this.monitors.add(monitor);
    //     }
    // }

    /**
     * Asociate a special monitor to the campament.
     * 
     * @param monitor
     */
    // public void associateSpecialMonitor(MonitorDTO monitor) {
    //     if (!monitor.isEspecial()) {
    //         System.out.println("El monitor no es de atención especial");
    //         return;
    //     }

    //     for (ActivityDTO a : this.activities) {
    //         if (a.getMonitors().contains(monitor)) {
    //             System.out.println("El monitor ya se encuentra en una actividad");
    //             return;
    //         }
    //     }

    //     this.monitors.add(monitor);
    // }

    // public boolean existsEspecialAssistant() {
    //     for (AssistantDTO it : this.assistants) {
    //         if (it.getAtention())
    //             return true;
    //     }
    //     return false;
    // }

    //sacamos todos los monitores asociados a actividades. (Para asociar un monitor a un campamento antes tiene que estar asociado a una actividad.)
    // public ArrayList<MonitorDTO> getAllActivityMonitors() { 
    //     ArrayList<MonitorDTO> ActivityMonitors = new ArrayList<MonitorDTO>();
    //     for (ActivityDTO activity : getActivities()) {
    //         for (MonitorDTO monitor : activity.getMonitors()) {
    //             if (!ActivityMonitors.contains(monitor)) {
    //                 ActivityMonitors.add(monitor);
    //             }
    //         }
    //     }
    //     return ActivityMonitors;
    // }
}
