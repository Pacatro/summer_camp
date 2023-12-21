package es.uco.pw.data.dao.inscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.business.common.schedule.Schedule;
import es.uco.pw.business.factory.ParcialInscriptionDTO;
import es.uco.pw.data.common.ConnectionDB;
import es.uco.pw.data.common.DataException;
import es.uco.pw.data.common.IDAO;

/**
 * This class represents a Data Access Object (DAO) for managing ParcialInscriptionDTO objects.
 * It provides methods to interact with the database for parcial inscriptions.
 */
public class ParcialInscriptionDAO implements IDAO<ParcialInscriptionDTO, Integer> {
    private Properties sql_properties;
    private Properties config_properties;

    /**
     * Default constructor for the ParcialInscriptionDAO class.
     */
    public ParcialInscriptionDAO(Properties sql_properties, Properties config_properties){
        this.sql_properties = sql_properties;
        this.config_properties = config_properties;
    }

    public ParcialInscriptionDTO getByIds(int campId, int assId) throws Exception {
        try {
            ParcialInscriptionDTO parcialInscriptionDTO = null;
            
            ConnectionDB connDB = new ConnectionDB(config_properties);
            Connection conn = connDB.getConnection();
    
            String sql = sql_properties.getProperty("GET_BYASSID_CAMPID_PARCIAL_INSCRIPTIONS");
            PreparedStatement ps = conn.prepareStatement(sql);
    
            ps.setInt(1, campId);        
            ps.setInt(2, assId);
    
            if(!ps.execute())
                throw new DataException("No existe esa inscripcion.");
    
            ResultSet rs = ps.executeQuery();
    
            while(rs.next()) {
                parcialInscriptionDTO = new ParcialInscriptionDTO();
                parcialInscriptionDTO.setDate(rs.getDate("date").toLocalDate());
                parcialInscriptionDTO.setCancellation(rs.getBoolean("cancelled"));
                parcialInscriptionDTO.setPrice(rs.getDouble("price"));
                parcialInscriptionDTO.setSchedule(Schedule.valueOf(rs.getString("schendule")));
                parcialInscriptionDTO.setIdCampament(campId);
                parcialInscriptionDTO.setIdParticipant(assId);
            }

            connDB.disconnect();
    
            return parcialInscriptionDTO;
        } catch (Exception e) { throw e; }
    }

    public ArrayList<ParcialInscriptionDTO> getAllByEmail(String email) throws Exception {
        try {
            ArrayList<ParcialInscriptionDTO> parcialInscriptions = new ArrayList<>();
            ParcialInscriptionDTO parcialInscription = null;
            
            ConnectionDB connDB = new ConnectionDB(config_properties);
            Connection conn = connDB.getConnection();
    
            String sql = sql_properties.getProperty("GET_BYASSID_PARCIAL_INSCRIPTIONS");
            PreparedStatement ps = conn.prepareStatement(sql);
    
            ps.setString(1, email);
    
            if(!ps.execute())
                throw new DataException("No existe esa inscripcion.");
    
            ResultSet rs = ps.executeQuery();
    
            while(rs.next()) {
                parcialInscription = new ParcialInscriptionDTO();
                parcialInscription.setDate(rs.getDate("date").toLocalDate());
                parcialInscription.setCancellation(rs.getBoolean("cancelled"));
                parcialInscription.setPrice(rs.getDouble("price"));
                parcialInscription.setSchedule(Schedule.valueOf(rs.getString("schendule")));
                parcialInscription.setIdCampament(rs.getInt("camp_id"));
                parcialInscription.setIdParticipant(rs.getInt("ass_id"));
                parcialInscriptions.add(parcialInscription);
            }

            connDB.disconnect();
    
            return parcialInscriptions;
        } catch (Exception e) { throw e; }
    }

    @Override
    public void insert(ParcialInscriptionDTO parcialInscriptionDTO) throws Exception {
        try {
            ConnectionDB connDB = new ConnectionDB(config_properties);

            Connection conn = connDB.getConnection();

            String sql = sql_properties.getProperty("INSERT_INSCRIPTION");
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, parcialInscriptionDTO.getIdParticipant());
            ps.setString(2, "PARCIAL");
            ps.setString(3, parcialInscriptionDTO.getDate().toString());
            ps.setBoolean(4, parcialInscriptionDTO.getCancellation());
            ps.setDouble(5, parcialInscriptionDTO.getPrice());
            ps.setString(6, parcialInscriptionDTO.getSchedule().toString());
            ps.setInt(7, parcialInscriptionDTO.getIdCampament());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected <= 0)
                throw new DataException("No se puede insertar la inscripcion");

            connDB.disconnect();
            
        } catch (Exception e) { throw e; }
    }

    @Override
    public void delete(ParcialInscriptionDTO dto) throws Exception {
        try {
            ConnectionDB connDB = new ConnectionDB(config_properties);
            Connection conn = connDB.getConnection();
    
            String sql = sql_properties.getProperty("DELETE_PARCIAL_INSCRIPTION");
            PreparedStatement ps = conn.prepareStatement(sql);
    
            ps.setInt(1, dto.getIdCampament());
            ps.setInt(2, dto.getIdParticipant());

            System.out.println("HOla");
    
            int rowsAffected = ps.executeUpdate();
    
            if (rowsAffected <= 0)
                throw new DataException("No se puede eliminar la inscripcion");
    
            connDB.disconnect();
        } catch (Exception e) { throw e; }
    }

    @Override
    public ArrayList<ParcialInscriptionDTO> getAll() throws Exception { throw new UnsupportedOperationException("Unimplemented method 'getAll'"); }

    @Override
    public ParcialInscriptionDTO getById(Integer id) throws Exception { throw new UnsupportedOperationException("Unimplemented method 'getById'"); }

    @Override
    public void update(ParcialInscriptionDTO dto) throws Exception { throw new UnsupportedOperationException("Unimplemented method 'update'"); }
}
