package es.uco.pw.data.dao.activity;

import java.util.ArrayList;
import java.util.Properties;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import es.uco.pw.business.activity.ActivityDTO;
import es.uco.pw.business.common.level.Level;
import es.uco.pw.business.common.schedule.Schedule;
import es.uco.pw.business.monitor.MonitorDTO;
import es.uco.pw.data.common.ConnectionDB;
import es.uco.pw.data.dao.common.IDAO;
import es.uco.pw.data.dao.monitor.MonitorDAO;


/**
 * Manage the data from the activities table
 */
public class ActivityDAO implements IDAO<ActivityDTO,String>{
    public ActivityDAO(){}

    @Override
    public void insert(ActivityDTO activity) throws Exception{
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("INSERT_ACTIVITY");

            Connection conn = new ConnectionDB().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, activity.getname());
            ps.setString(2, activity.getLevel().toString());
            ps.setString(3, activity.getSchedule().toString());
            ps.setInt(4, activity.getMaxParticipants());
            ps.setInt(5, activity.getNumMonitors());

            ps.execute();

            sql = properties.getProperty("INSERT_ACTIVITY_MONITOR");
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, activity.getname());
            
            for(MonitorDTO m: activity.getMonitors()){
                ps.setInt(2, m.getID());
                ps.execute();
            }

            
        } catch (Exception e) {throw e;}
    }

    /**
     * Adds a monitor to an activity in the database.
     *
     * @param act_id The identifier of the activity to which the monitor is added.
     * @param mon_id The identifier of the monitor being added to the activity.
     */
    public void addMonitor(String act_id, int mon_id) throws Exception{
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("INSERT_ACTIVITY_MONITOR");

            Connection conn = new ConnectionDB().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, act_id);
            ps.setInt(2, mon_id);

            ps.execute();

        } catch(Exception e) {throw e;}
    }

    /**
     * Retrieves a list of monitors associated with a specific activity.
     *
     * @param act_id The identifier of the activity for which monitors are retrieved.
     * @return An ArrayList of MonitorDTO objects representing the monitors associated with the activity.
     */
    public ArrayList<MonitorDTO> getMonitors(String act_id) throws Exception{
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("GETBYID_MONITOR_IN_ACTIVITY");
            
            Connection conn = new ConnectionDB().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, act_id);
            
            ArrayList<MonitorDTO> monitors = new ArrayList<MonitorDTO>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                MonitorDAO monitorDAO = new MonitorDAO();
                monitors.add(monitorDAO.getById(rs.getInt("monitor_id")));
            }

            return monitors;

        } catch(Exception e) {throw e;}
    }

    /**
     * Checks if the maximum number of monitors for a specific activity has been reached.
     *
     * @param act_id The identifier of the activity to check.
     * @return True if the maximum number of monitors has been reached, false otherwise.
     */
    public boolean isMonitorsFull(String act_id) throws Exception{
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("GET_NUMMONITORS_ACTIVITY");

            Connection conn = new ConnectionDB().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, act_id);
            ResultSet rs = ps.executeQuery();

            ActivityDTO activity = getById(act_id);

            while(rs.next()){
                if(activity.getNumMonitors() <= rs.getInt("count(monitor_id)")){
                    return true;
                }
            }

            return false;

        } catch(Exception e) {throw e;}
    }

    @Override
    public ArrayList<ActivityDTO> getAll() throws Exception{
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("GETALL_ACTIVITY");

            Connection conn = new ConnectionDB().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

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
    public ActivityDTO getById(String id) throws Exception{
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("GETBYID_ACTIVITY");

            Connection conn = new ConnectionDB().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            ActivityDTO act = new ActivityDTO();

            while(rs.next()){
                act.setname(rs.getString("name"));
                act.setMaxParticipants(rs.getInt("max_participants"));
                act.setNumMonitors(rs.getInt("num_monitors"));

                String levelString = rs.getString("education_level");
                act.setLevel(Level.valueOf(levelString));

                String scheduleString = rs.getString("schedule");
                act.setSchedule(Schedule.valueOf(scheduleString));
            }

            return act;
        } catch (Exception e) {throw e;}
    }

    @Override
    public void update(ActivityDTO dto) throws Exception{
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
