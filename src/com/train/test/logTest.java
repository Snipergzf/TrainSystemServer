package com.train.test;

import org.apache.log4j.Appender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.PatternLayout;
import com.train.logUtil.MyLog;

public class logTest {

	public static void main(String[] args) {
		
//		MDC.put("LOGIN_USER_IP", ipString);
//		logger.debug("我是logger, debug");
//		ipString = "186.192.12.3";
//		MDC.put("LOGIN_USER_IP", ipString);
//		logger.info("我是logger, info");
//		logger.warn("我是logger, warn");
//		String ipString = "127.0.0.1";
//		System.setProperty("myLog", ipString+".log");
//		Log logger = LogFactory.getLog("loga");
//		logger.warn("我是logger, warn");
//		System.out.println(System.getProperty("myLog"));
		try {
			MDC.put("LOGIN_USER_IP", "127.0.1.0");
//			Logger consoleLogger = Logger.getLogger(logTest.class);
			Logger mylog = Logger.getLogger("mylog1");
			Layout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} [IP:%X{LOGIN_USER_IP}] [%c:%p %L] %m%n");
			Appender appender = new DailyRollingFileAppender(layout, "./log/mylog1", "'.'yyyy-MM-dd");
			mylog.addAppender(appender);
			mylog.warn("haha");
			
			Logger mylog2 = Logger.getLogger("mylog2");
			Layout layout2 = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} [IP:%X{LOGIN_USER_IP}] [%c:%p %L] %m%n");
			Appender appender2 = new DailyRollingFileAppender(layout2, "./log/mylog2", "'.'yyyy-MM-dd");
			mylog2.addAppender(appender2);
			mylog2.warn("hahahahah");
			
			MyLog myLog3 = new MyLog("mylog3");
			myLog3.getLogger().warn("hererere");
			
			MyLog myLog4 = new MyLog("mylog4");
			myLog4.getLogger().warn("harawefdawfw");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
	}
}
