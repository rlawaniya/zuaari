package com.zuaari.client;

import com.zuaari.messages.IMessage;

public interface IMessageHandler {
	public void performAction(IMessage msg,Connection connection);

}
