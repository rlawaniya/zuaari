package com.zuaari.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

import com.zuaari.messages.HandShakeRequest;
import com.zuaari.messages.HandShakeResponse;
import com.zuaari.messages.IMessage;
import com.zuaari.messages.Message;
import com.zuaari.session.ISessionService;
import com.zuaari.session.SessionService;
import com.zuaari.util.IClient;
import com.zuaari.util.ServiceConstants;
import com.zuaari.util.Visitor;

/**
 * ToDO connection handler should handle the Connection rename this class to
 * Connection and create new class name Connection Handler - Done
 * 
 * @author Ravish
 * 
 */

public class Connection implements Runnable {
	ISessionService sessionService = SessionService.getSessionService();
	IMessageHandler msgHandler=MessageHandler.getMessageHandler();
	IClient client=null;
	Socket socket = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	private final Logger logger = Logger
			.getLogger("com.zuaari.server.ConnectionService");

	Connection(Socket socket) {
		logger.info("creating a connection handler...");
		this.socket = socket;
		Thread thread = new Thread(this);
		thread.start();
	}

	public void run() {
		IMessage clientMessage;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			try {
				HandShakeRequest firstMessage = (HandShakeRequest) ois
						.readObject();
				logger.info(" Connection Handler first message "
						+ firstMessage.toString());
				sendMessage(new HandShakeResponse(
						ServiceConstants.HANDSHAKE_RES_SUCCESS));
				sessionService.addVisitor("xxxx"+firstMessage.getMessage(),
						createVisitorFromHandShakeReq(firstMessage));

			} catch (Exception ex) {
				/**
				 * First message from client must be handshake else close the
				 * streams/socket and return
				 */
				oos.writeObject(new HandShakeResponse(
						ServiceConstants.HANDSHAKE_RES_FAILURE));
				oos.close();
				ois.close();
				close();
				ex.printStackTrace();
			}
			while (true) {
				try {
					// List clientMessage = (List) ois.readObject();
					clientMessage = (IMessage) ois.readObject();
					logger
							.info("printing client message ---->"
									+ clientMessage);

					msgHandler.performAction(clientMessage, client);
				} catch (ClassNotFoundException cce) {
					logger
							.info("ClassNotFoundException when converting byte to object message");
					cce.printStackTrace();

				} catch (Exception ex) {
					oos.close();
					ois.close();
					close();
					ex.printStackTrace();
					break;
				}
			}
		} catch (IOException ioe) {
			logger.info("Exception when reading/writing socket stream");
			ioe.printStackTrace();
			try {
				oos.close();
				ois.close();
			} catch (IOException ex) {
				// should nt happen
			}
			close();
		}

	}

	public void logOut() throws IOException {
		close();
	}

	void close() {
		logger.info("Closing socket");
		try {
			if (socket != null)
				socket.close();
		} catch (Exception ex) {
			// shouldnt happen
		}
	}

	Visitor createVisitorFromHandShakeReq(HandShakeRequest hsReq) {
		Visitor visitor = new Visitor();
		visitor.setIp(hsReq.getIp());
		visitor.setMacAddress(hsReq.getMacAddress());
		visitor.setBrowserInfo(hsReq.getBrowserInfo());
		visitor.setSystemInfo(hsReq.getSystemInfo());
		visitor.setConnection(this);
		this.client=visitor;
		return visitor;
	}

	public void sendMessage(IMessage msg) {
		try {
			logger.info("Connection: sendingMessage :" + msg.toString());
			oos.writeObject(msg);
		} catch (IOException e) {
			logger.info("Connection: IOException ");
			e.printStackTrace();
		}
	}

}
