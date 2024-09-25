// Implémentation spécifique pour PostgreSQL
package com.example.datasource;

import java.sql.*;

public class PostgreSQLDataSourceImpl implements DataSource {
    private static PostgreSQLDataSourceImpl instance;
    private static final String URL = "jdbc:postgresql://localhost:5432/gestion_dette";
    private static final String USER = "postgres";
    private static final String PASSWORD = "passer123";

    public PostgreSQLDataSourceImpl() {}

    public static PostgreSQLDataSourceImpl getInstance() {
        if (instance == null) {
            instance = new PostgreSQLDataSourceImpl();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void closeConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Override
    public ResultSet executeQuery(String query, Object... params) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        setParameters(preparedStatement, params);
        return preparedStatement.executeQuery();
    }

    @Override
    public int executeUpdate(String query, Object... params) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        setParameters(preparedStatement, params);
        int affectedRows = preparedStatement.executeUpdate();
        if (query.trim().toLowerCase().startsWith("insert")) {
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);  // Dernier ID inséré
            }
        }
        return affectedRows;
    }

    @Override
    public String generateSql(String baseSql, String[] fields, String[] conditions) {
        StringBuilder sql = new StringBuilder(baseSql);
        if (fields != null && fields.length > 0) {
            sql.append(" ").append(setFields(fields));
        }
        if (conditions != null && conditions.length > 0) {
            sql.append(" WHERE ");
            for (int i = 0; i < conditions.length; i++) {
                sql.append(conditions[i]);
                if (i < conditions.length - 1) {
                    sql.append(" AND ");
                }
            }
        }
        return sql.toString();
    }

    @Override
    public String setFields(String[] fields) {
        StringBuilder setClause = new StringBuilder("SET ");
        for (int i = 0; i < fields.length; i++) {
            setClause.append(fields[i]).append(" = ?");
            if (i < fields.length - 1) {
                setClause.append(", ");
            }
        }
        return setClause.toString();
    }

    private void setParameters(PreparedStatement preparedStatement, Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
    }
}
