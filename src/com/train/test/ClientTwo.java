package com.train.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
import com.train.config.Config;


public class ClientTwo implements Runnable{
	private Socket socket;
	private InputStreamReader inputStreamReader;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	public ClientTwo(String name){
		try {
			socket = new Socket("127.0.0.1", Config.ServerPort);
			inputStreamReader = new InputStreamReader(socket.getInputStream());
			bufferedReader = new BufferedReader(inputStreamReader);
			printWriter = new PrintWriter(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			for (int j = 0; j < 10; j++) {
				JSONObject out = new JSONObject();
				JSONObject message = new JSONObject();
				message.put("MC_deviceId", "1");
				out.put("status", 2);
				out.put("message", message.toString());
				printWriter.write(out.toString()+"\n");
				printWriter.flush();
				String message2;
				if((message2 = bufferedReader.readLine()) != null) {
					System.out.println(message2);
				}
			}
			TimeUnit.SECONDS.sleep(15);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				socket.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
//		for (int i=0; i<32; i++) {
//			Thread thread = new Thread(new ClientTwo(String.valueOf(i)));
//			thread.start();
//		}
		Thread thread = new Thread(new ClientTwo("1"));
		thread.start();
	}
}














