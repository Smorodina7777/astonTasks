package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class PostgreSQLConnection {
    private static final String user = "postgres";
    private  static final String password = "root";
    private static final String dataBase = "hw2test";
    private static final String url = "jdbc:postgresql://localhost:5432/" + dataBase;

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
