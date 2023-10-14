package classes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a campament for the system.
 */
public class Campament {
    private int id;
    private LocalDate initDate;    
    private LocalDate finalDate;
    private int maxAssistants;
    private Level level;
    private ArrayList<Activity> activities;
    private ArrayList<Monitor> monitors;

    /**
     * Default constructor for a Campament. Initializes activity and monitor lists.
     */
    public Campament(){
        this.activities = new ArrayList<Activity>();
        this.monitors = new ArrayList<Monitor>();
    }

    /**
     * Constructor for a Campament with an ID and start and end dates.
     *
     * @param id        The unique identifier for the camp.
     * @param initDate  The start date of the camp.
     * @param finalDate The end date of the camp.
     */
    public Campament(int id, LocalDate initDate, LocalDate finalDate){
        this.id = id;
        this.initDate = initDate;
        this.finalDate = finalDate;
        this.activities = new ArrayList<Activity>();
        this.monitors = new ArrayList<Monitor>();
    }

    public Campament(int id, LocalDate initDate, LocalDate finalDate, int maxAssistants, Level level){
        this.id = id;
        this.initDate = initDate;
        this.finalDate = finalDate;
        this.activities = new ArrayList<Activity>();
        this.monitors = new ArrayList<Monitor>();
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

    /**
     * Asociate an activity to the campament.
     * @param activity
     */
    public void associateActivity(Activity activity){
        if(activity.getLevel() != this.level){
            System.out.println("El nivel de la actividad no coincide con el nivel del campamento.");
            return;
        }
        this.activities.add(activity);
    }

    /**
     * Asociate a monitor to the campament.
     * @param monitor
     */
    public void associateMonitor(Monitor monitor){
        for(Activity a : this.activities){
            if(a.getMonitors().contains(monitor))
                this.monitors.add(monitor);
        }
    }

    /**
     * Asociate a special monitor to the campament.
     * @param monitor
     */
    public void associateSpecialMonitor(Monitor monitor){
        if(!monitor.isEspecial()){
            System.out.println("El monitor no es de atención especial");
            return;
        }
        
        for(Activity a : this.activities){
            if(a.getMonitors().contains(monitor)){
                System.out.println("El monitor ya se encuentra en una actividad");
                return;
            }
        }

        this.monitors.add(monitor);
    }

    public boolean existsEspecialAssistant(){
        return true;
    }
}
