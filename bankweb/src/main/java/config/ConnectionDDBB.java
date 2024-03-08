package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDDBB {
    Credentials credentials = new Credentials();

    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName(credentials.driver);
            conn = DriverManager.getConnection(credentials.url, credentials.userName, credentials.password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Cannot connect to database");
        }
        return conn;
    }
}

