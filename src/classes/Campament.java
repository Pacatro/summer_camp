package classes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Campament {
    private int id;
    private LocalDate initDate;    
    private LocalDate finalDate;
    private int maxAssistants;
    private Level level;
    private ArrayList<Activity> activities;
    private ArrayList<Monitor> monitors;

    public Campament(){
        this.activities = new ArrayList<Activity>();
        this.monitors = new ArrayList<Monitor>();
    }

    public Campament(int id, LocalDate initDate, LocalDate finalDate){
        this.id = id;
        this.initDate = initDate;
        this.finalDate = finalDate;
        this.activities = new ArrayList<Activity>();
        this.monitors = new ArrayList<Monitor>();
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

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public ArrayList<Monitor> getMonitors() {
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

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    public void setMonitors(ArrayList<Monitor> monitors) {
        this.monitors = monitors;
    }

    public String toString(){
        String campamentInfo = "El campamento con id: " + this.id + " empieza el " + 
                this.initDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + 
                " y termina el " + this.finalDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + 
                " con maximo de " + this.maxAssistants + " asistentes.";

        return campamentInfo;
    }

    public void associateActivity(Activity activity){
        if(activity.getLevel() != this.level)
            return;
        this.activities.add(activity);
    }

    public void associateMonitor(Monitor monitor){
        for(int i = 0; i < activities.size(); i++){
            if(this.activities.get(i).getMonitors().contains(monitor))
                this.monitors.add(monitor);
        }
    }

    public void associateSpecialMonitor(Monitor monitor){
        for(int i = 0; i < activities.size(); i++){
            if(this.activities.get(i).getMonitors().contains(monitor))
                return;
        }

        this.monitors.add(monitor);
    }
}
