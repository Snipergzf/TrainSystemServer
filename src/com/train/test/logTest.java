package com.train.test;

import org.apache.log4j.Logger;

public class logTest {
	public static Logger logger = Logger.getLogger(logTest.class);
	public static void main(String[] args) {
		logger.debug("我是logger, debug");
		logger.info("我是logger, info");
		logger.warn("我是logger, warn");
	}
}
