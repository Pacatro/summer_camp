package es.uco.pw.data.dao.inscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.business.common.schedule.Schedule;
import es.uco.pw.business.factory.CompleteInscriptionDTO;
import es.uco.pw.data.common.ConnectionDB;
import es.uco.pw.data.common.DataException;
import es.uco.pw.data.common.IDAO;

/**
 * This class represents a Data Access Object (DAO) for managing CompleteInscriptionDTO objects.
 * It provides methods to interact with the database for complete inscriptions.
 */
public class CompleteInscriptionDAO implements IDAO<CompleteInscriptionDTO, Integer>{
    private Properties sql_properties;
    private Properties config_properties;

    /**
     * Default constructor for the CompleteInscriptionDAO class.
     */
    public CompleteInscriptionDAO(Properties sql_properties, Properties config_properties){
        this.sql_properties = sql_properties;
        this.config_properties = config_properties;
    }

    public CompleteInscriptionDTO getByIds(int campId, int assId) throws Exception {
        try {

            CompleteInscriptionDTO completeInscriptionDTO = null;
            
            ConnectionDB connDB = new ConnectionDB(config_properties);
            Connection conn = connDB.getConnection();
    
            String sql = sql_properties.getProperty("GET_BYASSID_CAMPID_COMPLETE_INSCRIPTIONS");
            PreparedStatement ps = conn.prepareStatement(sql);
    
            ps.setInt(1, campId);        
            ps.setInt(2, assId);
    
            if(!ps.execute())
                throw new DataException("No existe esa inscripcion.");
    
            ResultSet rs = ps.executeQuery();
    
            while(rs.next()) {
                completeInscriptionDTO = new CompleteInscriptionDTO();
                completeInscriptionDTO.setDate(rs.getDate("date").toLocalDate());
                completeInscriptionDTO.setCancellation(rs.getBoolean("cancelled"));
                completeInscriptionDTO.setPrice(rs.getDouble("price"));
                completeInscriptionDTO.setSchedule(Schedule.valueOf(rs.getString("schendule")));
                completeInscriptionDTO.setIdCampament(campId);
                completeInscriptionDTO.setIdParticipant(assId);
            }

            connDB.disconnect();
    
            return completeInscriptionDTO;
        } catch (Exception e) { throw e; }
    }

    @Override
    public void insert(CompleteInscriptionDTO completeInscriptionDTO) throws Exception {
        try {
            ConnectionDB connDB = new ConnectionDB(config_properties);
            Connection conn = connDB.getConnection();

            String sql = sql_properties.getProperty("INSERT_INSCRIPTION");
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, completeInscriptionDTO.getIdParticipant());
            ps.setString(2, "COMPLETE");
            ps.setString(3, completeInscriptionDTO.getDate().toString());
            ps.setBoolean(4, completeInscriptionDTO.getCancellation());
            ps.setDouble(5, completeInscriptionDTO.getPrice());
            ps.setString(6, completeInscriptionDTO.getSchedule().toString());
            ps.setInt(7, completeInscriptionDTO.getIdCampament());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected <= 0)
                throw new DataException("No se puede insertar la inscripcion");

            connDB.disconnect();
            
        } catch (Exception e) { throw e; }
    }

    @Override
    public void delete(CompleteInscriptionDTO dto) throws Exception {
        ConnectionDB connDB = new ConnectionDB(config_properties);
        Connection conn = connDB.getConnection();

        String sql = sql_properties.getProperty("DELETE_COMPLETE_INSCRIPTION");
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, dto.getIdCampament());
        ps.setInt(2, dto.getIdParticipant());

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected <= 0)
            throw new DataException("No se puede eliminar la inscripcion");

        connDB.disconnect();
    }

    @Override
    public ArrayList<CompleteInscriptionDTO> getAll() throws Exception { throw new UnsupportedOperationException("Unimplemented method 'getAll'"); }

    @Override
    public CompleteInscriptionDTO getById(Integer id) throws Exception { throw new UnsupportedOperationException("Unimplemented method 'getById'"); }

    @Override
    public void update(CompleteInscriptionDTO dto) throws Exception { throw new UnsupportedOperationException("Unimplemented method 'update'"); }
}
