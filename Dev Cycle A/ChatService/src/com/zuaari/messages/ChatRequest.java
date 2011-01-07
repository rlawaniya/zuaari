package com.zuaari.messages;

import com.zuaari.chat.ChatService;

public class ChatRequest implements IMessage {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMessage(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public IMessage performAction() {
     return ChatService.getChatService().doChat(this);
	}

}
