package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Matiere;

public class CoursDAO {

    public List<Matiere> getAllCours() {
        List<Matiere> cours = new ArrayList<>();
        String sql = "SELECT * FROM matieres";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

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

    public List<Matiere> getCoursByProgramme(int programmeId) {
        List<Matiere> cours = new ArrayList<>();
        String sql = "SELECT * FROM matieres WHERE programme_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, programmeId);
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


    public boolean ajouterCours(Matiere matiere) {
        String sql = "INSERT INTO matieres (code, nom, description, programme_id, specialite_id, type_cours) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, matiere.getCode());
            pstmt.setString(2, matiere.getNom());
            pstmt.setString(3, matiere.getDescription());
            pstmt.setInt(4, matiere.getProgrammeId());
            pstmt.setInt(5, matiere.getSpecialiteId());
            pstmt.setString(6, matiere.getTypeCours());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 
    public boolean mettreAJourCours(Matiere matiere) {
        String sql = "UPDATE matieres SET code = ?, nom = ?, description = ?, programme_id = ?, specialite_id = ?, type_cours = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, matiere.getCode());
            pstmt.setString(2, matiere.getNom());
            pstmt.setString(3, matiere.getDescription());
            pstmt.setInt(4, matiere.getProgrammeId());
            pstmt.setInt(5, matiere.getSpecialiteId());
            pstmt.setString(6, matiere.getTypeCours());
            pstmt.setInt(7, matiere.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 
    public boolean supprimerCours(int id) {
        String sql = "DELETE FROM matieres WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
