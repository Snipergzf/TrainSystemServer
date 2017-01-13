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
	// 天线参数
	public static final String[] paramNames = { "序号", "参数组名", "卫星经度", "天线工作频段",
			"天线极化方式", "接收机工作状态", "接收机频偏", "接收机频率" };
	// 主界面在线情况标题
	public static final String[] onLineTitles = { "序号", "IP地址", "上线时间", "在线情况",
			"连接情况", "连接对象", "操作次数" };
	//主界面单人数据查询标题
	public static final String[] personTitle = {"参数名", "参数值", "参数名", "参数值"};
	// 天线工作频段选择
	public static final String[] workFreStrings = { "ku", "Ka" };
	// 天线极化方式选择
	public static final String[] polarizationStrings = { "水平", "垂直", "圆" };
	// 接收机工作状态选择
	public static final String[] workStatusStrings = { "工作", "中频自检" };
}
