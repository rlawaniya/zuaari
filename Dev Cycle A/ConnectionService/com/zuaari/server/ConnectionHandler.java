package com.zuaari.server;

import java.net.Socket;

public class ConnectionHandler {

	public static void createNewConnection(Socket socket) {
		new Connection(socket);
	}

}
