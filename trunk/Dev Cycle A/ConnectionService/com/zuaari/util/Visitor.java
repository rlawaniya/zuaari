package com.zuaari.util;

import com.zuaari.messages.IMessage;
import com.zuaari.server.Connection;
import com.zuaari.util.IClient;

public class Visitor implements IClient {
	String sessionId;
	String ip;
	String macAddress;
	String browserInfo;
	String systemInfo;
	Connection connection;

	public Visitor() {
	}

	public Visitor(String sessionId, String ip, String macAddress,
			String browserDetails, String osDetails) {
		super();
		this.sessionId = sessionId;
		this.ip = ip;
		this.macAddress = macAddress;
		this.browserInfo = browserDetails;
		this.systemInfo = osDetails;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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

	public void setBrowserInfo(String browserDetails) {
		this.browserInfo = browserDetails;
	}

	public String getSystemInfo() {
		return systemInfo;
	}

	public void setSystemInfo(String osDetails) {
		this.systemInfo = osDetails;
	}


	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}


	public void sendMessage(IMessage msg) {
     connection.sendMessage(msg);
	}
	public String toString() {
		return "Visitor [browserInfo=" + browserInfo + ", ip=" + ip
				+ ", macAddress=" + macAddress + ", systemInfo=" + systemInfo
				+ ", sessionId=" + sessionId + "]";
	}
}
