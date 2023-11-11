package es.uco.pw.data.dao.assistant;

import java.io.FileInputStream;
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
    public AssistantDAO(){}

    @Override
    public void insert(AssistantDTO assistantDTO) throws Exception{
        try{
            Properties properties= new Properties();
            properties.load(new FileInputStream("sql.properties"));
            Connection conn = new ConnectionDB().getConnection();

            String sql= properties.getProperty("INSERT_ASSISTANT");
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, assistantDTO.getId());
            ps.setString(2, assistantDTO.getName());
            ps.setString(3, assistantDTO.getSurname());
            ps.setString(4, assistantDTO.getDate().toString());
            ps.setBoolean(5,assistantDTO.getAtention());

            ps.execute();

        } catch (Exception e) { throw new DataException("No se puedo insertar el asistente."); }
    }

    @Override
    public AssistantDTO getById(Integer idDTO) throws Exception{
        try{
            Properties properties= new Properties();
            properties.load(new FileInputStream("sql.properties"));
            Connection conn = new ConnectionDB().getConnection();

            String sql= properties.getProperty("GETBYID_ASSISTANT");
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idDTO.intValue());
            
            ResultSet rs = ps.executeQuery();
            
            AssistantDTO assi=new AssistantDTO();
            while (rs.next()) {
                assi.setId(rs.getInt("ass_id"));
				assi.setName(rs.getString("name"));
                assi.setSurname(rs.getString("surname"));
                assi.setDate(rs.getDate("birth_date").toLocalDate());
                assi.setAtention(rs.getBoolean("attention"));
			}
            
            return assi;

        } catch (Exception e) { throw new DataException("No existe ningun asistente con el id " + idDTO +  "."); }
    }

    @Override
    public void update(AssistantDTO assistantDTO) throws Exception{
        try{
            Properties properties= new Properties();
            properties.load(new FileInputStream("sql.properties"));
            Connection conn = new ConnectionDB().getConnection();

            String sql= properties.getProperty("UPDATE_ASSISTANT");
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, assistantDTO.getName());
            ps.setString(2, assistantDTO.getSurname());
            ps.setString(3, assistantDTO.getDate().toString());
            ps.setBoolean(4,assistantDTO.getAtention());
            ps.setInt(5, assistantDTO.getId());

            ps.execute();
            
        } catch (Exception e) { throw new DataException("No se pudo actualizar la informacion del asistente."); }
    }

    @Override
    public ArrayList<AssistantDTO> getAll() throws Exception { 
        try{
            Properties properties= new Properties();
            properties.load(new FileInputStream("sql.properties"));
            Connection conn = new ConnectionDB().getConnection();
            
            String sql= properties.getProperty("GETALL_ASSISTANT");
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            ArrayList<AssistantDTO> listofassi = new ArrayList<AssistantDTO>();

            while (rs.next()) {
                int assist_id=rs.getInt("ass_id");
                String name_c=rs.getString("name");
                String surname_c=rs.getString("surname");
                LocalDate birth_c=rs.getDate("birth_date").toLocalDate();
                Boolean attention_c=rs.getBoolean("attention");
				listofassi.add(new AssistantDTO(assist_id, name_c, surname_c, birth_c, attention_c));
			}

            return listofassi;

        } catch (Exception e) { throw new DataException("No hay ningun asistente registrado"); }
    }


}
