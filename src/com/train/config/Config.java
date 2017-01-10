package com.train.config;

/**
 * @author gzf Configure file
 */
public class Config {
	public static final String URL = "jdbc:mysql://localhost:3306/trainsystem?verifyServerCertificate=false&useSSL=false";
	public static final String CharSet = "UTF-8";
	public static final String UserName = "root";
	public static final String Passwd = "gzfisme";
	public static final int ServerPort = 2333;
	public static final int CorePoolSize = 32; // 服务器线程池大小
	public static final int MaximumPoolSize = 32; // 服务器端最大的线程数，用于流量过大时应急线程最大数
	public static final int KeepAliveTime = 60 * 60 * 60; // 当客户端无数据向服务器发送超过KeepAliveTime的时间后该线程就会断掉，直到线程数为0
	public static final int CHECK = 1;
	public static final int UPDATE = 2;
	public static final int HEART = 4;
	
}
