package es.uco.pw.business.managers;

import java.util.ArrayList;

import es.uco.pw.business.activity.ActivityDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.monitor.MonitorDTO;
import es.uco.pw.business.schedule.Schedule;
import es.uco.pw.business.level.Level;
import es.uco.pw.data.dao.activity.ActivityDAO;
import es.uco.pw.data.dao.campament.CampamentDAO;
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
    * @param schedule          The schedule of the activity.
     * @param max_participants The maximum number of participants for the activity.
     * @param num_monitors     The number of monitors for the activity.
     */
    public void createActivity(String name, Level level, Schedule schedule, int max_participants, int num_monitors) throws Exception {
        try{

            ActivityDAO dao = new ActivityDAO();
            ActivityDTO newActivity = new ActivityDTO(name, level, schedule, max_participants, num_monitors);
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
     * @return True if the campament was successfully created, false otherwise.
     */
    public boolean createCampaments(int id, LocalDate initDate, LocalDate finalDate, Level level ,int max_assistants) throws Exception {
        try{
    
            CampamentDAO dao = new CampamentDAO();
            CampamentDTO newCampament = new CampamentDTO(id, initDate, finalDate,max_assistants, level);
            dao.insert(newCampament);
            return true;

        } catch (Exception e) {return false;}
        
    }
    
    /**
     * Associates a monitor with an activity.
     *
     * @param monitor_id    The ID of the monitor to associate.
     * @param activity_id   The ID of the activity to associate the monitor with.
     * @return True if the association was successful, false if the activity is already full of monitors.
     */
    public boolean associateMonitorsToActivities(int monitor_id, String activity_id) throws Exception{
        //Modificada para que solo se añada un monitor
        try{
            ActivityDAO act_dao = new ActivityDAO();

            if(act_dao.isMonitorsFull(activity_id)){ //TODO que se vea el error
                return false;
            }

            act_dao.addMonitor(activity_id, monitor_id);

            return true;

        } catch(Exception e) {throw e;}
    }

    /**
     * Retrieves a list of all activities.
     *
     * @return An ArrayList of ActivityDTO objects representing all activities.
     */
    public ArrayList<ActivityDTO> getAllActivities() throws Exception{
        try{
            ActivityDAO dao = new ActivityDAO();
            return dao.getAll();
        } catch (Exception e) {throw e;}
    }

    /**
     * Retrieves a list of all monitors that are not special.
     *
     * @return An ArrayList of MonitorDTO objects representing non-special monitors.
     */
    public ArrayList<MonitorDTO> getAllMonitorsNotEspecial() throws Exception{
        try{
            MonitorDAO dao = new MonitorDAO();
            ArrayList<MonitorDTO> monitors = dao.getAll();
            ArrayList<MonitorDTO> notEspecial = new ArrayList<MonitorDTO>();

            for(MonitorDTO m: monitors){
                if(!(m.isEspecial())){
                    notEspecial.add(m);
                }
            }

            return notEspecial;
            
        } catch (Exception e) {throw e;}
    }

    /**
     * Associates an activity with a campament.
     * 
     * @param camp_id       The campament id to associate.
     * @param activityId    The activity id to associate.
     * @return True if the association was successful, false if the levels do not match or an error occurs.
     */
    public boolean associateActivitiesToCampaments(int camp_id, String activityId) throws Exception {
        
        try {
            CampamentDAO campamentDAO = new CampamentDAO();
            ActivityDAO activityDAO = new ActivityDAO();
    
            // Obtener el campamento y la actividad
            CampamentDTO campament = campamentDAO.getById(camp_id);
            ActivityDTO activity = activityDAO.getById(activityId);
    
            // Verificar si tienen el mismo nivel
            if (campament.getLevel() == activity.getLevel()) {
                campamentDAO.addActivity(camp_id, activityId);
                return true;
            }
           
        }catch (Exception e) {throw e;}
        return false;
    }
    
    /**
     * Retrieves a list of all campaments.
     *
     * @return An ArrayList of CampamentDTO objects representing all campaments.
     */
    public ArrayList<CampamentDTO> getAllCampaments() throws Exception{
        try{
            CampamentDAO dao = new CampamentDAO();
            return dao.getAll();
        } catch (Exception e) {throw e;}
    }

    /**
     * Retrieves a list of all monitors.
     * 
     * @return An ArrayList of MonitorDTO objects representing all monitors.
     */
    public ArrayList<MonitorDTO> getAllMonitors() throws Exception{
        try{
            MonitorDAO dao = new MonitorDAO();
            return dao.getAll();
        } catch (Exception e) {throw e;}
    }

    
    /**
     * Associates a monitor with a campament based on certain conditions.
     *
     * @param camp_id The ID of the campament.
     * @param monitor_id The ID of the monitor.
     * @return True if the association was successful, false if conditions are not met or an error occurs.
     */
    public boolean associateMonitorsToCampaments(int camp_id, int monitor_id) throws Exception {
        try{
    
                MonitorDAO monitorDAO = new MonitorDAO();
                CampamentDAO campamentDAO = new CampamentDAO();

                MonitorDTO selectedMonitor = monitorDAO.getById(monitor_id);
                CampamentDTO selectedCampament = getById(camp_id);
                ArrayList<MonitorDTO> activityMonitors = selectedCampament.getMonitors();


                if (selectedMonitor.isEspecial() && (campamentDAO.existsEspecialAsistant(camp_id) && !activityMonitors.contains(selectedMonitor))
                    ||
                    !selectedMonitor.isEspecial() && activityMonitors.contains(selectedMonitor)) {
                    
                        campamentDAO.addMonitor(camp_id, monitor_id);
                        return true;
                }
            } catch (Exception e) {throw e;}
         return false;
    }

    /**
     * Retrieves a CampamentDTO object based on its identifier.
     *
     * @param id The identifier of the CampamentDTO object that is to be retrieved.
     * @return The CampamentDTO object corresponding to the provided identifier.
     */
    public CampamentDTO getById(int id) throws Exception{
        try{
            CampamentDAO campamentDAO = new CampamentDAO();
            CampamentDTO campament = campamentDAO.getById(id);
            return campament;
        } catch (Exception e) {throw e;}
    }
}
