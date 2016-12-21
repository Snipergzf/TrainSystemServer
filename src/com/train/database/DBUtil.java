package com.train.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.train.config.Config;

public class DBUtil {
	private static Connection conn = null;
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Config.URL+"&characterEncoding="+Config.CharSet, Config.UserName, Config.Passwd);	
		} catch (ClassNotFoundException e) {
			//TODO
		} catch (SQLException e) {
			//TODO
		}
	}

	public static Connection getConnection() {
		return conn;
	}

}
