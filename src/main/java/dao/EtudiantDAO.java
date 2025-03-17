package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Etudiant;
import models.Role;

public class EtudiantDAO {

	public boolean enregistrerOuMettreAJourEtudiant(int utilisateurId, Etudiant etudiant) {
		if (etudiantExiste(utilisateurId)) {
			return mettreAJourEtudiant(utilisateurId, etudiant);
		} else {
			return ajouterEtudiant(utilisateurId, etudiant);
		}
	}

	private boolean etudiantExiste(int utilisateurId) {
		String sql = "SELECT id FROM etudiants WHERE utilisateur_id = ?";
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

	public boolean ajouterEtudiant(int utilisateurId, Etudiant etudiant) {
		String sql = "INSERT INTO etudiants (utilisateur_id, matricule, prenom, nom, adresse_complete) VALUES (?, ?, ?, ?, ?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, utilisateurId);
			statement.setString(2, etudiant.getMatricule());
			statement.setString(3, etudiant.getPrenom());
			statement.setString(4, etudiant.getNom());
			statement.setString(5, etudiant.getAdresseComplete());

			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Erreur lors de l'ajout de l'étudiant : " + e.getMessage());
			return false;
		}
	}

	public boolean mettreAJourEtudiant(int utilisateurId, Etudiant etudiant) {
		String sql = "UPDATE etudiants SET telephone=?, adresse_complete=? WHERE utilisateur_id=?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, etudiant.getTelephone());
			pstmt.setString(2, etudiant.getAdresseComplete());
			pstmt.setInt(3, utilisateurId);

			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Etudiant> getAllEtudiants() {
		List<Etudiant> etudiants = new ArrayList<>();
		String sql = "SELECT * FROM etudiants";

		try (Connection conn = DatabaseConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Etudiant etudiant = new Etudiant();
				etudiant.setId(rs.getInt("id"));
				etudiant.setMatricule(rs.getString("matricule"));
				etudiant.setPrenom(rs.getString("prenom"));
				etudiant.setNom(rs.getString("nom"));
				etudiant.setTelephone(rs.getString("telephone"));
				etudiant.setAdresseComplete(rs.getString("adresse_complete"));
				etudiants.add(etudiant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return etudiants;
	}

	public boolean updateEtudiant(int id, String telephone, String adresse) {
		String sql = "UPDATE etudiants SET telephone=?, adresse_complete=? WHERE id=?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, telephone);
			pstmt.setString(2, adresse);
			pstmt.setInt(3, id);

			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Etudiant getEtudiantById(int id) {
		String sql = "SELECT e.*, u.username, u.email, u.mot_de_passe, u.role, u.photo " +
				"FROM etudiants e JOIN utilisateurs u ON e.utilisateur_id = u.id " +
				"WHERE u.id = ?";  // Correction pour utiliser utilisateur_id
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return new Etudiant(
						rs.getInt("id"),
						rs.getString("username"),
						rs.getString("email"),
						rs.getString("mot_de_passe"),
						Role.valueOf(rs.getString("role")),
						rs.getString("matricule"),
						rs.getString("prenom"),
						rs.getString("nom"),
						rs.getInt("genre_id"),
						rs.getDate("date_naissance"),
						rs.getInt("pays_naissance_id"),
						rs.getInt("nationalite_id"),
						rs.getString("telephone"),
						rs.getString("adresse_complete"),
						rs.getInt("dernier_diplome_id"),
						rs.getDate("date_inscription"), 
						rs.getInt("statut_id"),
						rs.getString("photo") 
						);
			}
		} catch (SQLException e) {
			System.err.println("❌ Erreur lors de la récupération de l'étudiant : " + e.getMessage());
		}
		return null;
	}


}
