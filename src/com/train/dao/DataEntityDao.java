package com.train.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.train.config.Config;
import com.train.database.DBUtil;
import com.train.model.DataEntity;

public class DataEntityDao {
	public boolean addEntity(DataEntity dataEntity) throws Exception {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		String sql = "INSERT INTO data"
				+ "(ipaddress,MC_deviceId,MC_groupRate,MC_groupInterface,"
				+ "MC_groupClock,MC_videoRate,MC_videoSendClock,MC_videoReceivedClock,"
				+ "VC_interfaceType,VC_clock,VC_rate,VC_codeFormat,VC_imageFormat,"
				+ "VC_frameRateValues,VC_audioParameValues,VC_synData,MD_modemSendDataRate,"
				+ "MD_modemScrambleType,MD_modemDifferEncode,MD_modemRSCode,MD_modemConvoluCode,"
				+ "MD_modemType,MD_modemCarrierOutput,MD_modemSendCarrierFrequence,MD_deModemReceiveDataRate,"
				+ "MD_deModemDescrambleType,MD_deModemDifferEncode,MD_deModemRSDecode,MD_deModemConvoluDecode,"
				+ "MD_deModemType,MD_deModemReceiveCarrierFrequence,MD_frameType,MD_inCarrierOutput,"
				+ "MD_frameSClockPhase,MD_frameSServiceInterface,MD_frameSDataClock,MD_deframeType,"
				+ "MD_deframeSClockPhase,MD_deframeRServiceInterface,MD_interfaceType,MD_interfaceCodeType)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);

		 ptmt.setString(1, dataEntity.getiPAddress());
		 ptmt.setString(2, dataEntity.getMC_deviceId());
		 ptmt.setString(3, dataEntity.getMC_groupRate());
		 ptmt.setString(4, dataEntity.getMC_groupInterface());
		 ptmt.setString(5, dataEntity.getMC_groupClock());
		 ptmt.setString(6, dataEntity.getMC_videoRate());
		 ptmt.setString(7, dataEntity.getMC_videoSendClock());
		 ptmt.setString(8, dataEntity.getMC_videoReceivedClock());
		 ptmt.setString(9, dataEntity.getVC_interfaceType());
		 ptmt.setString(10, dataEntity.getVC_clock());
		 ptmt.setString(11, dataEntity.getVC_rate());
		 ptmt.setString(12, dataEntity.getVC_codeFormat());
		 ptmt.setString(13, dataEntity.getVC_imageFormat());
		 ptmt.setString(14, dataEntity.getVC_frameRateValues());
		 ptmt.setString(15, dataEntity.getVC_audioParameValues());
		 ptmt.setString(16, dataEntity.getVC_synData());
		 ptmt.setString(17, dataEntity.getMD_modemSendDataRate());
		 ptmt.setString(18, dataEntity.getMD_modemScrambleType());
		 ptmt.setString(19, dataEntity.getMD_modemDifferEncode());
		 ptmt.setString(20, dataEntity.getMD_modemRSCode());
		 ptmt.setString(21, dataEntity.getMD_modemConvoluCode());
		 ptmt.setString(22, dataEntity.getMD_modemType());
		 ptmt.setString(23, dataEntity.getMD_modemCarrierOutput());
		 ptmt.setString(24, dataEntity.getMD_modemSendCarrierFrequence());
		 ptmt.setString(25, dataEntity.getMD_deModemReceiveDataRate());
		 ptmt.setString(26, dataEntity.getMD_deModemDescrambleType());
		 ptmt.setString(27, dataEntity.getMD_deModemDifferEncode());
		 ptmt.setString(28, dataEntity.getMD_deModemRSDecode());
		 ptmt.setString(29, dataEntity.getMD_deModemConvoluDecode());
		 ptmt.setString(30, dataEntity.getMD_deModemType());
		 ptmt.setString(31, dataEntity.getMD_deModemReceiveCarrierFrequence());
		 ptmt.setString(32, dataEntity.getMD_frameType());
		 ptmt.setString(33, dataEntity.getMD_inCarrierOutput());
		 ptmt.setString(34, dataEntity.getMD_frameSClockPhase());
		 ptmt.setString(35, dataEntity.getMD_frameSServiceInterface());
		 ptmt.setString(36, dataEntity.getMD_frameSDataClock());
		 ptmt.setString(37, dataEntity.getMD_deframeType());
		 ptmt.setString(38, dataEntity.getMD_frameSClockPhase());
		 ptmt.setString(39, dataEntity.getMD_deframeRServiceInterface());
		 ptmt.setString(40, dataEntity.getMD_interfaceType());
		 ptmt.setString(41, dataEntity.getMD_interfaceCodeType());
		 
		return ptmt.execute();
	}

	public DataEntity queryEntity(String ipAddress) throws Exception {
		DataEntity ret = new DataEntity();
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return ret;
		}
		String sql = "SELECT * FROM　" + Config.Table + " WHERE ipaddress="
				+ ipAddress;

		PreparedStatement ptmt = conn.prepareStatement(sql);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			ret.setiPAddress(rs.getString("ipaddress"));
			// ret.setModemSendDataRate(rs.getString("modemSendDataRate"));
			// ret.setModemScrambleType(rs.getString("modemScrambleType"));
			// ret.setModemDifferEncode(rs.getString("modemDifferEncode"));
			// ret.setModemRSCode(rs.getString("modemRSCode"));
			// ret.setModemConvoluCode(rs.getString("modemConvoluCode"));
			// ret.setModemType(rs.getString("modemType"));
			// ret.setModemCarrierOutput(rs.getString("modemCarrierOutput"));
		}
		return ret;
	}

	public List<DataEntity> queryEntity(HashMap<String, String> data,
			String ipAddress) throws Exception {
		Connection conn = DBUtil.getConnection();
		List<DataEntity> ret = new ArrayList<DataEntity>();
		if (conn == null || data == null || data.isEmpty()) {
			return ret;
		}
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM " + Config.Table + " WHERE 1=1");
		for (String key : data.keySet()) {
			// 循环拼接SQL字符串，考虑到计算量大，这里对每个关键字不做检查
			sql.append(" AND ");
			sql.append(key);
			sql.append("=");
			sql.append(data.get(key));
		}// 循环结束
		sql.append(" AND ipAddress != " + ipAddress);
		PreparedStatement ptmt = conn.prepareStatement(sql.toString());
		ResultSet rs = ptmt.executeQuery();
		DataEntity tmp;
		while (rs.next()) {
			tmp = new DataEntity();
			tmp.setiPAddress(rs.getString("iPAddress"));
			tmp.setMC_deviceId(rs.getString("MC_deviceId"));

			ret.add(tmp);
		}
		return ret;
	}

	public List<DataEntity> queryEntity() throws Exception {
		Connection conn = DBUtil.getConnection();
		List<DataEntity> ret = new ArrayList<DataEntity>();
		if (conn == null) {
			return ret;
		}
		String sql = "SELECT * FROM " + Config.Table;
		PreparedStatement ptmt = conn.prepareStatement(sql);

		ResultSet rs = ptmt.executeQuery();
		DataEntity tmp;
		while (rs.next()) {
			tmp = new DataEntity();
			tmp.setiPAddress(rs.getString("iPAddress"));
			// tmp.setModemSendDataRate(rs.getString("modemSendDataRate"));
			// tmp.setModemScrambleType(rs.getString("modemScrambleType"));
			// tmp.setModemDifferEncode(rs.getString("modemDifferEncode"));
			// tmp.setModemRSCode(rs.getString("modemRSCode"));
			// tmp.setModemConvoluCode(rs.getString("modemConvoluCode"));
			// tmp.setModemType(rs.getString("modemType"));
			// tmp.setModemCarrierOutput(rs.getString("modemCarrierOutput"));

			ret.add(tmp);
		}
		return ret;
	}

	public boolean updateEntity(HashMap<String, String> data, String ipAddress)
			throws SQLException {
		Connection conn = DBUtil.getConnection();
		if (conn == null || data == null || data.isEmpty()) {
			return false;
		}
		StringBuilder sql = new StringBuilder();
		for (String key : data.keySet()) {
			// 循环拼接SQL字符串，考虑到计算量大，这里对每个关键字不做检查
			sql.append(key);
			sql.append("=");
			sql.append(data.get(key));
			sql.append(",");
		}// 循环结束

		sql.deleteCharAt(sql.length() - 1);
		if (sql.length() > 0) {
			sql.insert(0, "UPDATE " + Config.Table + " SET ");
			sql.append("WHERE ipaddress=" + ipAddress);
		}

		PreparedStatement ptmt = conn.prepareStatement(sql.toString());
		if (ptmt.execute()) {
			// 寻找与该用户匹配的其他用户
			return true;
		} else {
			// TODO
			return false;
		}

	}

	public boolean deleteEntity(String ipAddress) throws SQLException {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		String sql = "DELETE FROM " + Config.Table + " WHERE ipaddress="
				+ ipAddress;
		PreparedStatement ptmt = conn.prepareStatement(sql);

		if (ptmt.execute()) {
			return true;
		} else {
			// TODO
			return false;
		}
	}
}
