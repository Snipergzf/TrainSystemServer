package com.train.Util;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.List;

import javax.swing.JFrame;

import com.train.model.DataEntity;
import com.train.model.UserEntity;

public class CommonUtil {

	// This method is used to calculate the coordinates of the inputFrame which
	// needed to be create in center
	public static int[] getCenterXY(JFrame inputFrame) {
		int[] ret = new int[2];
		Dimension windowSize = inputFrame.getSize();
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();
		ret[0] = centerPoint.x - windowSize.width / 2;
		ret[1] = centerPoint.y - windowSize.height / 2;
		return ret;
	}

	// This method is used to check whether a input string is made up by digit
	public static boolean checkDigit(String input) {
		for (char c : input.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	public static String[][] loadServerMainTableData(
			List<UserEntity> rowDataArrayList) {
		String[][] rowData;
		if (rowDataArrayList != null && !rowDataArrayList.isEmpty()) {
			rowData = new String[rowDataArrayList.size()][7];
			for (int i = 0; i < rowDataArrayList.size(); i++) {
				String[] tmp = new String[7];
				tmp[0] = Integer.toString(i + 1);
				tmp[1] = rowDataArrayList.get(i).getIpaddress();
				tmp[2] = rowDataArrayList.get(i).getCurrentlogin().toString()
						.substring(0, 19);
				tmp[3] = "在线";
				if (rowDataArrayList.get(i).getOnline() == 0) {
					tmp[3] = "不在线";
				}
				tmp[4] = "未连通";
				if (rowDataArrayList.get(i).getConnectstatus() == 1) {
					tmp[4] = "已连通";
				}
				tmp[5] = rowDataArrayList.get(i).getConnectwith();
				tmp[6] = String.valueOf(rowDataArrayList.get(i)
						.getOperationcount());
				rowData[i] = tmp;
			}
		} else {
			rowData = new String[0][7];
		}
		return rowData;
	}

	public static String[][] loadServerPersonTableData(DataEntity dataEntity) {
		String[][] rowData;
		if (dataEntity != null && dataEntity.getiPAddress() != null) {
			rowData = new String[28][4];
			rowData[0][0] = "IP地址";
			rowData[0][1] = dataEntity.getiPAddress();
			rowData[0][2] = "设备编号";
			rowData[0][3] = dataEntity.getMC_deviceId();
			rowData[1][0] = "群路速率";
			rowData[1][1] = dataEntity.getMC_groupRate();
			rowData[1][2] = "群路接口";
			rowData[1][3] = dataEntity.getMC_groupInterface();
			rowData[2][0] = "群路时钟";
			rowData[2][1] = dataEntity.getMC_groupClock();
			rowData[2][2] = "视频速率";
			rowData[2][3] = dataEntity.getMC_videoRate();
			rowData[3][0] = "视频发钟";
			rowData[3][1] = dataEntity.getMC_videoSendClock();
			rowData[3][2] = "视频收钟";
			rowData[3][3] = dataEntity.getMC_videoReceivedClock();
			rowData[4][0] = "线路接口";
			rowData[4][1] = dataEntity.getMD_interfaceType();
			rowData[4][2] = "时钟选择";
			rowData[4][3] = dataEntity.getVC_clock();
			rowData[5][0] = "线路速率";
			rowData[5][1] = dataEntity.getVC_rate();
			rowData[5][2] = "编码格式";
			rowData[5][3] = dataEntity.getVC_codeFormat();
			rowData[6][0] = "图像格式";
			rowData[6][1] = dataEntity.getVC_imageFormat();
			rowData[6][2] = "帧率";
			rowData[6][3] = dataEntity.getVC_frameRateValues();
			rowData[7][0] = "音频参数";
			rowData[7][1] = dataEntity.getVC_audioParameValues();
			rowData[7][2] = "同步数据";
			rowData[7][3] = dataEntity.getVC_synData();
			rowData[8][0] = "调制发数据速率";
			rowData[8][1] = dataEntity.getMD_modemSendDataRate();
			rowData[8][2] = "调制扰码方式";
			rowData[8][3] = dataEntity.getMD_modemScrambleType();
			rowData[9][0] = "调制差分编码";
			rowData[9][1] = dataEntity.getMD_modemDifferEncode();
			rowData[9][2] = "调制RS编码";
			rowData[9][3] = dataEntity.getMD_modemRSCode();
			rowData[10][0] = "调制卷积编码";
			rowData[10][1] = dataEntity.getMD_modemConvoluCode();
			rowData[10][2] = "调制方式";
			rowData[10][3] = dataEntity.getMD_modemType();
			rowData[11][0] = "载波输出";
			rowData[11][1] = dataEntity.getMD_modemCarrierOutput();
			rowData[11][2] = "调制发载波频率";
			rowData[11][3] = dataEntity.getMD_modemSendCarrierFrequence();
			rowData[12][0] = "解调收数据速率";
			rowData[12][1] = dataEntity.getMD_deModemReceiveDataRate();
			rowData[12][2] = "解调解扰方式";
			rowData[12][3] = dataEntity.getMD_deModemDescrambleType();
			rowData[13][0] = "解调差分译码";
			rowData[13][1] = dataEntity.getMD_deModemDifferEncode();
			rowData[13][2] = "解调RS译码";
			rowData[13][3] = dataEntity.getMD_deModemRSDecode();
			rowData[14][0] = "解调卷积译码";
			rowData[14][1] = dataEntity.getMD_deModemConvoluDecode();
			rowData[14][2] = "解调方式";
			rowData[14][3] = dataEntity.getMD_deModemType();
			rowData[15][0] = "解调收载波频率";
			rowData[15][1] = dataEntity.getMD_deModemReceiveCarrierFrequence();
			rowData[15][2] = "成帧类型";
			rowData[15][3] = dataEntity.getMD_frameType();
			rowData[16][0] = "成帧发时钟相位";
			rowData[16][1] = dataEntity.getMD_frameSClockPhase();
			rowData[16][2] = "成帧发勤务接口";
			rowData[16][3] = dataEntity.getMD_frameSServiceInterface();
			rowData[17][0] = "成帧发数据时钟";
			rowData[17][1] = dataEntity.getMD_frameSDataClock();
			rowData[17][2] = "解帧类型";
			rowData[17][3] = dataEntity.getMD_deframeType();
			rowData[18][0] = "解帧发时钟相位";
			rowData[18][1] = dataEntity.getMD_deframeSClockPhase();
			rowData[18][2] = "解帧收勤务接口";
			rowData[18][3] = dataEntity.getMD_deframeRServiceInterface();
			rowData[19][0] = "接口类型";
			rowData[19][1] = dataEntity.getMD_interfaceType();
			rowData[19][2] = "接口码型";
			rowData[19][3] = dataEntity.getMD_interfaceCodeType();
			rowData[20][0] = "基带环路";
			rowData[20][1] = dataEntity.getMD_systemBasicLoop();
			rowData[20][2] = "编码环路";
			rowData[20][3] = dataEntity.getMD_systemCodeLoop();
			rowData[21][0] = "帧环路";
			rowData[21][1] = dataEntity.getMD_systemFrameLoop();
			rowData[21][2] = "上变频器频率";
			rowData[21][3] = dataEntity.getUC_frequence();
			rowData[22][0] = "上变频器倒换";
			rowData[22][1] = dataEntity.getUC_LocalMachine();
			rowData[22][2] = "上变频器射频输出";
			rowData[22][3] = dataEntity.getUC_radioOutput();
			rowData[23][0] = "下变频器频率";
			rowData[23][1] = dataEntity.getDC_frequence();
			rowData[23][2] = "下变频器倒换";
			rowData[23][3] = dataEntity.getDC_LocalMachine();
			rowData[24][0] = "发射待机状态";
			rowData[24][1] = dataEntity.getHA_SendAwait();
			rowData[24][2] = "射频输出";
			rowData[24][3] = dataEntity.getHA_RadioFrequencyOutputW();
			rowData[25][0] = "卫星经度";
			rowData[25][1] = dataEntity.getSateLongitude();
			rowData[25][2] = "天线工作频段";
			rowData[25][3] = dataEntity.getAeWorkFre();
			rowData[26][0] = "天线极化方式";
			rowData[26][1] = dataEntity.getAePolarization();
			rowData[26][2] = "接收机工作状态";
			rowData[26][3] = dataEntity.getReWorkStatus();
			rowData[27][0] = "接收机频偏";
			rowData[27][1] = dataEntity.getReOffsetFre();
			rowData[27][2] = "接收机频率";
			rowData[27][3] = dataEntity.getReFre();
		} else {
			rowData = new String[0][4];
		}
		return rowData;
	}
}