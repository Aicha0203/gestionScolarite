package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Absence;

public class AbsenceDAO {
    public List<Absence> getAbsencesByEtudiant(int etudiantId) {
        List<Absence> absences = new ArrayList<>();
        String sql = "SELECT * FROM absences WHERE etudiant_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, etudiantId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                absences.add(new Absence(
                    rs.getInt("id"),
                    rs.getInt("etudiant_id"),
                    rs.getInt("programme_id"),
                    rs.getInt("specialite_id"),
                    rs.getDate("date_absence"),
                    rs.getBoolean("justifie")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return absences;
    }
}
