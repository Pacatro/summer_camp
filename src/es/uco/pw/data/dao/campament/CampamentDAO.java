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
import es.uco.pw.data.dao.common.IDAO;


/**
 * Manage the data from the campaments table
 */
public class CampamentDAO implements IDAO<CampamentDTO, Integer>{

    public CampamentDAO(){}
    
    @Override
    public void insert(CampamentDTO campamentDTO) throws Exception{
        try{
            Connection conn = new ConnectionDB().getConnection();

            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("INSERT_CAMPAMENT");
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, campamentDTO.getId());
            ps.setDate(2, Date.valueOf(campamentDTO.getInitDate()));
            ps.setDate(3, Date.valueOf(campamentDTO.getFinalDate()));
            ps.setInt(4, campamentDTO.getMaxAssistants());
            ps.setString(5,campamentDTO.getLevel().toString());

            ps.execute();

            for(int i=0; i<campamentDTO.getActivities().size(); i++){
                addActivity(campamentDTO.getId(), campamentDTO.getActivities().get(i).getname());
            }

            for (int i=0; i<campamentDTO.getMonitors().size(); i++){
                addMonitor(campamentDTO.getId(), campamentDTO.getMonitors().get(i).getID());
            }

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
            Connection conn = new ConnectionDB().getConnection();

            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("INSERT_ACTIVITY_CAMPAMENT");
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, activityId);
            ps.setInt(2, campId);

            ps.execute();

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
            Connection conn = new ConnectionDB().getConnection();

            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("INSERT_MONITOR_CAMPAMENT");
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, campId);
            ps.setInt(2, monitorId);

            ps.execute();

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
            Connection conn = new ConnectionDB().getConnection();

            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("GET_ESPECIAL_ASISTANT");
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, campId);

            ResultSet  rs = ps.executeQuery();

            return rs.next();


        } catch (Exception e) { throw e; }
    }

    @Override
    public CampamentDTO getById(Integer id) throws Exception{
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("GETBYID_CAMPAMENT");

            Connection conn = new ConnectionDB().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            
            CampamentDTO camp = new CampamentDTO();
            
            while(rs.next()){
                camp.setId(rs.getInt("camp_id"));
                camp.setInitDate(rs.getDate("start_date").toLocalDate());
                camp.setFinalDate(rs.getDate("end_date").toLocalDate());
                camp.setMaxAssistants(rs.getInt("max_assistant"));
                camp.setLevel(Level.valueOf(rs.getString("educate_level")));
            }

            camp.setActivities(getActivitiesFromCampament(id));
            camp.setMonitors(getMonitorsFromCampament(id));
            return camp;
        } catch (Exception e) {throw e;}
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

            Connection conn = new ConnectionDB().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            ArrayList<MonitorDTO> monitors = new ArrayList<MonitorDTO>();

            while(rs.next()){
                monitors.add(new MonitorDTO(rs.getInt("monitor_id"), rs.getString("name"), 
                            rs.getString("surname"), rs.getBoolean("special_edu")));
            }

            return monitors;
        } catch (Exception e) {throw e;}
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

            Connection conn = new ConnectionDB().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            ArrayList<ActivityDTO> activities = new ArrayList<ActivityDTO>();

            while(rs.next()){
                ActivityDTO act = new ActivityDTO();

                act.setname(rs.getString("name"));
                act.setMaxParticipants(rs.getInt("max_participants"));
                act.setNumMonitors(rs.getInt("num_monitors"));

                String levelString = rs.getString("education_level");
                if(levelString.equals("CHILD")){
                    act.setLevel(Level.CHILD);
                }else if(levelString.equals("TEENAGER")){
                    act.setLevel(Level.TEENAGER);
                }else{
                    act.setLevel(Level.YOUTH);
                }

                String scheduleString = rs.getString("schedule");
                if(scheduleString.equals("MORNING")){
                    act.setSchedule(Schedule.MORNING);
                }else{
                    act.setSchedule(Schedule.AFTERNOON);
                }

                activities.add(act);
            }

            return activities;
        } catch (Exception e) {throw e;}
    }

    @Override
    public void update(CampamentDTO campamentDTO) throws Exception { throw new UnsupportedOperationException("Unimplemented method 'update'"); }
    
    @Override
    public ArrayList<CampamentDTO> getAll() throws Exception{
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("GETALL_CAMPAMENTS");

            Connection conn = new ConnectionDB().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

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

            return campaments;
        } catch (Exception e) {throw e;}
    }



}