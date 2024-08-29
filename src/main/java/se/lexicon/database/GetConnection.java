package se.lexicon.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class GetConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/world";
    private static final String USER = "root";
    private static final String PASSWORD = "1234567890";

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
