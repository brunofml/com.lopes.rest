package com.lopes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Statement;
import java.util.ResourceBundle;

public class DBConnection {
	
	private static String sDriverName=null;
	private static String sServerName=null;
	private static String sPort=null;
	private static String sDatabaseName=null;
	private static String sUserName=null;
	private static String sPassword=null;
    private static Connection conn;
    


    public static Statement getDBConnection() throws Exception{
        ResourceBundle rb=ResourceBundle.getBundle("connection_config");
        
         sDriverName=rb.getString("driver.name");
         sServerName=rb.getString("server.name");
         sPort=rb.getString("port.no");
         sDatabaseName=rb.getString("database.name");
         sUserName=rb.getString("user.name");
         sPassword=rb.getString("user.password");
        
        Class.forName(sDriverName).newInstance();
         
        String sURL ="jdbc:mysql://"+sServerName+":"+sPort+"/"+sDatabaseName;
        
        conn = DriverManager.getConnection(sURL,sUserName, sPassword);
        Statement stmt = conn.createStatement();
      return stmt;
    }
    
    public static void closeConnection(Statement stmt) throws SQLException {
    	stmt.close();
        conn.close();
    }
}
