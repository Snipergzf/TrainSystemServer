package com.train.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import com.train.config.Config;
import com.train.dao.AerialEntityDao;
import com.train.dao.DataEntityDao;
import com.train.dao.IpEntityDao;
import com.train.dao.UserEntityDao;
import com.train.model.AerialEntity;
import com.train.model.DataEntity;

/**
 * @author gzf
 * 
 */
public class ComWorker implements Runnable {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Logger logger;
	private DataEntity dataEntity;
	private boolean UpdateFlag = false; // 改动数据标记位
	private String IPAddress;
	private DataEntityDao dataEntityDao;
	private UserEntityDao userEntityDao;
	private IpEntityDao ipEntityDao;
	private AerialEntityDao aerialEntityDao;
	private Thread updateThread;
	private Lock lock;
	private JSONObject inJson;
	private JSONObject outJson;
	private DataEntity self;
	private DataEntity checker;
	private AerialEntity aerialEntity;

	public ComWorker(Socket s, Logger logger, String IPAddress)
			throws IOException {
		this.dataEntityDao = new DataEntityDao();
		this.userEntityDao = new UserEntityDao();
		this.ipEntityDao = new IpEntityDao();
		this.aerialEntityDao = new AerialEntityDao();
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
						logger.info(IPAddress + " UPDATE completed");
						UpdateFlag = true;
						break;
					case Config.CHECK:
						dataEntityDao.addEntity(IPAddress, dataJsonString);
						logger.info(IPAddress + " CHECK completed");
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
			// 隔4秒检查更新情况，如果有数据则更新；不管有无更新数据都进行连接检查
			while (true) {
				try {
					lock.lock();
					if (!UpdateFlag) {// 无数据更新
						checkConnected();
					} else if (dataEntity.data != null
							&& dataEntity.data.size() > 0) {// 有数据更新
						if (dataEntityDao.updateEntity(dataEntity.data,
								IPAddress)) {
							// 更新数据库data,并成功
							checkConnected();
							dataEntity.data = new HashMap<String, String>(); // 清空data
						}
						UpdateFlag = false;
					}
					lock.unlock();
					Thread.sleep(4 * 1000);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}
	}

	public boolean checkConnected() {
		try {
			// 检查卫星参数组是否为空
			aerialEntity = aerialEntityDao.queryByIp(IPAddress);
			if (aerialEntity == null || aerialEntity.getAerialName() == null) {
				return false;
			}
			// 查询并检查当前用户的参数对象
			self = dataEntityDao.queryEntity(IPAddress);
			String connipaddress = ipEntityDao.queryConn(IPAddress);
			if (connipaddress == null || connipaddress.equals("")) {
				return false;
			}
			// 查询并检查需匹配用户的参数对象
			checker = dataEntityDao.queryEntity(connipaddress);
			if (self == null || checker == null
					|| self.getMC_deviceId() == null
					|| checker.getMC_deviceId() == null) {
				return false;
			}
			// 设置设备编号
			if (self.getMC_deviceId().equals(checker.getMC_deviceId())) {
				// 告知用户设备编号相同
				outputError();
				return false;
			}
			// 检查必须相同的参数部分
			// 群路速率
			if (!self.getMC_groupRate().equals(checker.getMC_groupRate())) {
				return false;
			}
			// 群路接口
			if (!self.getMC_groupInterface().equals(
					checker.getMC_groupInterface())) {
				return false;
			}
			// 视频速率
			if (!self.getMC_videoRate().equals(checker.getMC_videoRate())) {
				return false;
			}
			// 视频编解码器接口类型
			if (!self.getVC_interfaceType().equals(
					checker.getVC_interfaceType())) {
				return false;
			}
			// 视频编解码器传输速率
			if (!self.getVC_rate().equals(checker.getVC_rate())) {
				return false;
			}
			// 视频编解码器编码格式
			if (!self.getVC_codeFormat().equals(checker.getVC_codeFormat())) {
				return false;
			}
			// 视频编解码器图像格式
			if (!self.getVC_imageFormat().equals(checker.getVC_imageFormat())) {
				return false;
			}
			// 视频编解码器帧率
			if (!self.getVC_frameRateValues().equals(
					checker.getVC_frameRateValues())) {
				return false;
			}
			// 视频编解码器音频参数
			if (!self.getVC_audioParameValues().equals(
					checker.getVC_audioParameValues())) {
				return false;
			}
			// 视频编解码器同步数据设置
			if (!self.getVC_synData().equals(checker.getVC_synData())) {
				return false;
			}
			// 调制解调器接口类型
			if (!self.getMD_interfaceType().equals(
					checker.getMD_interfaceType())) {
				return false;
			}
			// 调制解调器接口码型
			if (!self.getMD_interfaceCodeType().equals(
					checker.getMD_interfaceCodeType())) {
				return false;
			}
			// 调制解调器基带环路
			if (self.getMD_systemBasicLoop().equals("ON")
					|| checker.getMD_systemBasicLoop().equals("ON")) {
				return false;
			}
			// 调制解调器编码环路
			if (self.getMD_systemCodeLoop().equals("ON")
					|| checker.getMD_systemCodeLoop().equals("ON")) {
				return false;
			}
			// 调制解调器帧环路
			if (self.getMD_systemFrameLoop().equals("ON")
					|| checker.getMD_systemFrameLoop().equals("ON")) {
				return false;
			}

			// 无需一致
			// 设置群路时钟
			String s_group_clock = self.getMC_groupClock();
			String c_group_clock = checker.getMC_groupClock();
			if (s_group_clock.equals("外钟")
					|| (s_group_clock.equals("内钟") && c_group_clock
							.equals("外钟"))
					|| (s_group_clock.equals("收钟") && !c_group_clock
							.equals("内钟"))) {
				return false;
			}
			// 设置视频发钟
			if (!self.getMC_videoSendClock().equals(
					checker.getMC_videoReceivedClock())) {
				return false;
			}
			// 设置视频收钟
			if (!self.getMC_videoReceivedClock().equals(
					checker.getMC_videoSendClock())) {
				return false;
			}
			// 编解码器时钟选择
			String s_vcClock = self.getVC_clock();
			String c_vcClock = checker.getVC_clock();
			if (s_vcClock.equals("外时钟")
					|| (s_vcClock.equals("内时钟") && c_vcClock.equals("外时钟"))
					|| (s_vcClock.equals("收时钟") && !c_vcClock.equals("内时钟"))) {
				return false;
			}
			// 调制解调器发数据速率
			if (!self.getMD_modemSendDataRate().equals(
					checker.getMD_deModemReceiveDataRate())) {
				return false;
			}
			// 调制解调器扰码方式
			if (!self.getMD_modemScrambleType().equals(
					checker.getMD_deModemDescrambleType())) {
				return false;
			}
			// 调制解调器差分编码
			if (!self.getMD_modemDifferEncode().equals(
					checker.getMD_deModemDifferEncode())) {
				return false;
			}
			// 调制解调器RS编码
			if (!self.getMD_modemRSCode().equals(
					checker.getMD_deModemRSDecode())) {
				return false;
			}
			// 调制解调器卷积编码
			if (!self.getMD_modemConvoluCode().equals(
					checker.getMD_deModemConvoluDecode())) {
				return false;
			}
			// 调制解调器调制方式
			if (!self.getMD_modemType().equals(checker.getMD_deModemType())) {
				return false;
			}
			// 载波输出、上变频器倒换、上变频器射频输出、下变频器倒换、高频放大器的发射待机状态、射频输出
			if (self.getMD_modemCarrierOutput().equals("N")
					|| self.getUC_LocalMachine().equals("离")
					|| self.getUC_radioOutput().equals("断")
					|| checker.getDC_LocalMachine().equals("离")
					|| self.getHA_SendAwait().equals("待机")
					|| Integer.valueOf(self.getHA_RadioFrequencyOutputW()) <= 1) {
				return false;
			}
			// 发方调制解调器发载波频率、发方上变频器的频率、收方调制解调器收载波频率、收方下变频器的频率
			if ((Integer.valueOf(self.getMD_modemSendCarrierFrequence())
					+ Integer.valueOf(self.getUC_frequence()) - 1750) != (Integer
					.valueOf(checker.getMD_deModemReceiveCarrierFrequence()) + Integer
					.valueOf(checker.getDC_frequence()))) {
				return false;
			}
			// 调制解调器收数据速率
			if (!self.getMD_deModemReceiveDataRate().equals(
					checker.getMD_modemSendDataRate())) {
				return false;
			}
			// 调制解调器解扰方式
			if (!self.getMD_deModemDescrambleType().equals(
					checker.getMD_modemScrambleType())) {
				return false;
			}
			// 调制解调器差分译码
			if (!self.getMD_deModemDifferEncode().equals(
					checker.getMD_modemDifferEncode())) {
				return false;
			}
			// 调制解调器RS译码
			if (!self.getMD_deModemRSDecode().equals(
					checker.getMD_modemRSCode())) {
				return false;
			}
			// 调制解调器卷积译码
			if (!self.getMD_deModemConvoluDecode().equals(
					checker.getMD_modemConvoluCode())) {
				return false;
			}
			// 调制解调器解调方式
			if (!self.getMD_deModemType().equals(checker.getMD_modemType())) {
				return false;
			}
			// 收载波频率

			// 成帧类型
			if (!self.getMD_frameType().equals(checker.getMD_deframeType())) {
				return false;
			}
			// 成帧时钟相位
			if (!self.getMD_frameSClockPhase().equals(
					checker.getMD_deframeSClockPhase())) {
				return false;
			}
			// 成帧勤务接口
			if (!self.getMD_frameSServiceInterface().equals(
					checker.getMD_deframeRServiceInterface())) {
				return false;
			}
			// 成帧数据时钟
			if (!self.getMD_frameSDataClock().equals(
					checker.getMD_frameSDataClock())) {
				return false;
			}
			// 解帧类型
			if (!self.getMD_deframeType().equals(checker.getMD_frameType())) {
				return false;
			}
			// 解帧时钟相位
			if (!self.getMD_deframeSClockPhase().equals(
					checker.getMD_frameSClockPhase())) {
				return false;
			}
			// 解帧勤务接口
			if (!self.getMD_deframeRServiceInterface().equals(
					checker.getMD_frameSServiceInterface())) {
				return false;
			}

			// 比对卫星参数
			// 卫星经度
			if ((!self.getSateLongitude().equals(
					aerialEntity.getSateLongitude()))
					&& (!checker.getSateLongitude().equals(
							aerialEntity.getSateLongitude()))) {
				return false;
			}
			// 天线工作频段
			if ((!self.getAeWorkFre().equals(aerialEntity.getAeWorkFre()))
					|| (!checker.getAeWorkFre().equals(
							aerialEntity.getAeWorkFre()))) {
				return false;
			}
			// 天线极化方式
			if ((!self.getAePolarization().equals(
					aerialEntity.getAePolarization()))
					|| (!checker.getAePolarization().equals(
							aerialEntity.getAePolarization()))) {
				return false;
			}
			// 接收机工作状态
			if ((aerialEntity.getReWorkStatus().equals("中频自检"))
					|| (!self.getReWorkStatus().equals(
							aerialEntity.getReWorkStatus()))
					|| (!checker.getReWorkStatus().equals(
							aerialEntity.getReWorkStatus()))) {
				return false;
			}
			// 接收机频偏
			if ((!self.getReOffsetFre().equals(aerialEntity.getReOffsetFre()))
					|| (!checker.getReOffsetFre().equals(
							aerialEntity.getReOffsetFre()))) {
				return false;
			}
			// 接收机频率
			if ((!self.getReFre().equals(aerialEntity.getReFre()))
					|| (!checker.getReFre().equals(aerialEntity.getReFre()))) {
				return false;
			}

			// 表示连通，返回连接对象的IP
			outputConnect(checker.getiPAddress());
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public void outputConnect(String ipAddress) throws Exception {
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

	public void outputError() throws Exception {
		if (out == null) {
			logger.error("socket is disconnected");
			return;
		} else {
			if (IPAddress != null && !IPAddress.trim().equals("")) {
				outJson = new JSONObject();
				outJson.put("status", 0);
				JSONObject message = new JSONObject();
				message.put("DeviceIdError", "Same deviceId");
				outJson.put("message", message.toString());
				out.println(outJson.toString());
				out.flush();
				logger.info("Same deviceId");
			} else {
				return;
			}
		}
	}
}