package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_de_la_scolarite_universitaire?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";

    public static Connection getConnection() {
        String password = System.getProperty("database.password");

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            if (password == null || password.isEmpty()) {
                connection = DriverManager.getConnection(URL, USER, "");
                System.out.println("Connexion à la base de données réussie sans mot de passe !");
            } else {
                connection = DriverManager.getConnection(URL, USER, password);
                System.out.println("Connexion à la base de données réussie avec mot de passe !");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur : Pilote JDBC MySQL introuvable. " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
        }
        return connection;
    }
}