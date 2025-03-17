package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Role;
import models.Utilisateur;

public class UtilisateurDAO {

	public boolean ajouterUtilisateur(Utilisateur utilisateur) {
		String sql = "INSERT INTO utilisateurs (username, email, mot_de_passe, role) VALUES (?, ?, ?, ?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, utilisateur.getUsername());
			statement.setString(2, utilisateur.getEmail());
			statement.setString(3, utilisateur.getMotDePasse());
			statement.setString(4, utilisateur.getRole().name());

			return statement.executeUpdate() > 0;            
		} catch (SQLException e) {
			System.err.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
			return false;
		}
	}

	public Utilisateur getUtilisateurParEmail(String email) {
		String sql = "SELECT * FROM utilisateurs WHERE email = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return new Utilisateur(
						rs.getInt("id"),
						rs.getString("username"),
						rs.getString("email"),
						rs.getString("mot_de_passe"),
						Role.valueOf(rs.getString("role")),
						rs.getString("photo")
						);
			}
		} catch (SQLException e) {
			System.err.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
		}
		return null;
	}

	public boolean modifierUtilisateur(Utilisateur utilisateur) {
		String sql = "UPDATE utilisateurs SET username = ?, email = ?, mot_de_passe = ?, role = ? WHERE id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, utilisateur.getUsername());
			statement.setString(2, utilisateur.getEmail());
			statement.setString(3, utilisateur.getMotDePasse());
			statement.setString(4, utilisateur.getRole().name());
			statement.setInt(5, utilisateur.getId());

			return statement.executeUpdate() > 0;

		} catch (SQLException e) {
			System.err.println("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
			return false;
		}
	}

	public boolean updateUtilisateurPhoto(int userId, String photoPath) {
		String sql = "UPDATE utilisateurs SET photo = ? WHERE id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, photoPath);
			pstmt.setInt(2, userId);

			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	public boolean supprimerUtilisateur(int id) {
		String sql = "DELETE FROM utilisateurs WHERE id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, id);
			return statement.executeUpdate() > 0;

		} catch (SQLException e) {
			System.err.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
			return false;
		}
	}
	
	public int ajouterUtilisateurRetourId(Utilisateur utilisateur) {
	    String sql = "INSERT INTO utilisateurs (username, email, mot_de_passe, role) VALUES (?, ?, ?, ?)";
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        statement.setString(1, utilisateur.getUsername());
	        statement.setString(2, utilisateur.getEmail());
	        statement.setString(3, utilisateur.getMotDePasse());
	        statement.setString(4, utilisateur.getRole().name());

	        int affectedRows = statement.executeUpdate();
	        if (affectedRows > 0) {
	            ResultSet generatedKeys = statement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                return generatedKeys.getInt(1); 
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
	    }
	    return -1;
	}
	
	public Utilisateur getUtilisateurById(int userId) {
	    String sql = "SELECT * FROM utilisateurs WHERE id = ?";
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setInt(1, userId);
	        ResultSet rs = statement.executeQuery();

	        if (rs.next()) {
	            return new Utilisateur(
	                    rs.getInt("id"),
	                    rs.getString("username"),
	                    rs.getString("email"),
	                    rs.getString("mot_de_passe"),
	                    Role.valueOf(rs.getString("role")),
	                    rs.getString("photo")
	            );
	        }
	    } catch (SQLException e) {
	        System.err.println("❌ Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
	    }
	    return null;
	}


}
