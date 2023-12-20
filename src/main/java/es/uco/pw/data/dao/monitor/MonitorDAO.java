package es.uco.pw.data.dao.monitor;

import java.util.ArrayList;
import java.util.Properties;
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
    private Properties sql_properties;
    private Properties config_properties;
    
    public MonitorDAO(Properties sql_properties, Properties config_properties){
        this.sql_properties = sql_properties;
        this.config_properties = config_properties;
    }

    @Override
    public void insert(MonitorDTO monitor) throws Exception{
        try{
            String sql = sql_properties.getProperty("INSERT_MONITOR");

            ConnectionDB connDB = new ConnectionDB(config_properties);
            Connection conn = connDB.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, monitor.getID());
            ps.setString(2, monitor.getName());
            ps.setString(3, monitor.getSurname());
            ps.setBoolean(4, monitor.isEspecial());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected <= 0)
                throw new DataException("No se puede insertar el monitor.");
          
            connDB.disconnect();
        } catch (Exception e){ throw e; }
    }

    @Override
    public MonitorDTO getById(Integer id) throws Exception{
        try{
            String sql = sql_properties.getProperty("GETBYID_MONITOR");

            ConnectionDB connDB = new ConnectionDB(config_properties);

            Connection conn = connDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            if(!ps.execute())
                throw new DataException("No existe ningun monitor con id " + id + ".");

            MonitorDTO monitor = null;

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                monitor = new MonitorDTO(rs.getInt("monitor_id"), rs.getString("name"), 
                                   rs.getString("surname"), rs.getBoolean("special_edu"));
            }

            connDB.disconnect();

            return monitor;
        } catch (Exception e) { throw e; }
    }

    @Override
    public ArrayList<MonitorDTO> getAll() throws Exception{
        try{
            String sql = sql_properties.getProperty("GETALL_MONITOR");

            ConnectionDB connDB = new ConnectionDB(config_properties);

            Connection conn = connDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            if(!ps.execute())
                throw new DataException("No hay monitores registrados.");

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

    @Override
    public void update(MonitorDTO dto) throws Exception{
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(MonitorDTO dto) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
