package com.train.model;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONObject;

public class DataEntity {
	private String iPAddress;
	private String modemSendDataRate;
	private String modemScrambleType;
	private String modemDifferEncode;
	private String modemRSCode;
	private String modemConvoluCode;
	private String modemType;
	private String modemCarrierOutput;
	public HashMap<String, String> data;
	
	public String getiPAddress() {
		return iPAddress;
	}

	public void setiPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}

	public String getModemSendDataRate() {
		return modemSendDataRate;
	}

	public void setModemSendDataRate(String modemSendDataRate) {
		this.modemSendDataRate = modemSendDataRate;
	}

	public String getModemScrambleType() {
		return modemScrambleType;
	}

	public void setModemScrambleType(String modemScrambleType) {
		this.modemScrambleType = modemScrambleType;
	}

	public String getModemDifferEncode() {
		return modemDifferEncode;
	}

	public void setModemDifferEncode(String modemDifferEncode) {
		this.modemDifferEncode = modemDifferEncode;
	}

	public String getModemRSCode() {
		return modemRSCode;
	}

	public void setModemRSCode(String modemRSCode) {
		this.modemRSCode = modemRSCode;
	}

	public String getModemConvoluCode() {
		return modemConvoluCode;
	}

	public void setModemConvoluCode(String modemConvoluCode) {
		this.modemConvoluCode = modemConvoluCode;
	}

	public String getModemType() {
		return modemType;
	}

	public void setModemType(String modemType) {
		this.modemType = modemType;
	}

	public String getModemCarrierOutput() {
		return modemCarrierOutput;
	}

	public void setModemCarrierOutput(String modemCarrierOutput) {
		this.modemCarrierOutput = modemCarrierOutput;
	}

	public void update(String str){
		JSONObject updateJson;
		if (str != null && !str.equals("")) {
			updateJson = new JSONObject(str);
			Iterator keys = updateJson.keys();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				data.put(key, updateJson.getString(key));
			}
		}
		updateJson = null;
	}
}
