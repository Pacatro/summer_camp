package managers;

import java.util.ArrayList;

import classes.Activity;
import classes.Campament;
import classes.Monitor;
import enums.Level;
import enums.Schendule;

import java.time.LocalDate;

/**
 * Manages operations related to campaments, activities, and monitors in the system, including creation and association.
 */
public class CampamentsManager {
    
    /**
     * Creates a new activity and adds it to the list of activities.
     *
     * @param activities       The list of activities.
     * @param name             The name of the activity.
     * @param level            The level of the activity.
     * @param schendule        The schedule of the activity.
     * @param max_participants The maximum number of participants for the activity.
     * @param num_monitors     The number of monitors for the activity.
     */
    public void createActivity(ArrayList<Activity> activities, String name, Level level, Schendule schendule, int max_participants, int num_monitors) throws Exception {
        
        Activity newActivity = new Activity(name, level, schendule, max_participants, num_monitors);
        activities.add(newActivity);
    }

    /**
     * Creates a new monitor and adds it to the list of monitors.
     *
     * @param monitors    The list of monitors.
     * @param id          The ID of the monitor.
     * @param name        The name of the monitor.
     * @param surname     The surname of the monitor.
     * @param isEspecial  Whether the monitor is a special needs monitor (true/false).
     */
    public void createMonitor(ArrayList<Monitor> monitors, int id, String name, String surname, boolean isEspecial)
            throws Exception {

        Monitor newMonitor = new Monitor(id, name, surname, isEspecial);
        monitors.add(newMonitor);

    }

    /**
     * Creates a new campament and adds it to the list of campaments.
     *
     * @param campaments The list of campaments.
     * @param id         The ID of the campament.
     * @param initDate   The start date of the campament.
     * @param finalDate  The end date of the campament.
     * @param level      The level of the campament.
     */
    public void createCampaments(ArrayList<Campament> campaments, int id, LocalDate initDate, LocalDate finalDate,
            Level level) throws Exception {
        
        Campament nuevoCampamento = new Campament(id, initDate, finalDate, level);
        campaments.add(nuevoCampamento);

    }
    
    /**
     * Associates a monitor with an activity, if possible.
     *
     * @param activities             The list of activities.
     * @param monitors               The list of monitors.
     * @param selectedMonitorIndex   The index of the selected monitor.
     * @param activity               The activity to associate with the monitor.
     */
    public void associateMonitorsToActivities(ArrayList<Activity> activities, ArrayList<Monitor> monitors, int selectedMonitorIndex, Activity activity) {
        
        Monitor selectedMonitor = monitors.get(selectedMonitorIndex);
        boolean isMonitorAdded = activity.associateMonitor(selectedMonitor);
        if (!isMonitorAdded) {
            System.out.println("No se admiten mas monitores en esta actividad.");
            return;
        }
    }

    /**
     * Associates an activity with a campament.
     *
     * @param campaments              The list of campaments.
     * @param activities              The list of activities.
     * @param selectedActivityIndex   The index of the selected activity.
     * @param selectedCampament       The campament to associate the activity with.
     */
    public void associateActivitiesToCampaments(ArrayList<Campament> campaments, ArrayList<Activity> activities, int selectedActivityIndex, Campament selectedCampament) {
        
        Activity selectedActivity = activities.get(selectedActivityIndex);
        selectedCampament.associateActivity(selectedActivity);
    }

    /**
     * Associates a monitor with a campament based on specific criteria.
     *
     * @param campaments              The list of campaments.
     * @param monitors                The list of monitors.
     * @param selectedMonitorIndex    The index of the selected monitor.
     * @param selectedCampament       The campament to associate the monitor with.
     */
    public void associateMonitorsToCampaments(ArrayList<Campament> campaments, ArrayList<Monitor> monitors, int selectedMonitorIndex, Campament selectedCampament) {
        Monitor selectedMonitor = monitors.get(selectedMonitorIndex);
        ArrayList<Monitor> activityMonitors = selectedCampament.getAllActivityMonitors();

        if (selectedMonitor.isEspecial() && (selectedCampament.existsEspecialAssistant() && !activityMonitors.contains(selectedMonitor))
            ||
            !selectedMonitor.isEspecial() && activityMonitors.contains(selectedMonitor)) {
                selectedCampament.associateMonitor(selectedMonitor);
        }
    }
}
