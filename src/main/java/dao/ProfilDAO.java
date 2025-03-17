package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Diplome;
import models.Genre;
import models.Nationalite;
import models.Pays;

public class ProfilDAO {
    public List<Genre> getGenres() {
        List<Genre> genres = new ArrayList<>();
        String sql = "SELECT * FROM genres";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                genres.add(new Genre(rs.getInt("id"), rs.getString("libelle")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genres;
    }

    public List<Pays> getPays() {
        List<Pays> paysList = new ArrayList<>();
        String sql = "SELECT * FROM pays";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                paysList.add(new Pays(rs.getInt("id"), rs.getString("nom")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paysList;
    }

    public List<Nationalite> getNationalites() {
        List<Nationalite> nationalites = new ArrayList<>();
        String sql = "SELECT * FROM nationalites";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                nationalites.add(new Nationalite(rs.getInt("id"), rs.getString("libelle")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nationalites;
    }

    public List<Diplome> getDiplomes() {
        List<Diplome> diplomes = new ArrayList<>();
        String sql = "SELECT * FROM diplomes";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                diplomes.add(new Diplome(rs.getInt("id"), rs.getString("libelle")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diplomes;
    }
    
    public boolean ajouterEtudiant(int utilisateurId, String matricule, int genreId, Date dateNaissance, int paysNaissanceId, int nationaliteId, String telephone, String adresse, int dernierDiplomeId) {
        String sql = "INSERT INTO etudiants (utilisateur_id, matricule, genre_id, date_naissance, pays_naissance_id, nationalite_id, telephone, adresse_complete, dernier_diplome_id, date_inscription) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, utilisateurId);
            pstmt.setString(2, matricule);
            pstmt.setInt(3, genreId);
            pstmt.setDate(4, dateNaissance);
            pstmt.setInt(5, paysNaissanceId);
            pstmt.setInt(6, nationaliteId);
            pstmt.setString(7, telephone);
            pstmt.setString(8, adresse);
            pstmt.setInt(9, dernierDiplomeId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean ajouterEnseignant(int utilisateurId, String matricule, String prenom, String nom, String email, String telephone, String specialite) {
        String sql = "INSERT INTO enseignants (utilisateur_id, matricule, prenom, nom, email, telephone, specialite) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, utilisateurId);
            pstmt.setString(2, matricule);
            pstmt.setString(3, prenom);
            pstmt.setString(4, nom);
            pstmt.setString(5, email);
            pstmt.setString(6, telephone);
            pstmt.setString(7, specialite);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
