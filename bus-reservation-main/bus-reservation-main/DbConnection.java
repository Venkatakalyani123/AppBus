package org.bus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","Kalyani@123");
		
	}


}
