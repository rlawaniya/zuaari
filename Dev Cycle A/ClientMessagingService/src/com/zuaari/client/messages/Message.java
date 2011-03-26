package com.zuaari.client.messages;

/**
 * 
 * Base class for client-server communication. DONT EXTEND THIS CLASS SIMPLY FOR
 * SERVER-CLIENT COMMUNICATION MESSAGE UNTIL YOU ARE SURE ABT PRESENT AND FUTURE
 * REQUIREMENTS. Ideally all the server-client messages should extend IMessage
 * interface. This gives a message flexibility to extend other class. Example
 * :Incoming message can be used as DB type.NO need to create a new class for DB
 * type.
 * 
 * 
 * @author Ravish
 * 
 */
public class Message implements IMessage {

	private static final long serialVersionUID = -7761682796353397214L;
	private String message;
	protected int msgNum;

	public Message() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getMsgNum() {
		return msgNum;
	}

	public void setMsgNum(int msgNum) {
		this.msgNum = msgNum;
	}

	public String toString() {
		return "Message [msgNum=" + msgNum + ", message=" + message + "]";
	}

	@Override
	public IMessage performAction() {
		// TODO Auto-generated method stub
		return null;
	}

}
