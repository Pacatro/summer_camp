package es.uco.pw.data.dao.monitor;

import java.util.ArrayList;
import java.util.Properties;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import es.uco.pw.business.monitor.MonitorDTO;
import es.uco.pw.data.common.ConnectionDB;
import es.uco.pw.data.common.DataException;
import es.uco.pw.data.common.IDAO;

/**
 * Manage the data from the monitors table
 */
public class MonitorDAO implements IDAO<MonitorDTO,Integer>{
    public MonitorDAO(){}

    @Override
    public void insert(MonitorDTO monitor) throws Exception{
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("INSERT_MONITOR");

            Connection conn = new ConnectionDB().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, monitor.getID());
            ps.setString(2, monitor.getName());
            ps.setString(3, monitor.getSurname());
            ps.setBoolean(4, monitor.isEspecial());

            ps.execute();

        } catch (Exception e){throw new DataException("No se puede insertar el monitor.");}
    }

    @Override
    public MonitorDTO getById(Integer id) throws Exception{
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("GETBYID_MONITOR");

            Connection conn = new ConnectionDB().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            return (new MonitorDTO(rs.getInt("monitor_id"), rs.getString("name"), 
                                   rs.getString("surname"), rs.getBoolean("special_edu")));
            }
        } catch (Exception e) {throw new DataException("No existe ningun monitor con id " + id + ".");}
        return null;
    }

    @Override
    public ArrayList<MonitorDTO> getAll() throws Exception{
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("sql.properties"));
            String sql = properties.getProperty("GETALL_MONITOR");

            Connection conn = new ConnectionDB().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            ArrayList<MonitorDTO> monitors = new ArrayList<MonitorDTO>();

            while(rs.next()){
                monitors.add(new MonitorDTO(rs.getInt("monitor_id"), rs.getString("name"), 
                                            rs.getString("surname"), rs.getBoolean("special_edu")));
            }

            return monitors;
        } catch (Exception e) {throw new DataException("No hay monitores registrados.");}
    }

    @Override
    public void update(MonitorDTO dto) throws Exception{
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
