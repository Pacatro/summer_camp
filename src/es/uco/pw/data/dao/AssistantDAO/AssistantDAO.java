package es.uco.pw.data.dao.AssistantDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.data.common.ConnectionDB;
import es.uco.pw.data.common.IDAO;

public class AssistantDAO implements IDAO<AssistantDTO> {
    public AssistantDAO(){}

    @Override
    public void insert(AssistantDTO assistantDTO) throws Exception{
        try{
            Connection conn = new ConnectionDB().getConnection();

            String sql= "INSERT INTO assistants (assi_id, name, surname, birth_date, attention) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, assistantDTO.getId());
            ps.setString(2, assistantDTO.getName());
            ps.setString(3, assistantDTO.getSurname());
            ps.setString(4, assistantDTO.getDate().toString());
            ps.setBoolean(5,assistantDTO.getAtention());

            ps.execute();

        } catch (Exception e) { throw e; }
    }

    @Override
    public AssistantDTO getById(int idDTO) throws Exception{
        AssistantDTO assi=new AssistantDTO();
        try{
            Connection conn = new ConnectionDB().getConnection();
            
            String sql = "SELECT * FROM assistants WHERE assi_id= ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idDTO);

            ResultSet rs=(ResultSet) ps.executeQuery(sql);
            while (rs.next()) {
				assi.setName(rs.getString("name"));
                assi.setSurname(rs.getString("surname"));
                assi.setDate(rs.getDate("birth_date").toLocalDate());
                assi.setAtention(rs.getBoolean("attention"));
			}
            

        } catch (Exception e) { throw e; }
        return assi;
    }

    @Override
    public void update(AssistantDTO assistantDTO) throws Exception{
        try{
            Connection conn = new ConnectionDB().getConnection();

            String sql= "UPDATE assistants SET name, surname, birth_date, attention WHERE assi_id= ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, assistantDTO.getId());

            ps.execute();
            
        } catch (Exception e) { throw e; }
    }

    @Override
    public ArrayList<AssistantDTO> getAll() throws Exception { 
        ArrayList<AssistantDTO> listofassi = new ArrayList<AssistantDTO>();
        try{
            Connection conn = new ConnectionDB().getConnection();

            String sql = "SELECT * FROM assistants";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = (ResultSet) ps.executeQuery(sql);

            while (rs.next()) {
                int assist_id=rs.getInt("assi_id");
                String name_c=rs.getString("name");
                String surname_c=rs.getString("surname");
                LocalDate birth_c=rs.getDate("birth_date").toLocalDate();
                Boolean attention_c=rs.getBoolean("attention");
				listofassi.add(new AssistantDTO(assist_id, name_c, surname_c, birth_c, attention_c));
			}


        } catch (Exception e) { throw e; }
        return listofassi;
    }


}
