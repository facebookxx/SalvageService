package tanat.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
  
public class MySQLConnUtils {
 
 public static Connection getMySQLConnection()
         throws ClassNotFoundException, SQLException {
    String hostName = "localhost";
    String dbName = "salvage_service";
    String dbPort = "3306";
    String userName = "root";
    String password = "qweqwe123123";
    String connectionURL = "jdbc:mysql://" + hostName + ":" + dbPort + "/" + dbName;
    Connection conn = null;

	try {
	Class.forName("com.mysql.cj.jdbc.Driver");	
//	DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
	
	conn = DriverManager.getConnection(connectionURL, userName, password);
	} catch (ClassNotFoundException e) {
		System.out.println(e.getMessage());
	}
  
     // Структура URL Connection для MySQL:
     //("jdbc:mysql://localhost:3306/orange","root", "qweqwe123123")
     
     
//  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/salvage_service","root", "qweqwe123123");
     
     
     return conn;
 }
 
	public static Connection getDBConnection() {
	    Connection conn = null;
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        System.out.println(e.getMessage());
	    }
	    try {
	    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/salvage_service","root", "qweqwe123123");
	        return conn;
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return conn;
	}
}