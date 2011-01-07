package com.zuaari.session;

import com.zuaari.util.IClient;
import com.zuaari.util.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author Ravish
 * @info This class takes care of users session.A session can be belong to below
 *       catagories </br> (i) Visitor : user is not logged in but user can open
 *       a table and subscribe to watch the game. server should take care of
 *       broadcasting the game table status to such visitors with low
 *       priority.</br> (ii)Logged in users: user is logged in.User can either
 *       play or visit a game.User can update account details.</br>
 *       (iii)Disconnected users: Disconencte Users.These user session should be
 *       cleaned in a pre defined configurable regulare intervel by a
 *       reaper.</br> (iv)Inactive users: logged in users which are inactive for
 *       certain periods. No need to send lobby updates or table updates for
 *       such players.
 * 
 */

public class SessionService implements ISessionService {
	private HashMap<String, IClient> visitors;
	private HashMap<String, User> loggedInUsers;
	private HashMap<String, User> inactiveUsers;
	private HashMap<String, User> disconnectedUsers;
	private static SessionService sessionService = new SessionService();

	private SessionService() {
		if (sessionService == null) {
			visitors = new HashMap<String, IClient>();
			loggedInUsers = new HashMap<String, User>();
			inactiveUsers = new HashMap<String, User>();
			disconnectedUsers = new HashMap<String, User>();
			sessionService = this;
		}
	}

	public static SessionService getSessionService() {
		return sessionService;
	}

	public synchronized void addVisitor(String sessionId, IClient visitor) {
		visitors.put(sessionId, visitor);
	}

	public synchronized void removeVisitor(String sessionId) {
		visitors.remove(sessionId);
	}

	public synchronized void addLoggedInUser(String sessionId, User user) {
		if (visitors.remove(sessionId) != null) {
			loggedInUsers.put(sessionId, user);
		} else {
			// send error message
		}
	}
	
	public Collection<IClient> getVisitors(){
		return  visitors.values();
	}

}
