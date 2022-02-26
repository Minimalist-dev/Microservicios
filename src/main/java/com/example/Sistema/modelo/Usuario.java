package com.example.Sistema.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neury-dev
 */
public class Usuario {
    private static final String URL = "jdbc:mysql://localhost:3306/java";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    static public Connection
    getConexion() throws SQLException {
        Connection setConexion = DriverManager.getConnection(URL, USUARIO, PASSWORD); 
        return setConexion;
    }
    static public Statement 
    getStatement() throws SQLException {
        Statement setStatement = Usuario.getConexion().createStatement();
        return setStatement;
    }
    static public ResultSet 
    setResultSet(String sql) throws SQLException {
        ResultSet getResultSet = Usuario.getStatement().executeQuery(sql);
        return getResultSet;
    }
    static public int
    executeUpdate(String sql){
        try {
            Statement statement = Usuario.getStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
