package com.train.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
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
				+ "MD_deModemType,MD_deModemReceiveCarrierFrequence,MD_frameType,MD_frameParam,"
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
		ptmt.setString(33, dataEntity.getMD_frameParam());
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

	public boolean addEntity(String ipaddress, String dataJsonString)
			throws Exception {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		deleteEntity(ipaddress);

		JSONObject dataJson = new JSONObject(dataJsonString);

		String sql = "INSERT INTO data"
				+ "(ipaddress,MC_deviceId,MC_groupRate,MC_groupInterface,"
				+ "MC_groupClock,MC_videoRate,MC_videoSendClock,MC_videoReceivedClock,"
				+ "VC_interfaceType,VC_clock,VC_rate,VC_codeFormat,VC_imageFormat,"
				+ "VC_frameRateValues,VC_audioParameValues,VC_synData,MD_modemSendDataRate,"
				+ "MD_modemScrambleType,MD_modemDifferEncode,MD_modemRSCode,MD_modemConvoluCode,"
				+ "MD_modemType,MD_modemCarrierOutput,MD_modemSendCarrierFrequence,MD_deModemReceiveDataRate,"
				+ "MD_deModemDescrambleType,MD_deModemDifferEncode,MD_deModemRSDecode,MD_deModemConvoluDecode,"
				+ "MD_deModemType,MD_deModemReceiveCarrierFrequence,MD_frameType,MD_frameParam,"
				+ "MD_frameSClockPhase,MD_frameSServiceInterface,MD_frameSDataClock,MD_deframeType,"
				+ "MD_deframeSClockPhase,MD_deframeRServiceInterface,MD_interfaceType,MD_interfaceCodeType)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement ptmt = conn.prepareStatement(sql);

		ptmt.setString(1, ipaddress);
		ptmt.setString(2, dataJson.getString("MC_deviceId"));
		ptmt.setString(3, dataJson.getString("MC_groupRate"));
		ptmt.setString(4, dataJson.getString("MC_groupInterface"));
		ptmt.setString(5, dataJson.getString("MC_groupClock"));
		ptmt.setString(6, dataJson.getString("MC_videoRate"));
		ptmt.setString(7, dataJson.getString("MC_videoSendClock"));
		ptmt.setString(8, dataJson.getString("MC_videoReceivedClock"));
		ptmt.setString(9, dataJson.getString("VC_interfaceType"));
		ptmt.setString(10, dataJson.getString("VC_clock"));
		ptmt.setString(11, dataJson.getString("VC_rate"));
		ptmt.setString(12, dataJson.getString("VC_codeFormat"));
		ptmt.setString(13, dataJson.getString("VC_imageFormat"));
		ptmt.setString(14, dataJson.getString("VC_frameRateValues"));
		ptmt.setString(15, dataJson.getString("VC_audioParameValues"));
		ptmt.setString(16, dataJson.getString("VC_synData"));
		ptmt.setString(17, dataJson.getString("MD_modemSendDataRate"));
		ptmt.setString(18, dataJson.getString("MD_modemScrambleType"));
		ptmt.setString(19, dataJson.getString("MD_modemDifferEncode"));
		ptmt.setString(20, dataJson.getString("MD_modemRSCode"));
		ptmt.setString(21, dataJson.getString("MD_modemConvoluCode"));
		ptmt.setString(22, dataJson.getString("MD_modemType"));
		ptmt.setString(23, dataJson.getString("MD_modemCarrierOutput"));
		ptmt.setString(24, dataJson.getString("MD_modemSendCarrierFrequence"));
		ptmt.setString(25, dataJson.getString("MD_deModemReceiveDataRate"));
		ptmt.setString(26, dataJson.getString("MD_deModemDescrambleType"));
		ptmt.setString(27, dataJson.getString("MD_deModemDifferEncode"));
		ptmt.setString(28, dataJson.getString("MD_deModemRSDecode"));
		ptmt.setString(29, dataJson.getString("MD_deModemConvoluDecode"));
		ptmt.setString(30, dataJson.getString("MD_deModemType"));
		ptmt.setString(31,
				dataJson.getString("MD_deModemReceiveCarrierFrequence"));
		ptmt.setString(32, dataJson.getString("MD_frameType"));
		ptmt.setString(33, dataJson.getString("MD_frameParam"));
		ptmt.setString(34, dataJson.getString("MD_frameSClockPhase"));
		ptmt.setString(35, dataJson.getString("MD_frameSServiceInterface"));
		ptmt.setString(36, dataJson.getString("MD_frameSDataClock"));
		ptmt.setString(37, dataJson.getString("MD_deframeType"));
		ptmt.setString(38, dataJson.getString("MD_frameSClockPhase"));
		ptmt.setString(39, dataJson.getString("MD_deframeRServiceInterface"));
		ptmt.setString(40, dataJson.getString("MD_interfaceType"));
		ptmt.setString(41, dataJson.getString("MD_interfaceCodeType"));

		return ptmt.execute();
	}
	
	//查询指定ip地址的对象
	public DataEntity queryEntity(String ipAddress) throws Exception {
		DataEntity ret = new DataEntity();
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return ret;
		}
		String sql = "SELECT * FROM data WHERE ipaddress=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, ipAddress);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			ret.setiPAddress(rs.getString("ipaddress"));
			ret.setMC_deviceId(rs.getString("mC_deviceId"));
			ret.setMC_groupClock(rs.getString("mC_groupClock"));
			ret.setMC_groupInterface(rs.getString("mC_groupInterface"));
			ret.setMC_groupRate(rs.getString("mC_groupRate"));
			ret.setMC_videoRate(rs.getString("mC_videoRate"));
			ret.setMC_videoReceivedClock(rs.getString("mC_videoReceivedClock"));
			ret.setMC_videoSendClock(rs.getString("mC_videoSendClock"));
			ret.setMD_deframeRServiceInterface(rs
					.getString("mD_deframeRServiceInterface"));
			ret.setMD_deframeSClockPhase(rs.getString("mD_deframeSClockPhase"));
			ret.setMD_deframeType(rs.getString("mD_deframeType"));
			ret.setMD_deModemConvoluDecode(rs
					.getString("mD_deModemConvoluDecode"));
			ret.setMD_deModemDescrambleType(rs
					.getString("mD_deModemDescrambleType"));
			ret.setMD_deModemDifferEncode(rs
					.getString("mD_deModemDifferEncode"));
			ret.setMD_deModemReceiveCarrierFrequence(rs
					.getString("mD_deModemReceiveCarrierFrequence"));
			ret.setMD_deModemReceiveDataRate(rs
					.getString("mD_deModemReceiveDataRate"));
			ret.setMD_deModemRSDecode(rs.getString("mD_deModemRSDecode"));
			ret.setMD_deModemType(rs.getString("mD_deModemType"));
			ret.setMD_frameSClockPhase(rs.getString("mD_frameSClockPhase"));
			ret.setMD_frameSDataClock(rs.getString("mD_frameSDataClock"));
			ret.setMD_frameSServiceInterface(rs
					.getString("mD_frameSServiceInterface"));
			ret.setMD_frameType(rs.getString("mD_frameType"));
			ret.setMD_frameParam(rs.getString("MD_frameParam"));
			ret.setMD_interfaceCodeType(rs.getString("mD_interfaceCodeType"));
			ret.setMD_interfaceType(rs.getString("mD_interfaceType"));
			ret.setMD_modemCarrierOutput(rs.getString("mD_modemCarrierOutput"));
			ret.setMD_modemConvoluCode(rs.getString("mD_modemConvoluCode"));
			ret.setMD_modemDifferEncode(rs.getString("mD_modemDifferEncode"));
			ret.setMD_modemRSCode(rs.getString("mD_modemRSCode"));
			ret.setMD_modemScrambleType(rs.getString("mD_modemScrambleType"));
			ret.setMD_modemSendCarrierFrequence(rs
					.getString("mD_modemSendCarrierFrequence"));
			ret.setMD_modemSendDataRate(rs.getString("mD_modemSendDataRate"));
			ret.setMD_modemType(rs.getString("mD_modemType"));
			ret.setVC_audioParameValues(rs.getString("vC_audioParameValues"));
			ret.setVC_clock(rs.getString("vC_clock"));
			ret.setVC_codeFormat(rs.getString("vC_codeFormat"));
			ret.setVC_frameRateValues(rs.getString("vC_frameRateValues"));
			ret.setVC_imageFormat(rs.getString("vC_imageFormat"));
			ret.setVC_interfaceType(rs.getString("vC_interfaceType"));
			ret.setVC_rate(rs.getString("vC_rate"));
			ret.setVC_synData(rs.getString("vC_synData"));
		}
		return ret;
	}

	//查询与传入的ip地址特定数据相同的用户
	public List<DataEntity> queryConnectEntity(String ipaddress)
			throws Exception {
		Connection conn = DBUtil.getConnection();
		List<DataEntity> ret = new ArrayList<DataEntity>();
		if (conn == null) {
			return ret;
		}

		DataEntity selfDataEntity = queryEntity(ipaddress);

		if (selfDataEntity == null || selfDataEntity.getiPAddress() == null) {
			return ret;
		}
		ret.add(selfDataEntity);

		String sql = "SELECT * FROM data WHERE ipaddress!=? AND MC_groupRate=? AND MC_groupInterface=? AND MC_videoRate=? AND "
				+ "VC_interfaceType=? AND VC_rate=? AND VC_codeFormat=? AND VC_imageFormat=? AND "
				+ "VC_frameRateValues=? AND VC_audioParameValues=? AND VC_synData=? AND MD_interfaceType=? AND "
				+ "MD_interfaceCodeType=?";

		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, selfDataEntity.getiPAddress());
		ptmt.setString(2, selfDataEntity.getMC_groupRate());
		ptmt.setString(3, selfDataEntity.getMC_groupInterface());
		ptmt.setString(4, selfDataEntity.getMC_videoRate());
		ptmt.setString(5, selfDataEntity.getVC_interfaceType());
		ptmt.setString(6, selfDataEntity.getVC_rate());
		ptmt.setString(7, selfDataEntity.getVC_codeFormat());
		ptmt.setString(8, selfDataEntity.getVC_imageFormat());
		ptmt.setString(9, selfDataEntity.getVC_frameRateValues());
		ptmt.setString(10, selfDataEntity.getVC_audioParameValues());
		ptmt.setString(11, selfDataEntity.getVC_synData());
		ptmt.setString(12, selfDataEntity.getMD_interfaceType());
		ptmt.setString(13, selfDataEntity.getMD_interfaceCodeType());

		ResultSet rs = ptmt.executeQuery();
		DataEntity connectEntity = new DataEntity();
		while (rs.next()) {
			connectEntity.setiPAddress(rs.getString("ipaddress"));
			connectEntity.setMC_deviceId(rs.getString("mC_deviceId"));
			connectEntity.setMC_groupClock(rs.getString("mC_groupClock"));
			connectEntity.setMC_groupInterface(rs
					.getString("mC_groupInterface"));
			connectEntity.setMC_groupRate(rs.getString("mC_groupRate"));
			connectEntity.setMC_videoRate(rs.getString("mC_videoRate"));
			connectEntity.setMC_videoReceivedClock(rs
					.getString("mC_videoReceivedClock"));
			connectEntity.setMC_videoSendClock(rs
					.getString("mC_videoSendClock"));
			connectEntity.setMD_deframeRServiceInterface(rs
					.getString("mD_deframeRServiceInterface"));
			connectEntity.setMD_deframeSClockPhase(rs
					.getString("mD_deframeSClockPhase"));
			connectEntity.setMD_deframeType(rs.getString("mD_deframeType"));
			connectEntity.setMD_deModemConvoluDecode(rs
					.getString("mD_deModemConvoluDecode"));
			connectEntity.setMD_deModemDescrambleType(rs
					.getString("mD_deModemDescrambleType"));
			connectEntity.setMD_deModemDifferEncode(rs
					.getString("mD_deModemDifferEncode"));
			connectEntity.setMD_deModemReceiveCarrierFrequence(rs
					.getString("mD_deModemReceiveCarrierFrequence"));
			connectEntity.setMD_deModemReceiveDataRate(rs
					.getString("mD_deModemReceiveDataRate"));
			connectEntity.setMD_deModemRSDecode(rs
					.getString("mD_deModemRSDecode"));
			connectEntity.setMD_deModemType(rs.getString("mD_deModemType"));
			connectEntity.setMD_frameSClockPhase(rs
					.getString("mD_frameSClockPhase"));
			connectEntity.setMD_frameSDataClock(rs
					.getString("mD_frameSDataClock"));
			connectEntity.setMD_frameSServiceInterface(rs
					.getString("mD_frameSServiceInterface"));
			connectEntity.setMD_frameType(rs.getString("mD_frameType"));
			connectEntity.setMD_frameParam(rs
					.getString("MD_frameParam"));
			connectEntity.setMD_interfaceCodeType(rs
					.getString("mD_interfaceCodeType"));
			connectEntity.setMD_interfaceType(rs.getString("mD_interfaceType"));
			connectEntity.setMD_modemCarrierOutput(rs
					.getString("mD_modemCarrierOutput"));
			connectEntity.setMD_modemConvoluCode(rs
					.getString("mD_modemConvoluCode"));
			connectEntity.setMD_modemDifferEncode(rs
					.getString("mD_modemDifferEncode"));
			connectEntity.setMD_modemRSCode(rs.getString("mD_modemRSCode"));
			connectEntity.setMD_modemScrambleType(rs
					.getString("mD_modemScrambleType"));
			connectEntity.setMD_modemSendCarrierFrequence(rs
					.getString("mD_modemSendCarrierFrequence"));
			connectEntity.setMD_modemSendDataRate(rs
					.getString("mD_modemSendDataRate"));
			connectEntity.setMD_modemType(rs.getString("mD_modemType"));
			connectEntity.setVC_audioParameValues(rs
					.getString("vC_audioParameValues"));
			connectEntity.setVC_clock(rs.getString("vC_clock"));
			connectEntity.setVC_codeFormat(rs.getString("vC_codeFormat"));
			connectEntity.setVC_frameRateValues(rs
					.getString("vC_frameRateValues"));
			connectEntity.setVC_imageFormat(rs.getString("vC_imageFormat"));
			connectEntity.setVC_interfaceType(rs.getString("vC_interfaceType"));
			connectEntity.setVC_rate(rs.getString("vC_rate"));
			connectEntity.setVC_synData(rs.getString("vC_synData"));
			ret.add(connectEntity);
		}
		connectEntity = null;
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
			sql.append("=\"");
			sql.append(data.get(key));
			sql.append("\",");
		}// 循环结束

		sql.deleteCharAt(sql.length() - 1);
		if (sql.length() > 0) {
			sql.insert(0, "UPDATE data SET ");
			sql.append(" WHERE ipaddress=?");
		}
		PreparedStatement ptmt = conn.prepareStatement(sql.toString());
		ptmt.setString(1, ipAddress);
		return (!ptmt.execute());
	}

	public boolean deleteEntity(String ipAddress) throws SQLException {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		String sql = "DELETE FROM  data WHERE ipaddress=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, ipAddress);
		return ptmt.execute();
	}

	/*public List<DataEntity> queryEntity() throws Exception {
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
	}*/

	
	/*public List<DataEntity> queryEntity(HashMap<String, String> data,
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
	}*/

}
