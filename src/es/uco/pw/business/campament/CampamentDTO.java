package es.uco.pw.business.campament;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import es.uco.pw.business.activity.ActivityDTO;
import es.uco.pw.business.common.level.Level;
import es.uco.pw.business.monitor.MonitorDTO;

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

    /**
     * Default constructor for a Campament. Initializes activity and monitor lists.
     */
    public CampamentDTO() {
        this.activities = new ArrayList<ActivityDTO>();
        this.monitors = new ArrayList<MonitorDTO>();
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
        this.level = level;
        this.activities = new ArrayList<ActivityDTO>();
        this.monitors = new ArrayList<MonitorDTO>();
    }

    public CampamentDTO(int id, LocalDate initDate, LocalDate finalDate, int maxAssistants, Level level) {
        this.id = id;
        this.initDate = initDate;
        this.finalDate = finalDate;
        this.activities = new ArrayList<ActivityDTO>();
        this.monitors = new ArrayList<MonitorDTO>();
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

    public String toString() {
        String campamentInfo = "El campamento con id: " + this.id + " empieza el " +
                this.initDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                " y termina el " + this.finalDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                " con maximo de " + this.maxAssistants + " asistentes.";

        return campamentInfo;
    }
    
}
