package com.train.communication;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
import org.json.*;

import com.train.dao.DataEntityDao;
import com.train.model.DataEntity;

/**
 * @author gzf
 *
 */
public class WorkerTwo implements Runnable{
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Logger logger;
	private Timer timer;
	private TimerUpdate timerUpdate;
	private int OperationCount = 0;     //操作次数统计
	private DataEntity dataEntity;
	private boolean UpdateFlag = false; //改动数据标记位
	private String IPAddress;
	private DataEntityDao dao;
	public WorkerTwo(Socket s, Logger logger, String IPAddress) throws IOException{
		this.timer = new Timer();
		this.dao = new DataEntityDao();
		this.dataEntity = new DataEntity();
		this.IPAddress = IPAddress;
		this.socket = s;
		this.logger = logger;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//Enable auto-flush
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
	}
	@Override
	public void run() {
		String str = "";
		try {
			timerUpdate = new TimerUpdate();
			timer.scheduleAtFixedRate(timerUpdate, 1000, 4000);
			while(in != null && (str = in.readLine()) != null) {
				if (!str.equals("")) {
					UpdateFlag = true;
					OperationCount++;
					dataEntity.update(str);
				}
			}
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}finally {
			try {
				socket.close();
				timer.cancel();
				logger.info(socket.getInetAddress()+" has closed");
			} catch (IOException e) {
				logger.severe(e.getMessage());
			}
		}
	}
	private class TimerUpdate extends TimerTask {
		@Override
		public void run(){
			if (dao == null) {
				logger.severe("dao is missing");
				return;
			}else if(!UpdateFlag){
				return;
			}else {
				try {
					if (!dao.updateEntity(dataEntity.data, IPAddress)) {
						logger.severe("Update failed");
					}
				} catch (Exception e) {
					logger.severe(e.getMessage());
				}
			}
			UpdateFlag = false;
//			output();
		}
//		public void output() {
//			if (out != null) {
//				logger.severe("socket is disconnected");
//				return;
//			}else {
//				if (ret != null && !ret.equals("")) {
//					JSONObject outjJsonObject = new JSONObject(ret);
//					int result = outjJsonObject.getInt("status");
//					String connectObject = outjJsonObject.getString("connectObject");
//					if (result == 1) {
//						logger.info(socket.getInetAddress()+" connect with "+connectObject);  //连线成功
//					}
//					out.print(ret+"\n");
//					out.flush();
//				}else {
//					return;
//				}
//			}
//		}
		
	}
}