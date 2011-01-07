package com.zuaari.server;

import java.io.*;
import java.net.*;
import java.util.logging.Logger;

/**
 * 
 * @author RavishL This class take care of establishing initial connection with
 *         client. Client sent a handshake request to connection service,service
 *         open a connection socket dedicated to particular client service
 *         release this connection on logOut message.Incase of client crash
 *         where server does not get logOut message connection release by dead
 *         client cleanup reaper.
 * 
 */
public class ConnectionService {

	private ServerSocket serverSocket;
	private static final Logger logger = Logger
			.getLogger("com.zuaari.server.ConnectionService");

	public ConnectionService(int port) {

		logger.info("Binding Connection service  to " + port + " wait....");

		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException ex) {
			logger.info("Exception when creating server socket"
					+ ex.getStackTrace());
			stop();
		}
		while (true) {
			try {
				logger.info("waiting for accepting a connection...");
				Socket socket = serverSocket.accept();
				ConnectionHandler.createNewConnection(socket);
			} catch (IOException ex) {
				logger.info("Exception when listen  socket"
						+ ex.getStackTrace());
				ex.printStackTrace();
			}
		}

	}

	public void handshake() {

	}

	public void stop() {
		/**
		 * free all the resources
		 */
		try {
			if (serverSocket != null)
				serverSocket.close();
		} catch (IOException ioe) {
			// shouldn't happen
		}
	}

	public static void main(String argv[]) {
		new ConnectionService(7777);
	}
}
