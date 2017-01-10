package com.train.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import com.train.database.DBUtil;

public class IpEntityDao {
	// 插入指定的接连对象
	public boolean insertConnectNode(int connectNum1, int connectNum2)
			throws Exception {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		boolean ret1 = false;
		boolean ret2 = false;
		String connectObject1 = "";
		String connectObject2 = "";
		ResultSet rs;
		// 查找num1和num2对应的ip
		String sql = "SELECT ipaddress FROM connection WHERE id=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, connectNum1);
		rs = ptmt.executeQuery();
		while (rs.next()) {
			connectObject1 = rs.getString("ipaddress");
		}

		ptmt.setInt(1, connectNum2);
		rs = ptmt.executeQuery();
		while (rs.next()) {
			connectObject2 = rs.getString("ipaddress");
		}
		// 将num1的ip和num2绑定，将num2的ip和num1绑定
		sql = "UPDATE connection SET connipaddress=? WHERE id=?";
		ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, connectObject1);
		ptmt.setInt(2, connectNum2);
		ret1 = !ptmt.execute();

		ptmt.setString(1, connectObject2);
		ptmt.setInt(2, connectNum1);
		ret2 = !ptmt.execute();
		return ret1 && ret2;
	}

	// 清空所有连接对象
	public boolean truncateAerialNum() throws Exception {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		String sql = "UPDATE connection SET aerialNum=0";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		return !ptmt.execute();
	}

	// 更新对象的卫星参数序号
	public boolean updateAerialNum(int id, int aerialNum) throws SQLException {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		String sql = "UPDATE connection SET aerialNum=? WHERE id=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, aerialNum);
		ptmt.setInt(2, id);
		return !ptmt.execute();
	}

	// 清空所有连接对象
	public boolean truncateConn() throws Exception {
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return false;
		}
		String sql = "UPDATE connection SET connipaddress=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, "");
		return !ptmt.execute();
	}

	public ArrayList<Integer> queryConn() throws Exception {
		HashMap<Integer, Integer> ret = new HashMap<>(); // ret的size小于等于16
		ArrayList<Integer> result = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return result;
		}
		String sql1 = "SELECT connipaddress FROM connection WHERE id=?";
		PreparedStatement ptmt1 = conn.prepareStatement(sql1);
		String sql2 = "SELECT id FROM connection WHERE ipaddress=?";
		PreparedStatement ptmt2 = conn.prepareStatement(sql2);
		ResultSet rs1;
		ResultSet rs2;
		String connipaddress;
		int id;
		for (int i = 1; i < 33; i++) {
			if (!ret.containsKey(i) && !ret.containsValue(i)) { // 如果ret的key和value中没有该id对应的对象
				ptmt1.setInt(1, i);
				rs1 = ptmt1.executeQuery();
				while (rs1.next()) {
					connipaddress = rs1.getString("connipaddress");
					if (connipaddress != null && !connipaddress.equals("")) { // connipaddress不为空
						ptmt2.setString(1, connipaddress);
						rs2 = ptmt2.executeQuery();
						while (rs2.next()) {
							id = rs2.getInt("id");
							ret.put(i, id);
						}
					}
				}
			}
		}

		for (Integer num1 : ret.keySet()) {
			result.add(num1);
			result.add(ret.get(num1));
		}
		return result;
	}

	// 根据ip地址查询连接对象
	public String queryConn(String ipaddress) throws Exception {
		String connipaddress = "";
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return connipaddress;
		}
		String sql = "SELECT connipaddress FROM connection WHERE ipaddress=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, ipaddress);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			connipaddress = rs.getString("connipaddress");
		}
		return connipaddress;
	}

	// 根据id查询aerialName,需要联表查询
	public String queryAerialName(int id) throws SQLException {
		String ret = "无";
		Connection conn = DBUtil.getConnection();
		if (conn == null) {
			return ret;
		}
		String sql = "SELECT a.aerialName as aerialName FROM aerialParams a, connection b WHERE a.id=b.aerialNum AND b.id=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			ret = rs.getString("aerialName");
		}
		return ret;
	}
}
