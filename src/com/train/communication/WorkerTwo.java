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
import java.util.logging.Logger;
import org.json.JSONObject;
import com.train.config.Config;
import com.train.dao.DataEntityDao;
import com.train.dao.UserEntityDao;
import com.train.model.DataEntity;
import com.train.model.UserEntity;

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
					int requestType = inJson.getInt("type");
					switch (requestType) {
					case Config.UPDATE:
						userEntityDao.addOperationCount(IPAddress);
						dataEntity.update(str);
						UpdateFlag = true;
						break;
					case Config.CHECK:
						insert(inJson);
						break;
					case Config.HEART:
						break;
					}
				}
				lock.unlock();
			}
		} catch (Exception e) {
			logger.severe(e.getMessage());
		} finally {
			try {
				socket.close();
				logger.info(socket.getInetAddress() + " has closed");
			} catch (IOException e) {
				logger.severe(e.getMessage());
			}
		}
	}

	private class UpdateThread implements Runnable {
		@Override
		public void run() {
			while (true) {
				if (dataEntityDao == null) {
					logger.severe("dao is missing");
				}
				try {
					lock.lock();
					if (UpdateFlag) {
						UpdateFlag = false;
					} else if (dataEntity.data != null
							&& dataEntity.data.size() > 0) {
						dataEntityDao.updateEntity(dataEntity.data, IPAddress);
						DataEntity connecterDataEntity = dataEntityDao
								.queryEntity(IPAddress);
						if (connecterDataEntity != null) {
							List<DataEntity> connectedDataEntities = dataEntityDao
									.queryEntity(dataEntity.data, IPAddress);
							if (connectedDataEntities != null
									&& connectedDataEntities.size() > 0) {
								// 与请求连接者有相同数据的情况
								String connectedIp = "";
								int count = 0;
								for (DataEntity dataEntity : connectedDataEntities) {
									if (dataEntity.toString().equals(
											connecterDataEntity.toString())
											&& !dataEntity.getiPAddress()
													.equals(connecterDataEntity
															.getiPAddress())) {
										count++;
										connectedIp = dataEntity.getiPAddress();
									}
								}
								if (count == 1) {
									output(connectedIp);
								}
							}
						}
						dataEntity.data = new HashMap<String, String>();
					}
					lock.unlock();
					Thread.sleep(4 * 1000);
				} catch (Exception e) {
					logger.severe(e.getMessage());
				}
			}
		}
	}

	public void insert(JSONObject inJsonObject){
		//TODO
	}
	
	public void output(String ipAddress) {
		if (out != null) {
			logger.severe("socket is disconnected");
			return;
		} else {
			if (ipAddress != null && !ipAddress.trim().equals("")) {
				outJson = new JSONObject();
				outJson.put("status", 1);
				outJson.put("message", new JSONObject(ipAddress));
				out.print(outJson.toString() + "\n");
				out.flush();
				logger.info(IPAddress + " connected with " + ipAddress);
			} else {
				return;
			}
		}
	}
}