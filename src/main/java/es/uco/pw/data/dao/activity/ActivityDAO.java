package es.uco.pw.data.dao.activity;

import java.util.ArrayList;
import java.util.Properties;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import es.uco.pw.business.activity.ActivityDTO;
import es.uco.pw.business.common.level.Level;
import es.uco.pw.business.common.schedule.Schedule;
import es.uco.pw.business.monitor.MonitorDTO;
import es.uco.pw.data.common.ConnectionDB;
import es.uco.pw.data.common.DataException;
import es.uco.pw.data.common.IDAO;
import es.uco.pw.data.dao.monitor.MonitorDAO;

/**
 * Manage the data from the activities table
 */
public class ActivityDAO implements IDAO<ActivityDTO,String>{
    private Properties sql_properties;
    private Properties config_properties;
    
    public ActivityDAO(Properties sql_properties, Properties config_properties){
        this.sql_properties = sql_properties;
        this.config_properties = config_properties;
    }

    @Override
    public void insert(ActivityDTO activity) throws Exception{
        try{
            String sql = sql_properties.getProperty("INSERT_ACTIVITY");

            ConnectionDB connDB = new ConnectionDB(config_properties);

            Connection conn = connDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, activity.getname());
            ps.setString(2, activity.getLevel().toString());
            ps.setString(3, activity.getSchedule().toString());
            ps.setInt(4, activity.getMaxParticipants());
            ps.setInt(5, activity.getNumMonitors());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected <= 0)
                throw new DataException("No se puede insertar la actividad.");

            sql = sql_properties.getProperty("INSERT_ACTIVITY_MONITOR");
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, activity.getname());
            
            for(MonitorDTO m: activity.getMonitors()){
                ps.setInt(2, m.getID());
                
                rowsAffected = ps.executeUpdate();

                if (rowsAffected <= 0)
                    throw new DataException("No se puede insertar el monitor.");
            }

            connDB.disconnect();

        } catch (Exception e) { throw e; }
    }

    /**
     * Adds a monitor to an activity in the database.
     *
     * @param act_id The identifier of the activity to which the monitor is added.
     * @param mon_id The identifier of the monitor being added to the activity.
     */
    public void addMonitor(String act_id, int mon_id) throws Exception{
        try {
            String sql = sql_properties.getProperty("INSERT_ACTIVITY_MONITOR");

            ConnectionDB connDB = new ConnectionDB(config_properties);

            Connection conn = connDB.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, act_id);
            ps.setInt(2, mon_id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected <= 0)
                throw new DataException("No se puede asociar el monitor " + mon_id + " a la actividad " + act_id + ".");

        } catch(Exception e) { throw e; }
    }

    /**
     * Retrieves a list of monitors associated with a specific activity.
     *
     * @param act_id The identifier of the activity for which monitors are retrieved.
     * @return An ArrayList of MonitorDTO objects representing the monitors associated with the activity.
     */
    public ArrayList<MonitorDTO> getMonitors(String act_id) throws Exception{
        
        try{
            String sql = sql_properties.getProperty("GETBYID_MONITOR_IN_ACTIVITY");
            
            ConnectionDB connDB = new ConnectionDB(config_properties);

            Connection conn = connDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, act_id);
            
            ArrayList<MonitorDTO> monitors = new ArrayList<MonitorDTO>();
            
            if(!ps.execute())
                throw new DataException("No hay ningun monitor asociado a la actividad " + act_id + ".");
            
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                MonitorDAO monitorDAO = new MonitorDAO(sql_properties, config_properties);
                monitors.add(monitorDAO.getById(rs.getInt("monitor_id")));
            }

            connDB.disconnect();
            
            return monitors;

        } catch (Exception e) { throw e; }
    }

    /**
     * Checks if the maximum number of monitors for a specific activity has been reached.
     *
     * @param act_id The identifier of the activity to check.
     * @return True if the maximum number of monitors has been reached, false otherwise.
     */
    public boolean isMonitorsFull(String act_id) throws Exception{
        try{
            String sql = sql_properties.getProperty("GET_NUMMONITORS_ACTIVITY");

            ConnectionDB connDB = new ConnectionDB(config_properties);

            Connection conn = connDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, act_id);

            if(!ps.execute())
                throw new DataException("No hay ningun monitor asociado a la actividad " + act_id + ".");
            
            ResultSet rs = ps.executeQuery();

            ActivityDTO activity = getById(act_id);

            while(rs.next()){
                if(activity.getNumMonitors() <= rs.getInt("count(monitor_id)"))
                    return true;
            }

            connDB.disconnect();

            return false;
        } catch(Exception e) { throw e; }

    }

    @Override
    public ArrayList<ActivityDTO> getAll() throws Exception{
        try{
            String sql = sql_properties.getProperty("GETALL_ACTIVITY");

            ConnectionDB connDB = new ConnectionDB(config_properties);

            Connection conn = connDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            if(!ps.execute())
                throw new DataException("No hay ninguna actividad disponible.");
            
            ResultSet rs = ps.executeQuery();

            ArrayList<ActivityDTO> activities = new ArrayList<ActivityDTO>();

            while(rs.next()){
                ActivityDTO act = new ActivityDTO();

                act.setname(rs.getString("name"));
                act.setMaxParticipants(rs.getInt("max_participants"));
                act.setNumMonitors(rs.getInt("num_monitors"));
                act.setLevel(Level.valueOf(rs.getString("education_level")));
                act.setSchedule(Schedule.valueOf(rs.getString("schedule")));

                activities.add(act);
            }

            for(int i = 0; i < activities.size(); i++){
                sql = sql_properties.getProperty("GET_MONITORS_ACTIVITY");
                ps = conn.prepareStatement(sql);
                ps.setString(1, activities.get(i).getname());
                rs = ps.executeQuery();
                
                while(rs.next()){
                    MonitorDTO monitor = new MonitorDTO();
                    monitor.setID(rs.getInt("monitor_id"));
                    monitor.setName(rs.getString("name"));
                    monitor.setSurname(rs.getString("surname"));
                    monitor.setisEspecial(rs.getBoolean("special_edu"));

                    activities.get(i).addMonitor(monitor);
                }
            }

            connDB.disconnect();

            return activities;
        } catch (Exception e) { throw e; }
        
    }

    @Override
    public ActivityDTO getById(String id) throws Exception{
        try{
            String sql = sql_properties.getProperty("GETBYID_ACTIVITY");
            
            ConnectionDB connDB = new ConnectionDB(config_properties);

            Connection conn = connDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            
            if(!ps.execute())
                throw new DataException("No existe ninguna actividad con el id " + id + ".");
            
            ResultSet rs = ps.executeQuery();

            ActivityDTO act = null;

            while(rs.next()){
                act = new ActivityDTO();
                act.setname(rs.getString("name"));
                act.setMaxParticipants(rs.getInt("max_participants"));
                act.setNumMonitors(rs.getInt("num_monitors"));

                String levelString = rs.getString("education_level");
                act.setLevel(Level.valueOf(levelString));

                String scheduleString = rs.getString("schedule");
                act.setSchedule(Schedule.valueOf(scheduleString));
            }

            sql = sql_properties.getProperty("GET_MONITORS_ACTIVITY");
            ps = conn.prepareStatement(sql);
            ps.setString(1, act.getname());
            rs = ps.executeQuery();
            
            while(rs.next()){
                MonitorDTO monitor = new MonitorDTO();
                monitor.setID(rs.getInt("monitor_id"));
                monitor.setName(rs.getString("name"));
                monitor.setSurname(rs.getString("surname"));
                monitor.setisEspecial(rs.getBoolean("special_edu"));

                act.addMonitor(monitor);
            }

            connDB.disconnect();

            return act;
        } catch (Exception e) { throw e; }
        
    }

    @Override
    public void update(ActivityDTO dto) throws Exception{
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(ActivityDTO dto) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
