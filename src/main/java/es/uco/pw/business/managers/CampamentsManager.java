package es.uco.pw.business.managers;

import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.business.activity.ActivityDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.common.exceptions.BusinessException;
import es.uco.pw.business.common.level.Level;
import es.uco.pw.business.common.schedule.Schedule;
import es.uco.pw.business.monitor.MonitorDTO;
import es.uco.pw.data.dao.activity.ActivityDAO;
import es.uco.pw.data.dao.campament.CampamentDAO;
import es.uco.pw.data.dao.monitor.MonitorDAO;

import java.time.LocalDate;

/**
 * Manages operations related to campaments, activities, and monitors in the system, including creation and association.
 */
public class CampamentsManager {
    
    private Properties sqlProperties;
    private Properties configProperties;
    
    public CampamentsManager(Properties sqlProperties, Properties configProperties){
        this.configProperties = configProperties;
        this.sqlProperties = sqlProperties;
    }

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

            ActivityDAO dao = new ActivityDAO(this.sqlProperties, this.configProperties);
            ActivityDTO newActivity = new ActivityDTO(name, level, schedule, max_participants, num_monitors);
            dao.insert(newActivity);
            
        } catch (Exception e) { BusinessException.handleException(e); }
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

            MonitorDAO dao = new MonitorDAO(this.sqlProperties, this.configProperties);
            MonitorDTO newMonitor = new MonitorDTO(id, name, surname, isEspecial);
            dao.insert(newMonitor);

        } catch (Exception e) { BusinessException.handleException(e); }
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
    public void createCampaments(int id, LocalDate initDate, LocalDate finalDate, Level level ,int max_assistants) throws Exception {
        try{
            if(initDate.isBefore(finalDate)){
            CampamentDAO dao = new CampamentDAO(this.sqlProperties, this.configProperties);
            CampamentDTO newCampament = new CampamentDTO(id, initDate, finalDate,max_assistants, level);
            dao.insert(newCampament);
            }

        } catch (Exception e) { BusinessException.handleException(e); }
        
    }
    
    /**
     * Associates a monitor to an activity, considering the limit of monitors for the activity.
     *
     * @param monitor_id The ID of the monitor to be associated with the activity.
     * @param activity_id The ID of the activity to associate the monitor with.
     * @throws Exception If an error occurs during the association process or if the monitor limit for the activity is reached.
     */
    public void associateMonitorsToActivities(int monitor_id, String activity_id) throws Exception{
        //Modificada para que solo se añada un monitor
        try{
            ActivityDAO act_dao = new ActivityDAO(this.sqlProperties, this.configProperties);

            if(act_dao.isMonitorsFull(activity_id)){
                throw new Exception("Demasiados monitores para esta actividad.");
            }

            act_dao.addMonitor(activity_id, monitor_id);

        } catch(Exception e) { BusinessException.handleException(e); }
    }

    /**
     * Retrieves a list of all activities.
     *
     * @return An ArrayList of ActivityDTO objects representing all activities.
     */
    public ArrayList<ActivityDTO> getAllActivities() throws Exception{
        ArrayList<ActivityDTO> activities = new ArrayList<>();
        try{
            ActivityDAO dao = new ActivityDAO(this.sqlProperties, this.configProperties);
            activities = dao.getAll();
        } catch (Exception e) { BusinessException.handleException(e); }
        
        return activities;
    }

    /**
     * Retrieves a list of all monitors that are not special.
     *
     * @return An ArrayList of MonitorDTO objects representing non-special monitors.
     */
    public ArrayList<MonitorDTO> getAllMonitorsNotEspecial() throws Exception{
        ArrayList<MonitorDTO> notEspecial = new ArrayList<MonitorDTO>();
        
        try{
            MonitorDAO dao = new MonitorDAO(this.sqlProperties, this.configProperties);
            ArrayList<MonitorDTO> monitors = dao.getAll();

            for(MonitorDTO m: monitors){
                if(!(m.isEspecial())){
                    notEspecial.add(m);
                }
            }    
        } catch (Exception e) { BusinessException.handleException(e); }
        
        return notEspecial;
    }

    /**
     * Associates an activity to a campament based on their levels.
     *
     * @param camp_id The ID of the campament to associate the activity with.
     * @param activityId The ID of the activity to be associated with the campament.
     * @throws Exception If an error occurs during the association process.
     */
    public void associateActivitiesToCampaments(int camp_id, String activityId) throws Exception {
        CampamentDAO campamentDAO = new CampamentDAO(this.sqlProperties, this.configProperties);
        ActivityDAO activityDAO = new ActivityDAO(this.sqlProperties, this.configProperties);
        CampamentDTO campament = new CampamentDTO();
        ActivityDTO activity = new ActivityDTO();

        // Obtener el campamento y la actividad
        try {
            campament = campamentDAO.getById(camp_id);
            activity = activityDAO.getById(activityId);
        } catch (Exception e) { BusinessException.handleException(e); }

        // Verificar si tienen el mismo nivel
        if (campament.getLevel() != activity.getLevel())
            throw new BusinessException("La actividad no tiene el mismo nivel que el campamento.");
        
        campamentDAO.addActivity(camp_id, activityId);
    }
    
    /**
     * Retrieves a list of all campaments.
     *
     * @return An ArrayList of CampamentDTO objects representing all campaments.
     */
    public ArrayList<CampamentDTO> getAllCampaments() throws Exception{
        ArrayList<CampamentDTO> campaments = new ArrayList<>();
        
        try{
            CampamentDAO dao = new CampamentDAO(this.sqlProperties, this.configProperties);
            campaments = dao.getAll();
        } catch (Exception e) { BusinessException.handleException(e); }

        return campaments;
    }

    /**
     * Retrieves a list of all monitors.
     * 
     * @return An ArrayList of MonitorDTO objects representing all monitors.
     */
    public ArrayList<MonitorDTO> getAllMonitors() throws Exception{
        ArrayList<MonitorDTO> monitors = new ArrayList<>();
        
        try{
            MonitorDAO dao = new MonitorDAO(this.sqlProperties, this.configProperties);
            monitors = dao.getAll();
        } catch (Exception e) { BusinessException.handleException(e); }

        return monitors;
    }
    
    /**
     * Associates a monitor to a campament based on specified conditions.
     *
     * @param camp_id The ID of the campament to associate the monitor with.
     * @param monitor_id The ID of the monitor to be associated with the campament.
     * @throws Exception If an error occurs during the association process or if the monitor cannot be associated.
     */
    public void associateMonitorsToCampaments(int camp_id, int monitor_id) throws Exception { 
        try {
            CampamentDAO campamentDAO = new CampamentDAO(this.sqlProperties, this.configProperties);
            
            MonitorDAO monitorDAO = new MonitorDAO(this.sqlProperties, this.configProperties);
            MonitorDTO selectedMonitor = monitorDAO.getById(monitor_id);

            ArrayList<ActivityDTO> activities = campamentDAO.getActivitiesFromCampament(camp_id);

            for(ActivityDTO act : activities) {
                for(MonitorDTO mon : act.getMonitors()) {
                    if(mon.getID() == selectedMonitor.getID()) {
                        campamentDAO.addMonitor(camp_id, monitor_id);
                    }
                }
            }

        } catch (Exception e) { BusinessException.handleException(e); }        
    }

    /**
     * Retrieves a CampamentDTO object based on its identifier.
     * 
     * @param id The identifier of the CampamentDTO object that is to be retrieved.
     * @return The CampamentDTO object corresponding to the provided identifier.
     */
    public CampamentDTO getById(int id) throws Exception{
        CampamentDTO campament = new CampamentDTO();
        
        try{
            CampamentDAO campamentDAO = new CampamentDAO(this.sqlProperties, this.configProperties);
            campament = campamentDAO.getById(id);
        } catch (Exception e) { BusinessException.handleException(e); }

        return campament;
    }

    public void getNumInscriptionsAll(ArrayList<Integer> campaments, ArrayList<Integer> num_inscrip_c,
                                      ArrayList<Integer> num_inscrip_p) throws Exception{
                                        
        CampamentDAO dao = new CampamentDAO(sqlProperties, configProperties);
        try{
            dao.getNumInscriptionsAll(campaments, num_inscrip_c, num_inscrip_p);
        }catch(Exception e) {BusinessException.handleException(e);}

    }

    public ArrayList<CampamentDTO> getCampsByDateInterval(LocalDate initDate, LocalDate finalDate) throws Exception{
        ArrayList<CampamentDTO> campaments = new ArrayList<>();
        
        try{
            CampamentDAO dao = new CampamentDAO(this.sqlProperties, this.configProperties);
            campaments = dao.getCampsByDateInterval(initDate, finalDate);
        } catch (Exception e) { BusinessException.handleException(e); }

        return campaments;

    }
}
