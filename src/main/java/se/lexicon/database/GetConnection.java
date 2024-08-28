package se.lexicon.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class GetConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/JDBC_Workshop";
    private static final String USER = "root"; // or your MySQL username
    private static final String PASSWORD = "password"; // or your MySQL password

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
