package com.zuaari.chat;

import com.zuaari.BroadcasterService;
import com.zuaari.messages.ChatRequest;
import com.zuaari.messages.ChatResponse;

public class ChatService implements IChatService {
	static ChatService chatService;

	private ChatService() {
	}

	public static ChatService getChatService() {
		if (chatService == null) {
			chatService = new ChatService();
		}
		return chatService;
	}

	public ChatResponse doChat(ChatRequest chatReq) {

		BroadcasterService.sendMessageToVisitors(new ChatResponse());

		return null;
	}

}
