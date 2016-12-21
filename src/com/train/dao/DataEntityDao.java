package com.train.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.train.config.Config;
import com.train.database.DBUtil;
import com.train.model.DataEntity;

public class DataEntityDao {
	public boolean addEntity(DataEntity dataEntity) throws Exception {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		String sql = "INSERT INTO "
				+ Config.Table
				+ "(iPAddress,modemSendDataRate,modemScrambleType,modemDifferEncode,"
				+ "modemRSCode,modemConvoluCode,modemType,modemCarrierOutput)"
				+ "VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);

		ptmt.setString(1, dataEntity.getiPAddress());
		ptmt.setString(2, dataEntity.getModemSendDataRate());
		ptmt.setString(3, dataEntity.getModemScrambleType());
		ptmt.setString(4, dataEntity.getModemDifferEncode());
		ptmt.setString(5, dataEntity.getModemRSCode());
		ptmt.setString(6, dataEntity.getModemConvoluCode());
		ptmt.setString(7, dataEntity.getModemType());
		ptmt.setString(8, dataEntity.getModemCarrierOutput());

		return ptmt.execute();
	}

	public List<DataEntity> queryEntity() throws Exception {
		Connection conn = DBUtil.getConnection();
		List<DataEntity> ret = new ArrayList<DataEntity>();
		if (conn == null) {
			return ret;
		}
		String sql = "SELECT * from " + Config.Table;
		PreparedStatement ptmt = conn.prepareStatement(sql);

		ResultSet rs = ptmt.executeQuery();
		DataEntity tmp;
		while (rs.next()) {
			tmp = new DataEntity();
			tmp.setiPAddress(rs.getString("iPAddress"));
			tmp.setModemSendDataRate(rs.getString("modemSendDataRate"));
			tmp.setModemScrambleType(rs.getString("modemScrambleType"));
			tmp.setModemDifferEncode(rs.getString("modemDifferEncode"));
			tmp.setModemRSCode(rs.getString("modemRSCode"));
			tmp.setModemConvoluCode(rs.getString("modemConvoluCode"));
			tmp.setModemType(rs.getString("modemType"));
			tmp.setModemCarrierOutput(rs.getString("modemCarrierOutput"));

			ret.add(tmp);
		}
		return ret;
	}

	public boolean updateEntity(HashMap<String, String> data, String ipAddress) throws SQLException {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		if (data == null || data.isEmpty()) {
			return false;
		}
		StringBuilder sql = new StringBuilder();
		for (String key : data.keySet()) {
			//循环拼接SQL字符串，考虑到计算量大，这里对每个关键字不做检查
			sql.append(key);
			sql.append("=");
			sql.append(data.get(key));
			sql.append(",");
		}//循环结束
		
		sql.deleteCharAt(sql.length()-1);
		if (sql.length()>0) {
			sql.insert(0, "UPDATE "+Config.Table+" SET ");
			sql.append("WHERE ipaddress="+ipAddress);
		}
		
		PreparedStatement ptmt = conn.prepareStatement(sql.toString());
		if (ptmt.execute()) {
			//寻找与该用户匹配的其他用户
			return true;
		}else {
			//TODO
			return false;
		}
		
	}

	public void deleteEntity() {

	}
}
