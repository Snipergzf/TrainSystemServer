package com.train.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import com.train.communication.WorkerTwo;
import com.train.config.Config;
import com.train.database.Connector;

/**
 * @author gzf
 *
 */
public class Server {
	private static Connector connector;
	private static Logger logger;
	private static ServerSocket serverSocket;
	private static ThreadPoolExecutor executor;
	public static void init() {
		logger = Logger.getLogger("TrainSystemServer");
		logger.info("Server initializing...");
		try {
			connector = new Connector(logger);
			serverSocket = new ServerSocket(Config.ServerPort);
			executor = new ThreadPoolExecutor(Config.CorePoolSize, Config.MaximumPoolSize, Config.KeepAliveTime, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(12));
			executor.allowCoreThreadTimeOut(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void go() throws IOException{
		try {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				logger.info("get a connection: "+clientSocket.getInetAddress());
				try {
					executor.execute(new WorkerTwo(clientSocket, connector, logger, clientSocket.getInetAddress().toString()));
				} catch (Exception e) {
					logger.severe(e.getMessage());
					logger.info(clientSocket.getInetAddress()+" has closed");
					clientSocket.close();
				}
			}
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}finally {
			serverSocket.close();
			logger.info("Server Exit.");
		}
	}
	public static void main(String[] args) {
		init();
		try {
			go();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
