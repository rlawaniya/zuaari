package com.zuaari.connectionmanager;

import com.zuaari.messages.IMessage;



public class MessageHandler implements IMessageHandler {

	public static MessageHandler msgHandler = new MessageHandler();

	private MessageHandler() {

	}

	public static MessageHandler getMessageHandler() {
		return msgHandler;
	}

	/**
	 * Action performed based on the message type.currenlty this function is
	 * working as delegator.
	 * 
	 * @TODO: Ideally handler should be created for each message to handle each
	 *        communication uniquely. These handlers can be changed and reloaded
	 *        on runtime.
	 */
	public void performAction(IMessage msg) {
		IMessage response = msg.performAction();
//		if (response != null) {
//			client.sendMessage(response);
//		}
	}

}
