package com.train.config;

/**
 * @author gzf
 * Configure file 
 */
public class Config {
	public static String URL = "jdbc:mysql://localhost:3306/trainsystem?verifyServerCertificate=false&useSSL=false";
	public static String CharSet = "UTF-8";
	public static String UserName = "root";
	public static String Passwd = "gzfisme";
	public static String Table = "test";
	public static int ServerPort = 2333;
	public static int CorePoolSize = 32;		//服务器线程池大小
	public static int MaximumPoolSize = 32;		//服务器端最大的线程数，用于流量过大时应急线程最大数
	public static int KeepAliveTime = 60;		//当客户端无数据向服务器发送超过KeepAliveTime的时间后该线程就会断掉，直到线程数为0
	public static int ParamCount = 210;			//总共比对参数个数
//	public static String column1 = "column1";
//	public static String column2 = "column2";
//	public static String column3 = "column3";
	
}
