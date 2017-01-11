package com.train.model;

public class AerialEntity {
	private int id;
	private String aerialName;
	private String sateLongitude; //卫星经度
	private String aeWorkFre; // 天线工作频段
	private String aePolarization; // 天线极化方式
	private String reWorkStatus;// 接收机工作状态
	private String reOffsetFre;// 接收机频偏
	private String reFre;// 接收机频率

	public String getAerialName() {
		return aerialName;
	}

	public void setAerialName(String aerialName) {
		this.aerialName = aerialName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSateLongitude() {
		return sateLongitude;
	}

	public void setSateLongitude(String sateLongitude) {
		this.sateLongitude = sateLongitude;
	}

	public String getAeWorkFre() {
		return aeWorkFre;
	}

	public void setAeWorkFre(String aeWorkFre) {
		this.aeWorkFre = aeWorkFre;
	}

	public String getAePolarization() {
		return aePolarization;
	}

	public void setAePolarization(String aePolarization) {
		this.aePolarization = aePolarization;
	}

	public String getReWorkStatus() {
		return reWorkStatus;
	}

	public void setReWorkStatus(String reWorkStatus) {
		this.reWorkStatus = reWorkStatus;
	}

	public String getReOffsetFre() {
		return reOffsetFre;
	}

	public void setReOffsetFre(String reOffsetFre) {
		this.reOffsetFre = reOffsetFre;
	}

	public String getReFre() {
		return reFre;
	}

	public void setReFre(String reFre) {
		this.reFre = reFre;
	}

}
