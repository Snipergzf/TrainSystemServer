package com.train.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		String sql = "UPDATE users SET operationcount = operationcount+1 WHERE ipaddress=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, ipaddress);
		return ptmt.execute();
	}
	
	public void deleteUser() {

	}

	public void updateUser() {

	}
}
