package com.train.model;

public class IpEntity {
	private int id;
	private String ipaddress;
	private String connipaddress;

	public IpEntity() {

	}

	public IpEntity(int id, String ipaddress, String connipaddress) {
		this.id = id;
		this.ipaddress = ipaddress;
		this.connipaddress = connipaddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getConnipaddress() {
		return connipaddress;
	}

	public void setConnipaddress(String connipaddress) {
		this.connipaddress = connipaddress;
	}

}
