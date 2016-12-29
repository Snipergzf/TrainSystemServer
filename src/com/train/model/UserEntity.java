package com.train.model;

import java.sql.Timestamp;

public class UserEntity {
	private String ipaddress;
	private Timestamp currentlogin;
	private int online;
	private int connectstatus;
	private String connectwith;
	private int operationcount;

	public UserEntity() {
	}

	public UserEntity(String ipaddress, String lastlogin, Timestamp currentlogin,
			int online, int connectstatus, String connectwith,
			int operationcount) {
		this.ipaddress = ipaddress;
		this.currentlogin = currentlogin;
		this.online = online;
		this.connectstatus = connectstatus;
		this.connectwith = connectwith;
		this.operationcount = operationcount;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public Timestamp getCurrentlogin() {
		return currentlogin;
	}

	public void setCurrentlogin(Timestamp currentlogin) {
		this.currentlogin = currentlogin;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

	public int getConnectstatus() {
		return connectstatus;
	}

	public void setConnectstatus(int connectstatus) {
		this.connectstatus = connectstatus;
	}

	public String getConnectwith() {
		return connectwith;
	}

	public void setConnectwith(String connectwith) {
		this.connectwith = connectwith;
	}

	public int getOperationcount() {
		return operationcount;
	}

	public void setOperationcount(int operationcount) {
		this.operationcount = operationcount;
	}

}
