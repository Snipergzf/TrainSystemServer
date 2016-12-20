package com.train.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;
import com.train.database.Connector;
import org.json.*;

/**
 * @author GZF
 *
 */
public class WorkerOne extends Thread{
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Connector connector;
	private Logger logger;
	public WorkerOne(Socket s, Connector conn, Logger logger) throws IOException{
		super();
		this.socket = s;
		this.connector = conn;
		this.logger = logger;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//Enable auto-flush
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		start();
	}
	@Override
	public void run() {
		super.run();
		String str = "";
		try {
			if ((str = in.readLine()) != null) {
//			while(in != null && (str = in.readLine()) != null) {
				if (!str.equals("")) {
					JSONObject inObject = new JSONObject(str);
					String name = inObject.getString("name");
					int phone = inObject.getInt("phone");
					if(connector.testInsert(name, phone)){
						JSONObject outObject = new JSONObject();
						outObject.put("status", 1);
						outObject.put("result", "insert successfully");
						out.print(outObject.toString());
						out.flush();
					}else {
						JSONObject outObject = new JSONObject();
						outObject.put("status", 0);
						outObject.put("result", "insert failed");
						out.print(outObject.toString());
						out.flush();
					}
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.severe(e.getMessage());
		}finally {
			try {
				socket.close();
				logger.info(socket.getInetAddress()+" has closed");
			} catch (IOException e) {
				logger.severe(e.getMessage());
			}
		}
	}
}