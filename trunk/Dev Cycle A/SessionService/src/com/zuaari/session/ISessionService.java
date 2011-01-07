package com.zuaari.session;

import java.util.Collection;
import java.util.List;

import com.zuaari.util.IClient;
import com.zuaari.util.User;

public interface ISessionService {

	public void addVisitor(String sessionId, IClient visitor);

	public void removeVisitor(String sessionId);

	public void addLoggedInUser(String sessionId, User user);

	public Collection<IClient> getVisitors();

}
