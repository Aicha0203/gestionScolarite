package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Matiere;

public class InscriptionCoursDAO {

    public boolean inscrireEtudiant(int etudiantId, int matiereId) {
        String sql = "INSERT INTO inscriptions_cours (etudiant_id, matiere_id) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, etudiantId);
            pstmt.setInt(2, matiereId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Matiere> getCoursInscrits(int etudiantId) {
        List<Matiere> cours = new ArrayList<>();
        String sql = "SELECT m.* FROM matieres m " +
                     "JOIN inscriptions_cours ic ON m.id = ic.matiere_id " +
                     "WHERE ic.etudiant_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, etudiantId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                cours.add(new Matiere(
                    rs.getInt("id"),
                    rs.getString("code"),
                    rs.getString("nom"),
                    rs.getString("description"),
                    rs.getInt("programme_id"),
                    rs.getInt("specialite_id"),
                    rs.getString("type_cours")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cours;
    }
}
