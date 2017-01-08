package com.train.test;

//import com.train.dao.DataEntityDao;
import com.train.dao.IpEntityDao;
//import com.train.dao.UserEntityDao;
import com.train.model.DataEntity;

public class DBTest {
	public static void main(String[] args) {
		DataEntity dataEntity = new DataEntity();
		dataEntity.setiPAddress("192.168.1.0");
		dataEntity.setMC_deviceId("6");
		dataEntity.setMC_groupClock("233");
		dataEntity.setMC_groupInterface("2w444");
		dataEntity.setMC_groupRate("235434");
		dataEntity.setMC_videoRate("21324");
		dataEntity.setMC_videoReceivedClock("1231312");
		dataEntity.setMC_videoSendClock("123");
		dataEntity.setMD_deframeRServiceInterface("12312");
		dataEntity.setMD_deframeSClockPhase("12312");
		dataEntity.setMD_deframeType("123");
		dataEntity.setMD_deModemConvoluDecode("q12w3e1");
		dataEntity.setMD_deModemDescrambleType("123");
		dataEntity.setMD_deModemDifferEncode("1q23");
		dataEntity.setMD_deModemReceiveCarrierFrequence("123123");
		dataEntity.setMD_deModemReceiveDataRate("123");
		dataEntity.setMD_deModemRSDecode("123");
		dataEntity.setMD_deModemType("12312");
		dataEntity.setMD_frameSClockPhase("1321");
		dataEntity.setMD_frameSDataClock("123");
		dataEntity.setMD_frameSServiceInterface("123");
		dataEntity.setMD_frameType("123");
		dataEntity.setMD_frameParam("123");
		dataEntity.setMD_interfaceCodeType("13");
		dataEntity.setMD_interfaceType("13");
		dataEntity.setMD_modemCarrierOutput("123");
		dataEntity.setMD_modemConvoluCode("13");
		dataEntity.setMD_modemDifferEncode("e214");
		dataEntity.setMD_modemRSCode("1323");
		dataEntity.setMD_modemScrambleType("1312");
		dataEntity.setMD_modemSendCarrierFrequence("e12");
		dataEntity.setMD_modemSendDataRate("312");
		dataEntity.setMD_modemType("123");
		dataEntity.setVC_audioParameValues("132");
		dataEntity.setVC_clock("312");
		dataEntity.setVC_codeFormat("1231");
		dataEntity.setVC_frameRateValues("12321");
		dataEntity.setVC_imageFormat("13213");
		dataEntity.setVC_interfaceType("da");
		dataEntity.setVC_rate("123");
		dataEntity.setVC_synData("123");
		
		
//		DataEntityDao dataEntityDao = new DataEntityDao();
//		UserEntityDao userEntityDao = new UserEntityDao();
		IpEntityDao ipEntityDao = new IpEntityDao();
		try {
//			dataEntityDao.addEntity(dataEntity);
//			for (int i = 0; i < 10; i++) {
//				userEntityDao.addOperationCount("127.0.0.1");
//			}
			System.out.println(ipEntityDao.insertConnectNode(1, 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
