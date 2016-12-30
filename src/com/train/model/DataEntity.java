package com.train.model;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONObject;

public class DataEntity {
	private String iPAddress;
	// 多功能复接器7个参数
	private String MC_deviceId; // 设备编号
	private String MC_groupRate; // 群路速率
	private String MC_groupInterface; // 群路接口
	private String MC_groupClock; // 群路时钟
	private String MC_videoRate; // 视频速率
	private String MC_videoSendClock; // 视频发钟
	private String MC_videoReceivedClock; // 视频收钟
	// 视频编码器8个参数
	private String VC_interfaceType; // 线路接口选择
	private String VC_clock; // 时钟选择
	private String VC_rate; // 线路速率设置
	private String VC_codeFormat; // 编码格式
	private String VC_imageFormat; // 图像格式
	private String VC_frameRateValues; // 帧率
	private String VC_audioParameValues; // 音频参数
	private String VC_synData; // 同步数据设置
	// 调制解调器25个参数
	private String MD_modemSendDataRate; // 调制 发数据速率
	private String MD_modemScrambleType; // 调制 扰码方式
	private String MD_modemDifferEncode; // 调制 差分编码
	private String MD_modemRSCode; // 调制 RS编码
	private String MD_modemConvoluCode; // 调制 卷积编码
	private String MD_modemType; // 调制 方式
	private String MD_modemCarrierOutput; // 载波输出
	private String MD_modemSendCarrierFrequence;// 调制 发载波频率
	private String MD_deModemReceiveDataRate; // 解调 收数据速率
	private String MD_deModemDescrambleType; // 解调 解扰方式
	private String MD_deModemDifferEncode; // 解调 差分译码
	private String MD_deModemRSDecode; // 解调 RS译码
	private String MD_deModemConvoluDecode; // 解调 卷积译码
	private String MD_deModemType; // 解调 方式
	private String MD_deModemReceiveCarrierFrequence;// 收载波频率
	private String MD_frameType; // 成帧 类型
	private String MD_inCarrierOutput; // 载波输出
	private String MD_frameSClockPhase; // 成帧 发时钟相位
	private String MD_frameSServiceInterface; // 成帧 发勤务接口
	private String MD_frameSDataClock; // 成帧 发数据时钟
	private String MD_deframeType; // 解帧 类型
	private String MD_deframeSClockPhase; // 解帧 发时钟相位
	private String MD_deframeRServiceInterface; // 解帧 收勤务接口
	private String MD_interfaceType; // 接口 类型
	private String MD_interfaceCodeType; // 接口 码型

	public HashMap<String, String> data;

	public DataEntity() {
		data = new HashMap<String, String>();
	}

	public String getiPAddress() {
		return iPAddress;
	}

	public void setiPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}

	public String getMC_deviceId() {
		return MC_deviceId;
	}

	public void setMC_deviceId(String mC_deviceId) {
		MC_deviceId = mC_deviceId;
	}

	public String getMC_groupRate() {
		return MC_groupRate;
	}

	public void setMC_groupRate(String mC_groupRate) {
		MC_groupRate = mC_groupRate;
	}

	public String getMC_groupInterface() {
		return MC_groupInterface;
	}

	public void setMC_groupInterface(String mC_groupInterface) {
		MC_groupInterface = mC_groupInterface;
	}

	public String getMC_groupClock() {
		return MC_groupClock;
	}

	public void setMC_groupClock(String mC_groupClock) {
		MC_groupClock = mC_groupClock;
	}

	public String getMC_videoRate() {
		return MC_videoRate;
	}

	public void setMC_videoRate(String mC_videoRate) {
		MC_videoRate = mC_videoRate;
	}

	public String getMC_videoSendClock() {
		return MC_videoSendClock;
	}

	public void setMC_videoSendClock(String mC_videoSendClock) {
		MC_videoSendClock = mC_videoSendClock;
	}

	public String getMC_videoReceivedClock() {
		return MC_videoReceivedClock;
	}

	public void setMC_videoReceivedClock(String mC_videoReceivedClock) {
		MC_videoReceivedClock = mC_videoReceivedClock;
	}

	public String getVC_interfaceType() {
		return VC_interfaceType;
	}

	public void setVC_interfaceType(String vC_interfaceType) {
		VC_interfaceType = vC_interfaceType;
	}

	public String getVC_clock() {
		return VC_clock;
	}

	public void setVC_clock(String vC_clock) {
		VC_clock = vC_clock;
	}

	public String getVC_rate() {
		return VC_rate;
	}

	public void setVC_rate(String vC_rate) {
		VC_rate = vC_rate;
	}

	public String getVC_codeFormat() {
		return VC_codeFormat;
	}

	public void setVC_codeFormat(String vC_codeFormat) {
		VC_codeFormat = vC_codeFormat;
	}

	public String getVC_imageFormat() {
		return VC_imageFormat;
	}

	public void setVC_imageFormat(String vC_imageFormat) {
		VC_imageFormat = vC_imageFormat;
	}

	public String getVC_frameRateValues() {
		return VC_frameRateValues;
	}

	public void setVC_frameRateValues(String vC_frameRateValues) {
		VC_frameRateValues = vC_frameRateValues;
	}

	public String getVC_audioParameValues() {
		return VC_audioParameValues;
	}

	public void setVC_audioParameValues(String vC_audioParameValues) {
		VC_audioParameValues = vC_audioParameValues;
	}

	public String getVC_synData() {
		return VC_synData;
	}

	public void setVC_synData(String vC_synData) {
		VC_synData = vC_synData;
	}

	public String getMD_modemSendDataRate() {
		return MD_modemSendDataRate;
	}

	public void setMD_modemSendDataRate(String mD_modemSendDataRate) {
		MD_modemSendDataRate = mD_modemSendDataRate;
	}

	public String getMD_modemScrambleType() {
		return MD_modemScrambleType;
	}

	public void setMD_modemScrambleType(String mD_modemScrambleType) {
		MD_modemScrambleType = mD_modemScrambleType;
	}

	public String getMD_modemDifferEncode() {
		return MD_modemDifferEncode;
	}

	public void setMD_modemDifferEncode(String mD_modemDifferEncode) {
		MD_modemDifferEncode = mD_modemDifferEncode;
	}

	public String getMD_modemRSCode() {
		return MD_modemRSCode;
	}

	public void setMD_modemRSCode(String mD_modemRSCode) {
		MD_modemRSCode = mD_modemRSCode;
	}

	public String getMD_modemConvoluCode() {
		return MD_modemConvoluCode;
	}

	public void setMD_modemConvoluCode(String mD_modemConvoluCode) {
		MD_modemConvoluCode = mD_modemConvoluCode;
	}

	public String getMD_modemType() {
		return MD_modemType;
	}

	public void setMD_modemType(String mD_modemType) {
		MD_modemType = mD_modemType;
	}

	public String getMD_modemCarrierOutput() {
		return MD_modemCarrierOutput;
	}

	public void setMD_modemCarrierOutput(String mD_modemCarrierOutput) {
		MD_modemCarrierOutput = mD_modemCarrierOutput;
	}

	public String getMD_modemSendCarrierFrequence() {
		return MD_modemSendCarrierFrequence;
	}

	public void setMD_modemSendCarrierFrequence(
			String mD_modemSendCarrierFrequence) {
		MD_modemSendCarrierFrequence = mD_modemSendCarrierFrequence;
	}

	public String getMD_deModemReceiveDataRate() {
		return MD_deModemReceiveDataRate;
	}

	public void setMD_deModemReceiveDataRate(String mD_deModemReceiveDataRate) {
		MD_deModemReceiveDataRate = mD_deModemReceiveDataRate;
	}

	public String getMD_deModemDescrambleType() {
		return MD_deModemDescrambleType;
	}

	public void setMD_deModemDescrambleType(String mD_deModemDescrambleType) {
		MD_deModemDescrambleType = mD_deModemDescrambleType;
	}

	public String getMD_deModemDifferEncode() {
		return MD_deModemDifferEncode;
	}

	public void setMD_deModemDifferEncode(String mD_deModemDifferEncode) {
		MD_deModemDifferEncode = mD_deModemDifferEncode;
	}

	public String getMD_deModemRSDecode() {
		return MD_deModemRSDecode;
	}

	public void setMD_deModemRSDecode(String mD_deModemRSDecode) {
		MD_deModemRSDecode = mD_deModemRSDecode;
	}

	public String getMD_deModemConvoluDecode() {
		return MD_deModemConvoluDecode;
	}

	public void setMD_deModemConvoluDecode(String mD_deModemConvoluDecode) {
		MD_deModemConvoluDecode = mD_deModemConvoluDecode;
	}

	public String getMD_deModemType() {
		return MD_deModemType;
	}

	public void setMD_deModemType(String mD_deModemType) {
		MD_deModemType = mD_deModemType;
	}

	public String getMD_deModemReceiveCarrierFrequence() {
		return MD_deModemReceiveCarrierFrequence;
	}

	public void setMD_deModemReceiveCarrierFrequence(
			String mD_deModemReceiveCarrierFrequence) {
		MD_deModemReceiveCarrierFrequence = mD_deModemReceiveCarrierFrequence;
	}

	public String getMD_frameType() {
		return MD_frameType;
	}

	public void setMD_frameType(String mD_frameType) {
		MD_frameType = mD_frameType;
	}

	public String getMD_inCarrierOutput() {
		return MD_inCarrierOutput;
	}

	public void setMD_inCarrierOutput(String mD_inCarrierOutput) {
		MD_inCarrierOutput = mD_inCarrierOutput;
	}

	public String getMD_frameSClockPhase() {
		return MD_frameSClockPhase;
	}

	public void setMD_frameSClockPhase(String mD_frameSClockPhase) {
		MD_frameSClockPhase = mD_frameSClockPhase;
	}

	public String getMD_frameSServiceInterface() {
		return MD_frameSServiceInterface;
	}

	public void setMD_frameSServiceInterface(String mD_frameSServiceInterface) {
		MD_frameSServiceInterface = mD_frameSServiceInterface;
	}

	public String getMD_frameSDataClock() {
		return MD_frameSDataClock;
	}

	public void setMD_frameSDataClock(String mD_frameSDataClock) {
		MD_frameSDataClock = mD_frameSDataClock;
	}

	public String getMD_deframeType() {
		return MD_deframeType;
	}

	public void setMD_deframeType(String mD_deframeType) {
		MD_deframeType = mD_deframeType;
	}

	public String getMD_deframeSClockPhase() {
		return MD_deframeSClockPhase;
	}

	public void setMD_deframeSClockPhase(String mD_deframeSClockPhase) {
		MD_deframeSClockPhase = mD_deframeSClockPhase;
	}

	public String getMD_deframeRServiceInterface() {
		return MD_deframeRServiceInterface;
	}

	public void setMD_deframeRServiceInterface(
			String mD_deframeRServiceInterface) {
		MD_deframeRServiceInterface = mD_deframeRServiceInterface;
	}

	public String getMD_interfaceType() {
		return MD_interfaceType;
	}

	public void setMD_interfaceType(String mD_interfaceType) {
		MD_interfaceType = mD_interfaceType;
	}

	public String getMD_interfaceCodeType() {
		return MD_interfaceCodeType;
	}

	public void setMD_interfaceCodeType(String mD_interfaceCodeType) {
		MD_interfaceCodeType = mD_interfaceCodeType;
	}

	public HashMap<String, String> getData() {
		return data;
	}

	public void setData(HashMap<String, String> data) {
		this.data = data;
	}

	public void update(String str) {
		JSONObject updateJson;
		if (str != null && !str.equals("")) {
			updateJson = new JSONObject(str);
			Iterator<?> keys = updateJson.keys();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				data.put(key, updateJson.getString(key));
			}
		}
		updateJson = null;
	}

//	public String toString() {
//		StringBuilder ret = new StringBuilder();
//		ret.append("modemSendDataRate=" + getModemSendDataRate() + ",");
//		ret.append("modemScrambleType=" + getModemScrambleType() + ",");
//		ret.append("modemDifferEncode=" + getModemDifferEncode() + ",");
//		ret.append("modemRSCode=" + getModemRSCode() + ",");
//		ret.append("modemConvoluCode=" + getModemConvoluCode() + ",");
//		ret.append("modemType=" + getModemType() + ",");
//		ret.append("modemCarrierOutput=" + getModemCarrierOutput() + ",");
//		ret.deleteCharAt(ret.length() - 1);
//
//		return ret.toString();
//	}
}
