package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Paiement;

public class PaiementDAO {
    public List<Paiement> getPaiementsByEtudiant(int etudiantId) {
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM paiements WHERE etudiant_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, etudiantId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                paiements.add(new Paiement(
                    rs.getInt("id"),
                    rs.getInt("etudiant_id"),
                    rs.getInt("facture_id"),
                    rs.getDouble("montant"),
                    rs.getDate("date_paiement"),
                    rs.getInt("mode_paiement_id"),
                    rs.getString("transaction_id"),
                    rs.getString("statut")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paiements;
    }
}
