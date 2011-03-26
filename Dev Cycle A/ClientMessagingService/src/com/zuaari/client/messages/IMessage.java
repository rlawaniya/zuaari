package com.zuaari.client.messages;

import java.io.Serializable;

/**
 * Base Interface for server-client communication. All the messages b/w server
 * and client must extend this interface.
 * 
 * @author Ravish
 * 
 */

public interface IMessage extends Serializable {

	String getMessage();

	void setMessage(String msg);

	IMessage performAction();

}
