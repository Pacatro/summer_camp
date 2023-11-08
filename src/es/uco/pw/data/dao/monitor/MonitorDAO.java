package es.uco.pw.data.dao.monitor;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import es.uco.pw.business.monitor.MonitorDTO;
import es.uco.pw.data.common.ConnectionDB;
import es.uco.pw.data.dao.common.IDAO;

public class MonitorDAO implements IDAO<MonitorDTO,Integer>{
    public MonitorDAO(){}

    @Override
    public void insert(MonitorDTO monitor) throws Exception{
        try{
            Connection conn = new ConnectionDB().getConnection();
            String sql = "INSERT INTO monitors (monitor_id, name, surname, special_edu) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, monitor.getID());
            ps.setString(2, monitor.getName());
            ps.setString(3, monitor.getSurname());
            ps.setBoolean(4, monitor.isEspecial());

            ps.execute();

        } catch (Exception e){throw e;}
    }

    @Override
    public MonitorDTO getById(Integer id) throws Exception{
        try{
            Connection conn = new ConnectionDB().getConnection();
            String sql2 = "SELECT * FROM monitors WHERE monitor_id=?";
            PreparedStatement ps = conn.prepareStatement(sql2);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            return (new MonitorDTO(rs.getInt("monitor_id"), rs.getString("name"), 
                                   rs.getString("surname"), rs.getBoolean("special_edu")));
        } catch (Exception e) {throw e;}
    }

    @Override
    public ArrayList<MonitorDTO> getAll() throws Exception{
        try{
            Connection conn = new ConnectionDB().getConnection();
            String sql = "SELECT * FROM monitors";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            ArrayList<MonitorDTO> monitors = new ArrayList<MonitorDTO>();

            while(rs.next()){
                monitors.add(new MonitorDTO(rs.getInt("monitor_id"), rs.getString("name"), 
                                            rs.getString("surname"), rs.getBoolean("special_edu")));
            }

            return monitors;
        } catch (Exception e) {throw e;}
    }

    @Override
    public void update(MonitorDTO dto) throws Exception{
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
