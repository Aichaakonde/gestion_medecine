package com.example.datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataSource {
    Connection getConnection() throws SQLException;        // Ouvre la connexion
    void closeConnection(Connection connection) throws SQLException; // Ferme la connexion
    ResultSet executeQuery(String query, Object... params) throws SQLException; // Exécute une requête SELECT
    int executeUpdate(String query, Object... params) throws SQLException;  // Exécute INSERT, UPDATE, DELETE
    String generateSql(String baseSql, String[] fields, String[] conditions); // Génère SQL
    String setFields(String[] fields);  // Gère les champs SET pour les requêtes UPDATE
}
