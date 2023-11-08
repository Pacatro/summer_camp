package es.uco.pw.data.dao.inscription;

import java.io.FileInputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.business.factory.CompleteInscriptionDTO;
import es.uco.pw.data.common.ConnectionDB;
import es.uco.pw.data.dao.common.IDAO;

public class CompleteInscriptionDAO implements IDAO<CompleteInscriptionDTO, Integer>{
    
    public CompleteInscriptionDAO(){}
    
    @Override
    public void insert(CompleteInscriptionDTO completeInscriptionDTO) throws Exception {
        Properties sqlProperties = new Properties();
        sqlProperties.load(new FileInputStream("sql.properties"));

        try {
            Connection conn = new ConnectionDB().getConnection();

            String sql = sqlProperties.getProperty("INSERT_INSCRIPTION");
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, completeInscriptionDTO.getIdParticipant());
            ps.setString(2, "Completa");
            ps.setString(3, completeInscriptionDTO.getDate().toString());
            ps.setBoolean(4, completeInscriptionDTO.getCancellation());
            ps.setDouble(5, completeInscriptionDTO.getPrice());
            ps.setString(6, completeInscriptionDTO.getSchendule().toString());
            ps.setInt(7, completeInscriptionDTO.getIdCampament());

            ps.execute();

        } catch (Exception e) { throw e; }
    }

    @Override
    public ArrayList<CompleteInscriptionDTO> getAll() throws Exception { throw new UnsupportedOperationException("Unimplemented method 'getAll'"); }

    @Override
    public CompleteInscriptionDTO getById(Integer id) throws Exception { throw new UnsupportedOperationException("Unimplemented method 'getById'"); }

    @Override
    public void update(CompleteInscriptionDTO dto) throws Exception { throw new UnsupportedOperationException("Unimplemented method 'update'"); }
}
