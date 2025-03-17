package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Programme;

public class ProgrammeDAO {
    public List<Programme> getAllProgrammes() {
        List<Programme> programmes = new ArrayList<>();
        String sql = "SELECT * FROM programmes";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                programmes.add(new Programme(
                    rs.getInt("id"),
                    rs.getString("code"),
                    rs.getString("nom"),
                    rs.getString("description"),
                    rs.getString("type_cours")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programmes;
    }
}
