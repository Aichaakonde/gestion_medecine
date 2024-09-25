
package com.example.repository;

import com.example.datasource.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RepositoryBDImpl {
    private final DataSource dataSource;

    public RepositoryBDImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Méthode pour récupérer le DataSource
    public DataSource getDataSource() {
        return dataSource;
    }

    public int insert(String tableName, String[] fields, Object[] values) throws SQLException {
        String sql = "INSERT INTO " + tableName + " (" + String.join(", ", fields) + ") VALUES (" + generatePlaceholders(fields.length) + ")";
        return dataSource.executeUpdate(sql, values);
    }

    public ResultSet selectAll(String tableName) throws SQLException {
        String sql = "SELECT * FROM " + tableName;
        return dataSource.executeQuery(sql);
    }

    private String generatePlaceholders(int length) {
        String[] placeholders = new String[length];
        for (int i = 0; i < length; i++) {
            placeholders[i] = "?";
        }
        return String.join(", ", placeholders);
    }
}