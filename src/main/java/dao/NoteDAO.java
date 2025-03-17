package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Note;

public class NoteDAO {
    public List<Note> getNotesByEtudiant(int etudiantId) {
        List<Note> notes = new ArrayList<>();
        String sql = "SELECT n.id, n.etudiant_id, n.programme_id, n.specialite_id, n.matiere_id, " +
                     "n.annee_academique, n.moyenne, n.credit, n.session, " +
                     "m.code AS code_matiere, m.nom AS nom_matiere " +
                     "FROM notes n " +
                     "JOIN matieres m ON n.matiere_id = m.id " + 
                     "WHERE n.etudiant_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, etudiantId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                notes.add(new Note(
                    rs.getInt("id"),
                    rs.getInt("etudiant_id"),
                    rs.getInt("programme_id"),
                    rs.getInt("specialite_id"),
                    rs.getInt("matiere_id"), 
                    rs.getString("annee_academique"),
                    rs.getDouble("moyenne"),
                    rs.getInt("credit"),
                    rs.getString("code_matiere"),
                    rs.getString("nom_matiere"),
                    rs.getString("session")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }
}
