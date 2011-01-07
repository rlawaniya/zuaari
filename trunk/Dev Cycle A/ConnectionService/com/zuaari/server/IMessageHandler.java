package com.zuaari.server;

import com.zuaari.messages.IMessage;
import com.zuaari.util.IClient;

public interface IMessageHandler {

	public void performAction(IMessage msg,IClient client);
	
}
