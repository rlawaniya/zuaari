package com.zuaari.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class ConnectionHandler2 implements Runnable {
	Socket client = null;
	private final Logger logger = Logger
			.getLogger("com.zuaari.server.ConnectionService2");

	ConnectionHandler2(Socket client) {
		logger.info("creating a connection handler. 2..");
		this.client = client;
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client
					.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			String line = null;
			while (true) {
				while (true) {
					try {
						line = in.readLine();
						System.out.println(" client input line :" +line);
						// Send data back to client
						out.println("server response : " + line);
					} catch (IOException e) {
						System.out.println("Read failed");
						System.exit(-1);
					}
				}
			}
		} catch (IOException ioe) {
			logger.info("Exception when reading/writing socket stream");
			ioe.printStackTrace();

		}

	}

}
