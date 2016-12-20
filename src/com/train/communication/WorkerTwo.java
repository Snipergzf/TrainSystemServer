package com.train.communication;

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
import com.train.database.Connector;
import com.train.database.DataEntity;

import org.json.*;

/**
 * @author gzf
 *
 */
public class WorkerTwo implements Runnable{
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Connector connector;
	private Logger logger;
	private Timer timer;
	private TimerUpdate timerUpdate;
	private int OperationCount = 0;     //操作次数统计
	private DataEntity dataEntity;
	private boolean UpdateFlag = false; //改动数据标记位
	public WorkerTwo(Socket s, Connector conn, Logger logger, String IPAddress) throws IOException{
		this.timer = new Timer();
		this.dataEntity = new DataEntity(IPAddress);
		this.socket = s;
		this.connector = conn;
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
//					JSONObject inObject = new JSONObject(str);
//					String name = inObject.getString("name");
//					int phone = inObject.getInt("phone");
//					if(connector.testInsert(name, phone)){
//						logger.info(socket.getInetAddress()+" insert successfully");
//						JSONObject outObject = new JSONObject();
//						outObject.put("status", 1);
//						outObject.put("result", "insert successfully");
//						out.print(outObject.toString()+"\n");
//						out.flush();
//					}else {
//						logger.info(socket.getInetAddress()+" insert failed");
//						JSONObject outObject = new JSONObject();
//						outObject.put("status", 0);
//						outObject.put("result", "insert failed");
//						out.print(outObject.toString()+"\n");
//						out.flush();
//					}
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
		private String ret;

		@Override
		public void run(){
			if (connector == null) {
				logger.severe("connector is missing");
				return;
			}else if(!UpdateFlag){
				return;
			}else {
				try {
					ret = connector.getConnect(dataEntity.toString());
				} catch (Exception e) {
					logger.severe(e.getMessage());
				}
			}
			UpdateFlag = false;
			output();
		}
		public void output() {
			if (out != null) {
				logger.severe("socket is disconnected");
				return;
			}else {
				if (ret != null && !ret.equals("")) {
					JSONObject outjJsonObject = new JSONObject(ret);
					int result = outjJsonObject.getInt("status");
					String connectObject = outjJsonObject.getString("connectObject");
					if (result == 1) {
						logger.info(socket.getInetAddress()+" connect with "+connectObject);  //连线成功
					}
					out.print(ret+"\n");
					out.flush();
				}else {
					return;
				}
			}
		}
		
	}
}