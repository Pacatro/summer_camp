package es.uco.pw.data.dao.assistant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import java.sql.ResultSet;

import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.data.common.ConnectionDB;
import es.uco.pw.data.common.DataException;
import es.uco.pw.data.common.IDAO;

/**
 * Manage the data from the assitants table
 */
public class AssistantDAO implements IDAO<AssistantDTO, Integer> {
    private Properties sql_properties;
    private Properties config_properties;
    
    public AssistantDAO(Properties sql_properties, Properties config_properties){
        this.sql_properties = sql_properties;
        this.config_properties = config_properties;
    }

    @Override
    public void insert(AssistantDTO assistantDTO) throws Exception{
        try{
            ConnectionDB connDB = new ConnectionDB(config_properties);

            Connection conn = connDB.getConnection();

            String sql= sql_properties.getProperty("INSERT_ASSISTANT");
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, assistantDTO.getId());
            ps.setString(2, assistantDTO.getName());
            ps.setString(3, assistantDTO.getSurname());
            ps.setString(4, assistantDTO.getDate().toString());
            ps.setBoolean(5,assistantDTO.getAtention());
            ps.setString(6, assistantDTO.getEmail());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected <= 0)
                throw new DataException("No se pudo insertar el asistente.");

            connDB.disconnect();

        } catch (Exception e) { throw e; }
    }

    @Override
    public AssistantDTO getById(Integer idDTO) throws Exception{
        
        try{
            ConnectionDB connDB = new ConnectionDB(config_properties);

            Connection conn = connDB.getConnection();
            
            String sql= sql_properties.getProperty("GETBYID_ASSISTANT");
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idDTO.intValue());
            
            if(!ps.execute())
                throw new DataException("No existe ningun asistente con id: " + idDTO + ".");
            
            ResultSet rs = ps.executeQuery();

            AssistantDTO assi = null;

            while (rs.next())
                assi = new AssistantDTO(rs.getInt("ass_id"), rs.getString("name"), rs.getString("surname"), rs.getDate("birth_date").toLocalDate(), rs.getBoolean("attention"), rs.getString("email"));
			
            connDB.disconnect();

            return assi;
        } catch (Exception e) { throw e; }
        
    }

    @Override
    public void update(AssistantDTO assistantDTO) throws Exception{
        try{
            ConnectionDB connDB = new ConnectionDB(config_properties);

            Connection conn = connDB.getConnection();

            String sql= sql_properties.getProperty("UPDATE_ASSISTANT");
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, assistantDTO.getName());
            ps.setString(2, assistantDTO.getSurname());
            ps.setString(3, assistantDTO.getDate().toString());
            ps.setBoolean(4,assistantDTO.getAtention());
            ps.setString(5, assistantDTO.getEmail());
            ps.setInt(6, assistantDTO.getId());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected <= 0)
                throw new DataException("No se pudo actualizar la informacion del asistente.");
          
            connDB.disconnect();

        } catch (Exception e) { throw e; }
    }

    @Override
    public ArrayList<AssistantDTO> getAll() throws Exception { 
        try{
            ConnectionDB connDB = new ConnectionDB(config_properties);

            Connection conn = connDB.getConnection();
            
            String sql= sql_properties.getProperty("GETALL_ASSISTANT");
            PreparedStatement ps = conn.prepareStatement(sql);
            
            if(!ps.execute())
                throw new DataException("No hay ningun asistente registrado");
            
            ResultSet rs = ps.executeQuery(sql);

            ArrayList<AssistantDTO> listofassi = new ArrayList<AssistantDTO>();

            while (rs.next()) {
                int assist_id=rs.getInt("ass_id");
                String name_c=rs.getString("name");
                String surname_c=rs.getString("surname");
                LocalDate birth_c=rs.getDate("birth_date").toLocalDate();
                Boolean attention_c=rs.getBoolean("attention");
                String email_c=rs.getString("email");
				listofassi.add(new AssistantDTO(assist_id, name_c, surname_c, birth_c, attention_c, email_c));
			}

            connDB.disconnect();
            
            return listofassi;

        } catch (Exception e) { throw e; }
    }

    public AssistantDTO getByEmail(String email) throws Exception{
        ConnectionDB connDB = new ConnectionDB(config_properties);
        Connection conn = connDB.getConnection();
        
        String sql= sql_properties.getProperty("GET_ASSISTANT_EMAIL");
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setString(1, email);
        
        if(!ps.execute())
            throw new DataException("No existe ningun asistente con email: " + email + ".");
        
        ResultSet rs = ps.executeQuery();

        AssistantDTO assi = null;

        while (rs.next())
            assi = new AssistantDTO(rs.getInt("ass_id"), rs.getString("name"), rs.getString("surname"), rs.getDate("birth_date").toLocalDate(), rs.getBoolean("attention"), rs.getString("email"));
        
        connDB.disconnect();

        return assi;
    }

    @Override
    public void delete(AssistantDTO dto) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
