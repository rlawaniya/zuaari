package com.zuaari.messages;

import com.zuaari.util.ServiceConstants;

public class HandShakeResponse extends Message {

	private int handShakeRes;
	private String sessionId;

	public static int messageID = ServiceConstants.HANDSHAKE_RES_MSG_ID;

	public HandShakeResponse(int handshakeRes) { //,String sessionId
		super();
		this.setHandShakeRes(handshakeRes);
		super.msgNum = messageID;
	}

	public void setHandShakeRes(int handShakeRes) {
		this.handShakeRes = handShakeRes;
	}

	public int getHandshakeRes() {
		return handShakeRes;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
