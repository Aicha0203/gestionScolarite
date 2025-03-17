package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Facture;

public class FactureDAO {

    public List<Facture> getFacturesByEtudiant(int etudiantId) {
        List<Facture> factures = new ArrayList<>();
        String sql = "SELECT * FROM factures WHERE etudiant_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, etudiantId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Facture facture = new Facture();
                facture.setId(rs.getInt("id"));
                facture.setEtudiantId(rs.getInt("etudiant_id"));
                facture.setTypeFraisId(rs.getInt("type_frais_id"));
                facture.setMontant(rs.getDouble("montant"));
                facture.setDateCreation(rs.getDate("date_creation"));
                facture.setDateEcheance(rs.getDate("date_echeance"));
                facture.setStatut(rs.getString("statut"));
                factures.add(facture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return factures;
    }

    public boolean ajouterFacture(Facture facture) {
        String sql = "INSERT INTO factures (etudiant_id, type_frais_id, montant, date_creation, date_echeance, statut) VALUES (?, ?, ?, NOW(), ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, facture.getEtudiantId());
            pstmt.setInt(2, facture.getTypeFraisId());
            pstmt.setDouble(3, facture.getMontant());
            pstmt.setDate(4, new java.sql.Date(facture.getDateEcheance().getTime()));
            pstmt.setString(5, facture.getStatut());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
