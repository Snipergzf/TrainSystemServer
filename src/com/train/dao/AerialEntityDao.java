package com.train.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import com.train.database.DBUtil;
import com.train.model.AerialEntity;

public class AerialEntityDao {
	public ArrayList<String> query() throws SQLException {
		ArrayList<String> ret = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return ret;
		}
		String sql = "SELECT aerialName FROM aerialParams";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			ret.add(rs.getString("aerialName"));
		}
		if (!ret.isEmpty()) {
			Collections.sort(ret);
		}
		return ret;
	}
	
	public AerialEntity query(String aerialName) throws SQLException {
		AerialEntity ret = new AerialEntity();
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return ret;
		}
		String sql = "SELECT * FROM aerialParams WHERE aerialName=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, aerialName);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			ret.setId(rs.getInt("id"));
			ret.setAerialName(aerialName);
			ret.setAeWorkFre(rs.getString("aeWorkFre"));
			ret.setAePolarization(rs.getString("aePolarization"));
			ret.setReWorkStatus(rs.getString("reWorkStatus"));
			ret.setReOffsetFre(rs.getString("reOffsetFre"));
			ret.setReFre(rs.getString("reFre"));
			ret.setSateLongitude(rs.getString("sateLongitude"));
		}
		return ret;
	}
	
	public AerialEntity query(int id) throws SQLException {
		AerialEntity ret = new AerialEntity();
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return ret;
		}
		String sql = "SELECT * FROM aerialParams WHERE id=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			ret.setId(id);
			ret.setAerialName(rs.getString("aerialName"));
			ret.setAeWorkFre(rs.getString("aeWorkFre"));
			ret.setAePolarization(rs.getString("aePolarization"));
			ret.setReWorkStatus(rs.getString("reWorkStatus"));
			ret.setReOffsetFre(rs.getString("reOffsetFre"));
			ret.setReFre(rs.getString("reFre"));
			ret.setSateLongitude(rs.getString("sateLongitude"));
		}
		return ret;
	}
	
	//根据卫星参数名查询对应ID
	public int queryId(String aerialName) throws SQLException {
		int ret = -1;
		Connection conn = DBUtil.getConnection();
		if (conn == null || aerialName.equals("无")) {
			return ret;
		}
		String sql = "SELECT id FROM aerialParams WHERE aerialName=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, aerialName);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			ret = rs.getInt("id");
		}
		return ret;
	}

	public boolean insert(AerialEntity aerialEntity) throws SQLException {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		String sql = "INSERT INTO aerialParams (aerialName, sateLongitude, "
				+ "aeWorkFre, aePolarization, reWorkStatus, reOffsetFre, reFre) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, aerialEntity.getAerialName());
		ptmt.setString(2, aerialEntity.getSateLongitude());
		ptmt.setString(3, aerialEntity.getAeWorkFre());
		ptmt.setString(4, aerialEntity.getAePolarization());
		ptmt.setString(5, aerialEntity.getReWorkStatus());
		ptmt.setString(6, aerialEntity.getReOffsetFre());
		ptmt.setString(7, aerialEntity.getReFre());
		return !ptmt.execute();
	}

	
	//根据ID更新,不能根据名称更新,以防止名称被修改
	public boolean update(AerialEntity aerialEntity) throws SQLException {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		String sql = "UPDATE aerialParams SET sateLongitude=?, aeWorkFre=?, aePolarization=?, "
				+ "reWorkStatus=?, reOffsetFre=?, reFre=?, aerialName=? WHERE id=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, aerialEntity.getSateLongitude());
		ptmt.setString(2, aerialEntity.getAeWorkFre());
		ptmt.setString(3, aerialEntity.getAePolarization());
		ptmt.setString(4, aerialEntity.getReWorkStatus());
		ptmt.setString(5, aerialEntity.getReOffsetFre());
		ptmt.setString(6, aerialEntity.getReFre());
		ptmt.setString(7, aerialEntity.getAerialName());
		ptmt.setInt(8, aerialEntity.getId());
		return !ptmt.execute();
	}
	
	public boolean delete(String aerialName) throws SQLException{
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false; 
		}
		String sql = "DELETE FROM aerialParams WHERE aerialName=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, aerialName);
		return !ptmt.execute();
	}
	
}
