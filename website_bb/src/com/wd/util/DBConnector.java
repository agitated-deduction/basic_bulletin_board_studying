package com.wd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnector {
	public static Connection getConnect() throws Exception{
		String user = "system";
		String password = "1234";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		Class.forName(driver);
		
		Connection conn = DriverManager.getConnection(url, user, password);
		
		return conn;
	}
	public static void disconnect(PreparedStatement pstm,Connection conn) throws Exception{
		pstm.close();
		conn.close();
	}
	public static void disconnect(PreparedStatement pstm,Connection conn, ResultSet rs) throws Exception{
		rs.close();
		pstm.close();
		conn.close();
	}
}
