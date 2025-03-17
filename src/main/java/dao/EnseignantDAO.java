package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Enseignant;
import models.Role;

public class EnseignantDAO {
    public boolean enregistrerOuMettreAJourEnseignant(int utilisateurId, Enseignant enseignant) {
        if (enseignantExiste(utilisateurId)) {
            return mettreAJourEnseignant(utilisateurId, enseignant);
        } else {
            return ajouterEnseignant(utilisateurId, enseignant);
        }
    }

    private boolean enseignantExiste(int utilisateurId) {
        String sql = "SELECT id FROM enseignants WHERE utilisateur_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, utilisateurId);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ajouterEnseignant(int utilisateurId, Enseignant enseignant) {
        String sql = "INSERT INTO enseignants (utilisateur_id, matricule, prenom, nom, specialite) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, utilisateurId);
            statement.setString(2, enseignant.getMatricule());
            statement.setString(3, enseignant.getPrenom());
            statement.setString(4, enseignant.getNom());
            statement.setString(5, enseignant.getSpecialite());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'enseignant : " + e.getMessage());
            return false;
        }
    }

    public boolean mettreAJourEnseignant(int utilisateurId, Enseignant enseignant) {
        String sql = "UPDATE enseignants SET matricule=?, prenom=?, nom=?, telephone=?, specialite=? WHERE utilisateur_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, enseignant.getMatricule());
            pstmt.setString(2, enseignant.getPrenom());
            pstmt.setString(3, enseignant.getNom());
            pstmt.setString(4, enseignant.getTelephone());
            pstmt.setString(5, enseignant.getSpecialite());
            pstmt.setInt(6, utilisateurId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Enseignant getEnseignantById(int id) {
        String sql = "SELECT e.*, u.username, u.email, u.mot_de_passe, u.role, u.photo " +
                "FROM enseignants e JOIN utilisateurs u ON e.utilisateur_id = u.id " +
                "WHERE u.id = ?";  // 📌 Correction ici pour récupérer l'utilisateur correctement
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Enseignant(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        Role.valueOf(rs.getString("role")),
                        rs.getString("matricule"),
                        rs.getString("prenom"),
                        rs.getString("nom"),
                        rs.getString("telephone"),
                        rs.getString("specialite"),
                        rs.getString("photo")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération de l'enseignant : " + e.getMessage());
        }
        return null;
    }
}
