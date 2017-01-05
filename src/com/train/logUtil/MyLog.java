package com.train.logUtil;

import java.io.File;

import org.apache.log4j.Appender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class MyLog {
	private Logger logger;

	public MyLog(String loggerName) throws Exception {
		deleteFile("./log/" + loggerName);
		logger = Logger.getLogger(loggerName);
		Layout layout = new PatternLayout(
				"%d{yyyy-MM-dd HH:mm:ss} [%c:%p %L] %m%n");
		Appender appender = new DailyRollingFileAppender(layout, "./log/"
				+ loggerName, "'.'yyyy-MM-dd");
		logger.addAppender(appender);
	}

	public Logger getLogger() {
		return logger;
	}

	private void deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}
}
