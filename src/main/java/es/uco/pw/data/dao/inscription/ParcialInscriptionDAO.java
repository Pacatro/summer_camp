package es.uco.pw.data.dao.inscription;

import java.io.FileInputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.business.factory.ParcialInscriptionDTO;
import es.uco.pw.data.common.ConnectionDB;
import es.uco.pw.data.common.DataException;
import es.uco.pw.data.common.IDAO;

/**
 * This class represents a Data Access Object (DAO) for managing ParcialInscriptionDTO objects.
 * It provides methods to interact with the database for parcial inscriptions.
 */
public class ParcialInscriptionDAO implements IDAO<ParcialInscriptionDTO, Integer> {
    
    /**
     * Default constructor for the ParcialInscriptionDAO class.
     */
    public ParcialInscriptionDAO(){}

    @Override
    public void insert(ParcialInscriptionDTO parcialInscriptionDTO) throws Exception {
        Properties sqlProperties = new Properties();
        sqlProperties.load(new FileInputStream("sql.properties"));

        try {
            ConnectionDB connDB = new ConnectionDB();

            Connection conn = connDB.getConnection();

            String sql = sqlProperties.getProperty("INSERT_INSCRIPTION");
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
    public ArrayList<ParcialInscriptionDTO> getAll() throws Exception { throw new UnsupportedOperationException("Unimplemented method 'getAll'"); }

    @Override
    public ParcialInscriptionDTO getById(Integer id) throws Exception { throw new UnsupportedOperationException("Unimplemented method 'getById'"); }

    @Override
    public void update(ParcialInscriptionDTO dto) throws Exception { throw new UnsupportedOperationException("Unimplemented method 'update'"); }
}
