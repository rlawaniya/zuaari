package com.zuaari.connectionmanager;

import com.zuaari.messages.IMessage;


public interface IMessageHandler {

	public void performAction(IMessage msg);
	
}
