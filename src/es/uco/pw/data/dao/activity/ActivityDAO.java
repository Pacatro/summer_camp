package es.uco.pw.data.dao.activity;

import java.util.ArrayList;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import es.uco.pw.business.activity.ActivityDTO;
import es.uco.pw.business.level.Level;
import es.uco.pw.business.monitor.MonitorDTO;
import es.uco.pw.business.schendule.Schendule;
import es.uco.pw.data.common.ConnectionDB;
import es.uco.pw.data.dao.common.IDAO;

/**
 * Manage the data from the activities table
 */
public class ActivityDAO implements IDAO<ActivityDTO>{
    public ActivityDAO(){}

    @Override
    public void insert(ActivityDTO activity) throws Exception{
        try{
            Connection conn = new ConnectionDB().getConnection();

            String sql = "INSERT INTO activities (name, education_level, schendule, max_participants, num_monitors) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            String level;
            if(activity.getLevel() == Level.CHILD){
                level = "CHILD";
            }else if(activity.getLevel() == Level.TEENAGER){
                level = "TEENAGER";
            }else{
                level = "YOUTH";
            }

            String schendule;
            if(activity.getSchendule() == Schendule.MORNING){
                schendule = "MORNING";
            }else{
                schendule = "AFTERNOON";
            }

            ps.setString(1, activity.getname());
            ps.setString(2, level);
            ps.setString(3, schendule);
            ps.setInt(4, activity.getMaxParticipants());
            ps.setInt(5, activity.getNumMonitors());

            ps.execute();

            sql = "INSERT INTO activities_monitors (act_id, monitor_id) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, activity.getname());
            
            for(MonitorDTO m: activity.getMonitors()){
                ps.setInt(2, m.getID());
                ps.execute();
            }

            
        } catch (Exception e) {throw e;}
    }

    public void addMonitor(String act_id, int mon_id) throws Exception{
        try {
            Connection conn = new ConnectionDB().getConnection();

            String sql = "INSERT INTO activities_monitors (act_id, monitor_id) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, act_id);
            ps.setInt(2, mon_id);

            ps.execute();

        } catch(Exception e) {throw e;}
    }

    public ArrayList<MonitorDTO> getMonitors(String act_id) throws Exception{
        try{
            ArrayList<MonitorDTO> monitors = new ArrayList<MonitorDTO>();

            Connection conn = new ConnectionDB().getConnection();
            String sql = "SELECT monitor_id FROM activities_monitors where act_id=? VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, act_id);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String sql2 = "SELECT * FROM monitors WHERE monitor_id=? VALUES (?)";
                PreparedStatement psMon = conn.prepareStatement(sql2);

                psMon.setInt(1, rs.getInt("monitor_id"));

                ResultSet rsMon = psMon.executeQuery();

                monitors.add(new MonitorDTO(rsMon.getInt("monitor_id"), rsMon.getString("name"), 
                                            rsMon.getString("surname"), rsMon.getBoolean("special_edu")));
            }

            return monitors;

        } catch(Exception e) {throw e;}
    }

    @Override
    public ArrayList<ActivityDTO> getAll() throws Exception{
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public ActivityDTO getById() throws Exception{
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public void update(ActivityDTO dto) throws Exception{
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
