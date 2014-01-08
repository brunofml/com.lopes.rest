package com.lopes.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import com.lopes.exceptions.DataAccessException;

public class UtilitariosDao {

    private static Connection conn;
    private static Properties propriedades;

    public static Statement createConnection() throws ClassNotFoundException,
            SQLException,
            FileNotFoundException,
            IOException,
            DataAccessException {
        
        propriedades = new Properties();
        FileInputStream in = new FileInputStream("src/com/lopes/dao/"
                + "DadosAcessoBd.properties");
        
        propriedades.load(in);
        in.close();

        String driver = propriedades.getProperty("Driver");
        if (driver != null) {
            Class.forName(driver);
        } else {
            throw new DataAccessException("Erro no acesso à base de dados");
        }

        String url = propriedades.getProperty("Url");
        String username = propriedades.getProperty("Username");
        String password = propriedades.getProperty("Password");

        conn = DriverManager.getConnection(url, username, password);

        Statement stmt = conn.createStatement();

        return stmt;
    }

    public static void closeConnection(Statement stmt) throws SQLException {
        stmt.close();
        conn.close();
    }
}
