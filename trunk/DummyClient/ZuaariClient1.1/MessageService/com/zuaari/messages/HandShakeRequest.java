package com.zuaari.messages;

import com.zuaari.connectionmanager.ServiceConstants;



public class HandShakeRequest extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8102487587330977683L;
	private String ip;
	private String macAddress;
	private String browserInfo;
	private String systemInfo;
	public static int messageID=ServiceConstants.HANDSHAKE_REQ_MSG_ID;
	
	public HandShakeRequest(){
		super();
		super.msgNum=messageID;	
	}

	public HandShakeRequest(String ip, String macAddress, String browserInfo,
			String systemInfo) {
		super();
		this.ip = ip;
		this.macAddress = macAddress;
		this.browserInfo = browserInfo;
		this.systemInfo = systemInfo;
		super.msgNum=messageID;		
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getBrowserInfo() {
		return browserInfo;
	}

	public void setBrowserInfo(String browserInfo) {
		this.browserInfo = browserInfo;
	}

	public String getSystemInfo() {
		return systemInfo;
	}

	public void setSystemInfo(String systemInfo) {
		this.systemInfo = systemInfo;
	}

	@Override
	public String toString() {
		return "HandShakeRequest [browserInfo=" + browserInfo + ", ip=" + ip
				+ ", macAddress=" + macAddress + ", systemInfo=" + systemInfo
				+ "]" +super.toString();
	}

}
