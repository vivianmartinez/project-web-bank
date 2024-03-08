package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class PersistDDBB {

    private Connection connection;
    Credentials credentials = new Credentials();

    public PersistDDBB() {
        this.connection = connect();
    }

    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName(credentials.driver);
            conn = DriverManager.getConnection(credentials.url, credentials.userName, credentials.password);
        } catch (ClassNotFoundException | SQLException e) {
            // e.printStackTrace();
            System.out.println("Cannot connect to database");
        }
        return conn;
    }

    public ArrayList<HashMap> executeSelectSQL(String sqlQuery) {
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            ArrayList<HashMap> data = rsToArrayList(rs);
            stmt.close();
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<HashMap> rsToArrayList(ResultSet rs) throws SQLException {
        ResultSetMetaData rsMeta = rs.getMetaData();
        ArrayList<HashMap> data = new ArrayList<>();
        int number_columns = rsMeta.getColumnCount();
        while (rs.next()) {
            HashMap resultHash = new HashMap<>();
            try {
                for (int i = 1; i <= number_columns; i++) {
                    String column_value = rs.getString(rsMeta.getColumnName(i));
                    resultHash.put(rsMeta.getColumnName(i), column_value);
                }
                data.add(resultHash);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        rs.close();
        return data;
    }
}
