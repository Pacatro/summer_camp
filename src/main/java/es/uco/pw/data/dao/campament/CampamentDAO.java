package es.uco.pw.data.dao.campament;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.Date;

import es.uco.pw.business.activity.ActivityDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.common.level.Level;
import es.uco.pw.business.common.schedule.Schedule;
import es.uco.pw.business.monitor.MonitorDTO;
import es.uco.pw.data.common.ConnectionDB;
import es.uco.pw.data.common.DataException;
import es.uco.pw.data.common.IDAO;

/**
 * Manage the data from the campaments table
 */
public class CampamentDAO implements IDAO<CampamentDTO, Integer>{

    public CampamentDAO(){}
    
    @Override
    public void insert(CampamentDTO campamentDTO) throws Exception{
        try{

            ConnectionDB connDB = new ConnectionDB();

            Connection conn = connDB.getConnection();

            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("INSERT_CAMPAMENT");
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, campamentDTO.getId());
            ps.setDate(2, Date.valueOf(campamentDTO.getInitDate()));
            ps.setDate(3, Date.valueOf(campamentDTO.getFinalDate()));
            ps.setInt(4, campamentDTO.getMaxAssistants());
            ps.setString(5,campamentDTO.getLevel().toString());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected <= 0)
                throw new DataException("No se pudo insertar el campamento.");

            for(int i=0; i<campamentDTO.getActivities().size(); i++){
                addActivity(campamentDTO.getId(), campamentDTO.getActivities().get(i).getname());
            }

            for (int i=0; i<campamentDTO.getMonitors().size(); i++){
                addMonitor(campamentDTO.getId(), campamentDTO.getMonitors().get(i).getID());
            }

            connDB.disconnect();

        } catch (Exception e) { throw e; }
    }

    /**
     * Adds an activity to a specific camp in the database.
     *
     * @param campId The identifier of the camp to which the activity is added.
     * @param activityId The identifier of the activity being added.
     */
    public void addActivity(int campId, String activityId) throws Exception{
        try{

            ConnectionDB connDB = new ConnectionDB();

            Connection conn = connDB.getConnection();

            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("INSERT_ACTIVITY_CAMPAMENT");
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, activityId);
            ps.setInt(2, campId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected <= 0)
                throw new DataException("No se pudo asociar la actividad " + activityId + " con el campamento " + campId + ".");

            connDB.disconnect();

        } catch (Exception e) { throw e; }
    }

    /**
     * Adds a monitor to a specific camp in the database.
     *
     * @param campId The identifier of the camp to which the monitor is added.
     * @param monitorId The identifier of the monitor being added.
     */
    public void addMonitor(int campId, int monitorId) throws Exception{
        try{
            ConnectionDB connDB = new ConnectionDB();

            Connection conn = connDB.getConnection();

            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("INSERT_MONITOR_CAMPAMENT");
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, campId);
            ps.setInt(2, monitorId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected <= 0)
                throw new DataException("No se pudo asociar el monitor " + monitorId + " con el campamento " + campId + ".");

            connDB.disconnect();

        } catch (Exception e) { throw e; }
    }

    /**
     * Checks if a special assistant exists for a specific camp in the database.
     *
     * @param campId The identifier of the camp to check for a special assistant.
     * @return True if a special assistant exists for the camp, false otherwise.
     */
    public boolean existsEspecialAsistant(int campId) throws Exception{
        try{

            ConnectionDB connDB = new ConnectionDB();

            Connection conn = connDB.getConnection();

            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("GET_ESPECIAL_ASISTANT");
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, campId);

            if(!ps.execute())
                throw new DataException("No se pudo seleccionar ningun monitor especial en el campamento " + campId + ".");

            ResultSet rs = ps.executeQuery();

            boolean flag = rs.next();

            connDB.disconnect();

            return flag;

        } catch (Exception e) { throw e; }
    }

    @Override
    public CampamentDTO getById(Integer id) throws Exception{
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("GETBYID_CAMPAMENT");

            ConnectionDB connDB = new ConnectionDB();

            Connection conn = connDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            if(!ps.execute())
                throw new DataException("No existe ningun campamento con el id: " + id + ".");

            ResultSet rs = ps.executeQuery();
            
            CampamentDTO camp = null;

            while(rs.next()){
                camp = new CampamentDTO();
                camp.setId(rs.getInt("camp_id"));
                camp.setInitDate(rs.getDate("start_date").toLocalDate());
                camp.setFinalDate(rs.getDate("end_date").toLocalDate());
                camp.setMaxAssistants(rs.getInt("max_assistant"));
                camp.setLevel(Level.valueOf(rs.getString("educate_level")));
                camp.setActivities(getActivitiesFromCampament(id));
                camp.setMonitors(getMonitorsFromCampament(id));
            }

            connDB.disconnect();

            return camp;

        } catch (Exception e) { throw e; }
    }

    /**
     * Retrieves a list of monitors associated with a specific camp in the database.
     *
     * @param id The identifier of the camp for which monitors are retrieved.
     * @return An ArrayList of MonitorDTO objects representing the monitors associated with the camp.
     */
    public ArrayList<MonitorDTO> getMonitorsFromCampament(Integer id) throws Exception{
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("GET_MONITORS_CAMPAMENT");

            ConnectionDB connDB = new ConnectionDB();

            Connection conn = connDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            if(!ps.execute())
                throw new DataException("No se ha podido seleccionar los monitores del campamento " + id + ".");

            ResultSet rs = ps.executeQuery();

            ArrayList<MonitorDTO> monitors = new ArrayList<MonitorDTO>();

            while(rs.next()){
                monitors.add(new MonitorDTO(rs.getInt("monitor_id"), rs.getString("name"), 
                                            rs.getString("surname"), rs.getBoolean("special_edu")));
            }

            connDB.disconnect();

            return monitors;
        } catch (Exception e) { throw e; }
    }

    /**
     * Retrieves a list of activities associated with a specific camp in the database.
     *
     * @param id The identifier of the camp for which activities are retrieved.
     * @return An ArrayList of ActivityDTO objects representing the activities associated with the camp.
     */
    public ArrayList<ActivityDTO> getActivitiesFromCampament(Integer id)throws Exception{
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("GET_ACTIVITIES_CAMPAMENT");

            ConnectionDB connDB = new ConnectionDB();

            Connection conn = connDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            if(!ps.execute())
                throw new DataException("No se ha podido seleccionar las actividades del campamento " + id + ".");

            ResultSet rs = ps.executeQuery();

            ArrayList<ActivityDTO> activities = new ArrayList<ActivityDTO>();

            while(rs.next()){
                ActivityDTO act = new ActivityDTO();

                act.setname(rs.getString("name"));
                act.setMaxParticipants(rs.getInt("max_participants"));
                act.setNumMonitors(rs.getInt("num_monitors"));

                String levelString = rs.getString("education_level");
                act.setLevel(Level.valueOf(levelString));

                String scheduleString = rs.getString("schedule");
                act.setSchedule(Schedule.valueOf(scheduleString));

                activities.add(act);
            }

            connDB.disconnect();

            return activities;
        } catch (Exception e) { throw e; }
    }
 
    @Override
    public ArrayList<CampamentDTO> getAll() throws Exception{
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("GETALL_CAMPAMENTS");

            ConnectionDB connDB = new ConnectionDB();

            Connection conn = connDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            if(!ps.execute())
                throw new DataException("No se han podido seleccionar todos los campamentos.");

            ResultSet rs = ps.executeQuery();

            ArrayList<CampamentDTO> campaments = new ArrayList<CampamentDTO>();
            
            while(rs.next()){
                CampamentDTO camp = new CampamentDTO();
                camp.setId(rs.getInt("camp_id"));
                camp.setInitDate(rs.getDate("start_date").toLocalDate());
                camp.setFinalDate(rs.getDate("end_date").toLocalDate());
                camp.setMaxAssistants(rs.getInt("max_assistant"));
                camp.setLevel(Level.valueOf(rs.getString("educate_level")));
                camp.setActivities(getActivitiesFromCampament(camp.getId()));
                camp.setMonitors(getMonitorsFromCampament(camp.getId()));
                campaments.add(camp);
            }

            connDB.disconnect();

            return campaments;
        } catch (Exception e) { throw e; }
    }

    public ArrayList<CampamentDTO> getByAssistant(int id) throws Exception{

        Properties properties = new Properties();
        properties.load(new FileInputStream("sql.properties"));
        String sql = properties.getProperty("GET_CAMPAMENT_BYASSIST");

        ConnectionDB connDB = new ConnectionDB();

        Connection conn = connDB.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id);

        if(!ps.execute())
            throw new DataException("No se han podido seleccionar los campamentos.");

        ResultSet rs = ps.executeQuery();
        ArrayList<CampamentDTO> campaments = new ArrayList<CampamentDTO>();

        while(rs.next()){
            CampamentDTO camp = new CampamentDTO();
            camp.setId(rs.getInt("camp_id"));
            camp.setInitDate(rs.getDate("start_date").toLocalDate());
            camp.setFinalDate(rs.getDate("end_date").toLocalDate());
            camp.setMaxAssistants(rs.getInt("max_assistant"));
            camp.setLevel(Level.valueOf(rs.getString("educate_level")));
            camp.setActivities(getActivitiesFromCampament(camp.getId()));
            camp.setMonitors(getMonitorsFromCampament(camp.getId()));
            campaments.add(camp);
        }

        connDB.disconnect();

        return campaments;
    }

    public int getNumInscriptions(int camp_id) throws Exception{
        Properties properties = new Properties();
        properties.load(new FileInputStream("sql.properties"));
        String sql = properties.getProperty("GET_NUMINSCRIP_CAMP");

        ConnectionDB connDB = new ConnectionDB();

        Connection conn = connDB.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, camp_id);

        if(!ps.execute())
            throw new DataException("No se han podido contar las inscripciones.");

        ResultSet rs = ps.executeQuery();
        int count = 0;

        while(rs.next()){
            count = rs.getInt("count(ass_id)");
        }

        return count;
    }

    @Override
    public void update(CampamentDTO campamentDTO) throws Exception { throw new UnsupportedOperationException("Unimplemented method 'update'"); }
}