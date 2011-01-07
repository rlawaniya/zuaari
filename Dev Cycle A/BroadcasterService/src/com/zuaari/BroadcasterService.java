package com.zuaari;

import com.zuaari.messages.IMessage;
import com.zuaari.session.ISessionService;
import com.zuaari.session.SessionService;
import com.zuaari.util.IClient;

/**
 * This class give ability to send a message to multiple users. users selection
 * can be based on different criteria : example (i) Send messages to players
 * playing on a particular table (ii) Send messages to players subscribed to a
 * particular table (iii) Send messages to all the logged in player (iv) Send
 * messages to all the visitors (v) Send message to all the visitors and logged
 * in players so on ...
 * 
 * @author RavishL
 * 
 */
public class BroadcasterService {
	static BroadcasterService broadcasterService;

	private BroadcasterService() {
	}

	public static BroadcasterService getBroadcasterService() {
		if (broadcasterService == null) {
			broadcasterService = new BroadcasterService();
		}
		return broadcasterService;
	}

	static ISessionService sessionService = SessionService.getSessionService();

	public static void sendMessage(Table table, IMessage message) {

	}
  /**
   * TODO: send the message in separate thread
   * it must be multi-threaded
   * @param message
   */
	public static void sendMessageToVisitors(IMessage message) {
		for (IClient client : sessionService.getVisitors()) {
			System.out.println("sendMessageToVisitors:=:" + client.toString());
			client.sendMessage(message);
		}
	}

	public static void sendMessageToLoggedInUsers(IMessage message) {

	}

	public static void sendMessageToTableSubscribers(Table table,
			IMessage message) {

	}

}
