package es.uco.pw.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import es.uco.pw.business.factory.CompleteInscriptionDTO;
import es.uco.pw.business.factory.ParcialInscriptionDTO;
import es.uco.pw.data.common.ConnectionDB;

public class InscriptionDAO {
    public InscriptionDAO(){}
    
    public void insert(CompleteInscriptionDTO completeInscriptionDTO) throws Exception {
        try {
            Connection conn = new ConnectionDB().getConnection();

            String sql = "INSERT INTO inscription (ass_id, type, date, price, camp_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, completeInscriptionDTO.getIdParticipant());
            ps.setString(2, "Completa");
            ps.setString(3, completeInscriptionDTO.getDate().toString());
            ps.setDouble(4, completeInscriptionDTO.getPrice());
            ps.setInt(5, completeInscriptionDTO.getIdCampament());

            ps.executeQuery();
        } catch (Exception e) { throw e; }
    }

    public void insert(ParcialInscriptionDTO parcialInscriptionDTO) throws Exception {
        try {
            Connection conn = new ConnectionDB().getConnection();

            String sql = "INSERT INTO inscription (ass_id, type, date, price, camp_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, parcialInscriptionDTO.getIdParticipant());
            ps.setString(2, "Parcial");
            ps.setString(3, parcialInscriptionDTO.getDate().toString());
            ps.setDouble(4, parcialInscriptionDTO.getPrice());
            ps.setInt(5, parcialInscriptionDTO.getIdCampament());

            ps.executeQuery();
        } catch (Exception e) { throw e; }
    }
}
