package com.train.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.json.JSONObject;
import com.train.config.Config;

/**
 * @author gzf
 *
 */
public class Connector{
	private Logger logger;
	private Connection conn = null;
	public Connector(Logger logger) throws Exception{
		if (logger != null) {
			this.logger = logger;
			this.getConnection();
		}else {
			throw new Exception("logger is null!");
		}
	}
	public void getConnection() {
		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(Config.URL+"&characterEncoding="+Config.CharSet, Config.UserName, Config.Passwd);
			} catch (Exception e) {
				logger.severe(e.getMessage());
			}
		}
	}
	public boolean testInsert(String name, int phone) {
		if (conn == null) {
			logger.severe("the connection of database is missing");
			return false;
		}
		try {
			String sql = "INSERT INTO test(name, phone) VALUES(?, ?)";
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, name);
			ptmt.setInt(2, phone);
			ptmt.execute();
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
		return true;
	}
	
	public String getConnect(String sql) {
		if (conn == null) {
			this.getConnection();
		}
		JSONObject resultJson = new JSONObject();
		if (sql == null || sql.equals("")) {
			resultJson.put("status", 0);   //0 means connect failed
			resultJson.put("message", new JSONObject("Invalid SQLString"));
			return resultJson.toString();
		}else {
			//SQL string is not null
			try {
				PreparedStatement ptmt = conn.prepareStatement(sql);
				ptmt.execute();
			} catch (SQLException e) {
				logger.severe(e.getMessage());
			}
		}
		return "";
	}
}
