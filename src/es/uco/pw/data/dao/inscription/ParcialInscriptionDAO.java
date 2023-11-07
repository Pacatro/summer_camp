package es.uco.pw.data.dao.inscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import es.uco.pw.business.factory.ParcialInscriptionDTO;
import es.uco.pw.data.common.ConnectionDB;
import es.uco.pw.data.common.IDAO;

public class ParcialInscriptionDAO implements IDAO<ParcialInscriptionDTO> {
    public ParcialInscriptionDAO(){}

    @Override
    public void insert(ParcialInscriptionDTO parcialInscriptionDTO) throws Exception {
        try {
            Connection conn = new ConnectionDB().getConnection();

            String sql = "INSERT INTO inscriptions (ass_id, type, date, cancelled, price, schendule, camp_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, parcialInscriptionDTO.getIdParticipant());
            ps.setString(2, "Parcial");
            ps.setString(3, parcialInscriptionDTO.getDate().toString());
            ps.setBoolean(4, parcialInscriptionDTO.getCancellation());
            ps.setDouble(5, parcialInscriptionDTO.getPrice());
            ps.setString(6, parcialInscriptionDTO.getSchendule().toString());
            ps.setInt(7, parcialInscriptionDTO.getIdCampament());

            ps.execute();

        } catch (Exception e) { throw e; }
    }

    @Override
    public ArrayList<ParcialInscriptionDTO> getAll() throws Exception { throw new UnsupportedOperationException("Unimplemented method 'getAll'"); }

    @Override
    public ParcialInscriptionDTO getById(int id) throws Exception { throw new UnsupportedOperationException("Unimplemented method 'getById'"); }

    @Override
    public void update(ParcialInscriptionDTO dto) throws Exception { throw new UnsupportedOperationException("Unimplemented method 'update'"); }
}
