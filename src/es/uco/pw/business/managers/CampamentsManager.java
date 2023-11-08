package es.uco.pw.business.managers;

import java.util.ArrayList;

import es.uco.pw.business.activity.ActivityDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.monitor.MonitorDTO;
import es.uco.pw.business.level.Level;
import es.uco.pw.business.schendule.Schendule;
import es.uco.pw.data.dao.activity.ActivityDAO;
import es.uco.pw.data.dao.monitor.MonitorDAO;

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
    public void createActivity(String name, Level level, Schendule schendule, int max_participants, int num_monitors) throws Exception {
        try{

            ActivityDAO dao = new ActivityDAO();
            ActivityDTO newActivity = new ActivityDTO(name, level, schendule, max_participants, num_monitors);
            dao.insert(newActivity);
            
        } catch (Exception e) {throw e;}
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
    public void createMonitor(int id, String name, String surname, boolean isEspecial) throws Exception {
        try{

            MonitorDAO dao = new MonitorDAO();
            MonitorDTO newMonitor = new MonitorDTO(id, name, surname, isEspecial);
            dao.insert(newMonitor);

        } catch (Exception e) {throw e;}
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
    public void createCampaments(ArrayList<CampamentDTO> campaments, int id, LocalDate initDate, LocalDate finalDate,
            Level level) throws Exception {
        
        CampamentDTO nuevoCampamento = new CampamentDTO(id, initDate, finalDate, level);
        campaments.add(nuevoCampamento);

    }
    
    /**
     * Associates a monitor with an activity, if possible.
     *
     * @param monitor_id             The monitor id to associate.
     * @param activity_id            The activity id to associate.
     */
    public void associateMonitorsToActivities(int monitor_id, String activity_id) throws Exception{
        //Modificada para que solo se añada un monitor
        try{
            ActivityDAO act_dao = new ActivityDAO();

            if(act_dao.isMonitorsFull(activity_id)){ //TODO que se vea el error
                return;
            }

            act_dao.addMonitor(activity_id, monitor_id);

        } catch(Exception e) {throw e;}
    }

    /**
     * Associates an activity with a campament.
     *
     * @param campaments              The list of campaments.
     * @param activities              The list of activities.
     * @param selectedActivityIndex   The index of the selected activity.
     * @param selectedCampament       The campament to associate the activity with.
     */
    public void associateActivitiesToCampaments(ArrayList<CampamentDTO> campaments, ArrayList<ActivityDTO> activities, int selectedActivityIndex, CampamentDTO selectedCampament) {
        
        // ActivityDTO selectedActivity = activities.get(selectedActivityIndex);
        // selectedCampament.associateActivity(selectedActivity);
    }

    /**
     * Associates a monitor with a campament based on specific criteria.
     *
     * @param campaments              The list of campaments.
     * @param monitors                The list of monitors.
     * @param selectedMonitorIndex    The index of the selected monitor.
     * @param selectedCampament       The campament to associate the monitor with.
     */
    public void associateMonitorsToCampaments(ArrayList<CampamentDTO> campaments, ArrayList<MonitorDTO> monitors, int selectedMonitorIndex, CampamentDTO selectedCampament) {
        // MonitorDTO selectedMonitor = monitors.get(selectedMonitorIndex);
        // ArrayList<MonitorDTO> activityMonitors = selectedCampament.getAllActivityMonitors();

        // if (selectedMonitor.isEspecial() && (selectedCampament.existsEspecialAssistant() && !activityMonitors.contains(selectedMonitor))
        //     ||
        //     !selectedMonitor.isEspecial() && activityMonitors.contains(selectedMonitor)) {
        //         selectedCampament.associateMonitor(selectedMonitor);
        // }
    }
}
