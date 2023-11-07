package es.uco.pw.data.dao.inscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import es.uco.pw.business.factory.CompleteInscriptionDTO;
import es.uco.pw.data.common.ConnectionDB;
import es.uco.pw.data.common.IDAO;

public class CompleteInscriptionDAO implements IDAO<CompleteInscriptionDTO>{
    @Override
    public void insert(CompleteInscriptionDTO completeInscriptionDTO) throws Exception {
        try {
            Connection conn = new ConnectionDB().getConnection();

            String sql = "INSERT INTO inscriptions (ass_id, type, date, cancelled, price, schendule, camp_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
    public CompleteInscriptionDTO getById(int id) throws Exception { throw new UnsupportedOperationException("Unimplemented method 'getById'"); }

    @Override
    public void update(CompleteInscriptionDTO dto) throws Exception { throw new UnsupportedOperationException("Unimplemented method 'update'"); }
}
