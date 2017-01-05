package com.train.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.train.database.DBUtil;
import com.train.model.UserEntity;

public class UserEntityDao {
	public boolean addUser(UserEntity user) throws Exception {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		String sql = "INSERT INTO users" + "(ipaddress,currentlogin,online,"
				+ "connectstatus,connectwith,operationcount)"
				+ "VALUES(?,?,?,?,?,?)";

		PreparedStatement ptmt = conn.prepareStatement(sql);

		ptmt.setString(1, user.getIpaddress());
		ptmt.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
		ptmt.setInt(3, user.getOnline());
		ptmt.setInt(4, user.getConnectstatus());
		ptmt.setString(5, user.getConnectwith());
		ptmt.setInt(6, user.getOperationcount());

		return ptmt.execute();
	}
	
	public boolean addUser(String ipaddress) throws Exception {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		
		deleteUser(ipaddress);
		
		String sql = "INSERT INTO users" + "(ipaddress,currentlogin,online,"
				+ "connectstatus,operationcount)"
				+ "VALUES(?,?,?,?,?)";

		PreparedStatement ptmt = conn.prepareStatement(sql);

		ptmt.setString(1, ipaddress);
		ptmt.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
		ptmt.setInt(3, 1);
		ptmt.setInt(4, 0);
		ptmt.setInt(5, 0);

		return ptmt.execute();
	}

	public List<UserEntity> queryUser() throws Exception {
		List<UserEntity> ret = new ArrayList<UserEntity>();
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return ret;
		}
		String sql = "SELECT * FROM users";

		PreparedStatement ptmt = conn.prepareStatement(sql);
		ResultSet rs = ptmt.executeQuery();

		while (rs.next()) {
			UserEntity tmp = new UserEntity();
			tmp.setIpaddress(rs.getString("ipaddress"));
			tmp.setCurrentlogin(rs.getTimestamp("currentlogin"));
			tmp.setOnline(rs.getInt("online"));
			tmp.setConnectstatus(rs.getInt("connectstatus"));
			tmp.setConnectwith(rs.getString("connectwith"));
			tmp.setOperationcount(rs.getInt("operationcount"));
			ret.add(tmp);
		}
		return ret;
	}

	public List<UserEntity> queryUser(String ipaddress) throws Exception {
		List<UserEntity> ret = new ArrayList<UserEntity>();
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return ret;
		}
		String sql = "SELECT * FROM users WHERE ipaddress LIKE ?";

		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, "%" + ipaddress + "%");
		ResultSet rs = ptmt.executeQuery();

		while (rs.next()) {
			UserEntity tmp = new UserEntity();
			tmp.setIpaddress(rs.getString("ipaddress"));
			tmp.setCurrentlogin(rs.getTimestamp("currentlogin"));
			tmp.setOnline(rs.getInt("online"));
			tmp.setConnectstatus(rs.getInt("connectstatus"));
			tmp.setConnectwith(rs.getString("connectwith"));
			tmp.setOperationcount(rs.getInt("operationcount"));
			ret.add(tmp);
		}
		return ret;
	}

	public boolean addOperationCount(String ipaddress) throws Exception {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		String sql = "UPDATE users SET operationcount=operationcount+1 WHERE ipaddress=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, ipaddress);
		return !ptmt.execute();
	}
	
	public boolean deleteUser(String ipaddress) throws SQLException {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		String sql = "DELETE FROM users WHERE ipaddress = ?";
		
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, ipaddress);
		
		return ptmt.execute();
	}

	public boolean offline(String ipaddress) throws Exception {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		String sql = "UPDATE users SET online=0 WHERE ipaddress = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, ipaddress);
		
		return !ptmt.execute();
	}
	
	public boolean updateConnectWith(String ipaddressSelf, String ipaddressConnectWith) throws Exception {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		String sql1 = "UPDATE users SET connectwith=? WHERE ipaddress = ?";
		PreparedStatement ptmt1 = conn.prepareStatement(sql1);
		ptmt1.setString(1, ipaddressConnectWith);
		ptmt1.setString(2, ipaddressSelf);
		ptmt1.execute();
		
		String sql2 = "UPDATE users SET connectwith=? WHERE ipaddress = ?";
		PreparedStatement ptmt2 = conn.prepareStatement(sql2);
		ptmt2.setString(1, ipaddressSelf);
		ptmt2.setString(2, ipaddressConnectWith);
		return !ptmt2.execute();
	}
}
