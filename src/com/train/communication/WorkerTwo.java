package com.train.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import com.train.config.Config;
import com.train.dao.DataEntityDao;
import com.train.dao.UserEntityDao;
import com.train.model.DataEntity;

/**
 * @author gzf
 * 
 */
public class WorkerTwo implements Runnable {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Logger logger;
	private DataEntity dataEntity;
	private boolean UpdateFlag = false; // 改动数据标记位
	private String IPAddress;
	private DataEntityDao dataEntityDao;
	private UserEntityDao userEntityDao;
	private Thread updateThread;
	private Lock lock;
	private JSONObject inJson;
	private JSONObject outJson;

	public WorkerTwo(Socket s, Logger logger, String IPAddress)
			throws IOException {
		this.dataEntityDao = new DataEntityDao();
		this.userEntityDao = new UserEntityDao();
		this.dataEntity = new DataEntity();
		this.IPAddress = IPAddress;
		this.socket = s;
		this.logger = logger;
		this.updateThread = new Thread(new UpdateThread());
		this.lock = new ReentrantLock();
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		// Enable auto-flush
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream())), true);

	}

	@Override
	public void run() {
		String str = "";
		try {
			updateThread.start();
			while (in != null && (str = in.readLine()) != null) {
				lock.lock();
				if (!str.equals("")) {
					inJson = new JSONObject(str);
					int requestType = inJson.getInt("status");
					String dataJsonString = inJson.getString("message");
					switch (requestType) {
					case Config.UPDATE:
						userEntityDao.addOperationCount(IPAddress);
						dataEntity.update(dataJsonString);
						logger.info(IPAddress +" UPDATE completed");
						UpdateFlag = true;
						break;
					case Config.CHECK:
						dataEntityDao.addEntity(IPAddress, dataJsonString);
						logger.info(IPAddress +" CHECK completed");
						break;
					case Config.HEART:
						break;
					}
				}
				lock.unlock();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				socket.close();
				userEntityDao.offline(IPAddress);
				logger.info("connection closed");
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}

	private class UpdateThread implements Runnable {
		@Override
		public void run() {
			while (true) {
				if (dataEntityDao == null) {
					logger.error("dao is missing");
				}
				try {
					lock.lock();
					if (UpdateFlag) {
						UpdateFlag = false;
					} else if (dataEntity.data != null
							&& dataEntity.data.size() > 0) {
						if (dataEntityDao.updateEntity(dataEntity.data,
								IPAddress)) { // 更新数据库data
							List<DataEntity> connectDataEntities = dataEntityDao
									.queryConnectEntity(IPAddress); // 查找与当前用户特定数据一致的用户
							if (connectDataEntities != null
									&& connectDataEntities.size() > 1) {
								// 与请求连接者有相同数据的情况
								String connectedIp = "";
								int count = 0;
								DataEntity selfDataEntity = connectDataEntities
										.get(0);
								for (int i = 1; i < connectDataEntities.size(); i++) {
									if (checkConnected(selfDataEntity,
											connectDataEntities.get(i))) {
										count++;
										connectedIp = connectDataEntities
												.get(i).getiPAddress();
									}
								}
								if (count == 1) {
									output(connectedIp);
								}
							}
						}
						dataEntity.data = new HashMap<String, String>(); // 清空data
					}
					lock.unlock();
					Thread.sleep(4 * 1000);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}
	}

	public boolean checkConnected(DataEntity self, DataEntity checker) {
		try {
			//设置设备编号
			if (self.getMC_deviceId().equals(checker.getMC_deviceId())) {
				return false;
			}
			//设置群路时钟
			String s_group_clock = self.getMC_groupClock();
			String c_group_clock = checker.getMC_groupClock();
			if (s_group_clock.equals("外钟")
					|| (s_group_clock.equals("内钟") && c_group_clock
							.equals("外钟"))
					|| (s_group_clock.equals("收钟") && !c_group_clock
							.equals("内钟"))) {
				return false;
			}
			//设置视频发钟  设置视频收钟
			String s_videoSendClock = self.getMC_videoSendClock();
			String s_videoReceivedClock = self.getMC_videoReceivedClock();
			String c_videoSendClock = checker.getMC_videoSendClock();
			String c_videoReceivedClock = checker.getMC_videoReceivedClock();
			if ((!s_videoSendClock.equals(c_videoReceivedClock)) || (!s_videoReceivedClock.equals(c_videoSendClock))) {
				return false;
			}
			//时钟选择
			if (!self.getVC_clock().equals(checker.getVC_clock()) || self.getVC_clock().equals("外钟")) {
				return false;
			}
			//调制解调器
			String s_sendDataRate = self.getMD_modemSendDataRate();
			String c_ReceiveDataRate = checker.getMD_deModemReceiveDataRate();
			if (!s_sendDataRate.equals(c_ReceiveDataRate)) {
				return false;
			}
			String s_modemScrambleType = self.getMD_modemScrambleType();
			String c_deModemDescrambleType = checker.getMD_deModemDescrambleType();
			if (!s_modemScrambleType.equals(c_deModemDescrambleType)) {
				return false;
			}
			String s_modemDifferEncode = self.getMD_modemDifferEncode();
			String c_deModemDifferEncode = checker.getMD_deModemDifferEncode();
			if (!s_modemDifferEncode.equals(c_deModemDifferEncode)) {
				return false;
			}
			String s_modemRSCode = self.getMD_modemRSCode();
			String c_deModemRSDecode = self.getMD_deModemRSDecode();
			if (!s_modemRSCode.equals(c_deModemRSDecode)) {
				return false;
			}
			String s_modemConvoluCode = self.getMD_modemConvoluCode();
			String c_deModemConvoluDecode = self.getMD_deModemConvoluDecode();
			if (!s_modemConvoluCode.equals(c_deModemConvoluDecode)) {
				return false;
			}
			String s_modemType = self.getMD_modemType();
			String c_deModemType = checker.getMD_deModemType();
			if (!s_modemType.equals(c_deModemType)) {
				return false;
			}
			String s_modemCarrierOutput = self.getMD_modemCarrierOutput();
			String c_modemCarrierOutput = checker.getMD_modemCarrierOutput();
			if (s_modemCarrierOutput.equals("N") || c_modemCarrierOutput.equals("N")) {
				return false;
			}
			String s_modemSendCarrierFrequence = self.getMD_modemSendCarrierFrequence();
			String c_deModemReceiveCarrierFrequence = checker.getMD_deModemReceiveCarrierFrequence();
			if (!s_modemSendCarrierFrequence.equals(c_deModemReceiveCarrierFrequence)) {
				return false;
			}
			String s_deModemReceiveDataRate = self.getMD_deModemReceiveDataRate();
			String c_modemSendDataRate = checker.getMD_modemSendDataRate();
			if (!s_deModemReceiveDataRate.equals(c_modemSendDataRate)) {
				return false;
			}
			String s_deModemDescrambleType = self.getMD_deModemDescrambleType();
			String c_modemScrambleType = checker.getMD_modemScrambleType();
			if (!s_deModemDescrambleType.equals(c_modemScrambleType)) {
				return false;
			}
			String s_deModemDifferEncode = self.getMD_deModemDifferEncode();
			String c_modemDifferEncode = checker.getMD_modemDifferEncode();
			if (!s_deModemDifferEncode.equals(c_modemDifferEncode)) {
				return false;
			}
			String s_deModemRSDecode = self.getMD_deModemRSDecode();
			String c_modemRSCode = checker.getMD_modemRSCode();
			if (!s_deModemRSDecode.equals(c_modemRSCode)) {
				return false;
			}
			String s_deModemConvoluDecode = self.getMD_deModemConvoluDecode();
			String c_modemConvoluCode = checker.getMD_modemConvoluCode();
			if (!s_deModemConvoluDecode.equals(c_modemConvoluCode)) {
				return false;
			}
			String s_deModemType = self.getMD_deModemType();
			String c_modemType = checker.getMD_modemType();
			if (!s_deModemType.equals(c_modemType)) {
				return false;
			}
			String s_deModemReceiveCarrierFrequence = self.getMD_deModemReceiveCarrierFrequence();
			String c_modemSendCarrierFrequence = checker.getMD_modemSendCarrierFrequence();
			if (!s_deModemReceiveCarrierFrequence.equals(c_modemSendCarrierFrequence)) {
				return false;
			}
			String s_frameType = self.getMD_frameType();
			String c_deframeType = checker.getMD_deframeType();
			if (!s_frameType.equals(c_deframeType)) {
				return false;
			}
			String s_frameParam = self.getMD_frameParam();
			String c_frameParam = checker.getMD_frameParam();
			if (s_frameParam.equals("N") || c_frameParam.equals("N")) {
				return false;
			}
			String s_frameSClockPhase = self.getMD_frameSClockPhase();
			String c_deframeSClockPhase = checker.getMD_deframeSClockPhase();
			if (!s_frameSClockPhase.equals(c_deframeSClockPhase)) {
				return false;
			}
			String s_frameSServiceInterface = self.getMD_frameSServiceInterface();
			String c_deframeRServiceInterface = checker.getMD_deframeRServiceInterface();
			if (!s_frameSServiceInterface.equals(c_deframeRServiceInterface)) {
				return false;
			}
			String s_frameSDataClock = self.getMD_frameSDataClock();
			String c_frameSDataClock = checker.getMD_frameSDataClock();
			if (!s_frameSDataClock.equals(c_frameSDataClock)) {
				return false;
			}
			String s_deframeType = self.getMD_deframeType();
			String c_frameType = checker.getMD_frameType();
			if (!s_deframeType.equals(c_frameType)) {
				return false;
			}
			String s_deframeSClockPhase = self.getMD_deframeSClockPhase();
			String c_frameSClockPhase = checker.getMD_frameSClockPhase();
			if (!s_deframeSClockPhase.equals(c_frameSClockPhase)) {
				return false;
			}
			String s_deframeRServiceInterface = self.getMD_deframeRServiceInterface();
			String c_frameSServiceInterface = checker.getMD_frameSServiceInterface();
			if (!s_deframeRServiceInterface.equals(c_frameSServiceInterface)) {
				return false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	public void output(String ipAddress) throws Exception {
		if (out == null) {
			logger.error("socket is disconnected");
			return;
		} else {
			if (ipAddress != null && !ipAddress.trim().equals("")) {
				outJson = new JSONObject();
				outJson.put("status", 1);
				JSONObject message = new JSONObject();
				message.put("connectedIp", ipAddress);
				outJson.put("message", message.toString());
				out.println(outJson.toString());
				out.flush();
				userEntityDao.updateConnectWith(IPAddress, ipAddress);
				logger.info(IPAddress + " connected with " + ipAddress);
			} else {
				return;
			}
		}
	}
}