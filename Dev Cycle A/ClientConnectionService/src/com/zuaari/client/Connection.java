package com.zuaari.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;
import com.zuaari.messages.HandShakeRequest;
import com.zuaari.messages.HandShakeResponse;
import com.zuaari.messages.IMessage;
import com.zuaari.client.IMessageHandler;
import com.zuaari.client.MessageHandler;

public class Connection {
	/**
	 * This class will take care of creating a instance of socket connection
	 */

	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	public final static int HANDSHAKE_RES_SUCCESS = 1000;

	private final Logger logger = Logger
			.getLogger("com.zuaari.client.Connection");

	// Constructor create a connection
	Connection(String serverIP, int port) {
		try {
			socket = new Socket(serverIP, port);
			run();
		} catch (UnknownHostException e) {
			logger.info("client.Connection Unknown host: Server does not exist/accepting connection on IP"
					+ serverIP + " port=" + port);
			System.exit(1);
		} catch (IOException e) {
			logger.info("client Connection : No I/O");
			System.exit(1);
		}
	}

	/**
	 * Currently we have assumption that client maintain max one connection
	 * only. This implementation (single thread)needs to be changed to meet
	 * future requirement of maintaining multiple connection on client in case
	 * of game / table host on more then one servers.
	 */

	public void run() {
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			sendFirstMessage(true);
			Thread thread = new Thread(new MessageListener(this));
			thread.start();
		} catch (IOException ioe) {
			logger.info("Exception when reading/writing socket stream");
			ioe.printStackTrace();
			try {
				oos.close();
				ois.close();
			} catch (IOException ex) {
				// should not happen
			}
			close();
		}
	}

	private void sendFirstMessage(boolean retryOn) {
		HandShakeRequest firstMessage = new HandShakeRequest();
		sendMessage(firstMessage);
		try {
			HandShakeResponse firstResponse = (HandShakeResponse) ois
					.readObject();
			logger.info(" first message " + firstMessage.toString());
			if (firstResponse.getHandshakeRes() != HANDSHAKE_RES_SUCCESS) {
				System.exit(1);
			}

		} catch (Exception ex) {
			if (retryOn) {
				sendFirstMessage(false);
			}
			close();
			ex.printStackTrace();
			System.exit(1);
		}

	}

	public void sendMessage(IMessage message) {
		try {
			oos.writeObject(message);
			logger.info("Message sent " + message);
		} catch (IOException exp) {
			exp.printStackTrace();
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

	class MessageListener implements Runnable {
		Connection connection;

		public MessageListener(Connection conn) {
			this.connection = conn;
		}
		public void run() {
			IMessage serverMessage;
			IMessageHandler msgHandler = MessageHandler.getMessageHandler();
			while (true) {
				try {
					// List serverMessage = (List) ois.readObject();
					serverMessage = (IMessage) ois.readObject();
					logger.info("printing server message ---->" + serverMessage);

					msgHandler.performAction(serverMessage, connection);
				} catch (ClassNotFoundException cce) {
					logger.info("ClassNotFoundException when converting byte to object message");
					cce.printStackTrace();
				} catch (Exception ex) {
					close();
					ex.printStackTrace();
					break;
				}
			}
		}
	}

}
