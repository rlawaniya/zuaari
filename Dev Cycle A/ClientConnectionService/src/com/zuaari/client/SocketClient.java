package com.zuaari.client;
/**
 * DELETE IT
 */

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

import com.zuaari.messages.*;

import java.io.*;
import java.net.*;

class SocketClient extends JFrame implements ActionListener {

	JLabel text, clicked;
	JButton button;
	JPanel panel;
	JTextField textField;
	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	int count = 0;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	SocketClient() { // Begin Constructor
		text = new JLabel("Text to send over socket:");
		textField = new JTextField(20);
		button = new JButton("Click Me");
		button.addActionListener(this);
		//listener();
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.white);
		getContentPane().add(panel);
		panel.add("North", text);
		panel.add("Center", textField);
		panel.add("South", button);
	} // End Constructor

	public void actionPerformed(ActionEvent event) {
		
		if (count == 0) {
			count = 1;
			HandShakeRequest hreq = new HandShakeRequest();
			hreq.setIp("6");
			actionPerformed1(event, hreq);
			return;
		}
		if (count == 1) {
			count = 2;
			actionPerformed1(event, new RegistrationRequest());
			return;
		}
		if (count == 2) {
			count = 3;
			actionPerformed1(event, new LoginRequest());
			return;
		}
		if (count == 3) {
			count = 3;
			actionPerformed1(event, new ChatRequest());
			return;
		}
		Object source = event.getSource();

		if (source == button) {
			// Send data over socket
			String text = textField.getText();
			Message clientMsg = new Message();
			clientMsg.setMessage(text);
			try {
				oos.writeObject(clientMsg);
				System.out.println("Message sent");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// out.println(text);
			textField.setText(new String(""));

			// Receive text from server

			try {
				IMessage inputMsg = null;
				try {
					inputMsg = (IMessage) ois.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// String line = in.readLine();
				System.out.println("Text received :" + inputMsg.getClass()
						+ "  :" + inputMsg.toString());
			} catch (IOException e) {
				System.out.println("Read failed::" + e.toString());
				System.exit(1);
			}
		}
	}

	public void actionPerformed1(ActionEvent event, IMessage clientMsg) {
		Object source = event.getSource();
		if (source == button) {
			// Send data over socket
			String text = textField.getText();
			clientMsg.setMessage(text);
			try {
				oos.writeObject(clientMsg);
				System.out.println("Message sent");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// out.println(text);
			textField.setText(new String(""));

			// Receive text from server

//			try {
//				IMessage inputMsg = null;
//				try {
//					inputMsg = (IMessage) ois.readObject();
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				// String line = in.readLine();
//				System.out.println("Text received :" + inputMsg.getClass()
//						+ "  :" + inputMsg.toString());
//			} catch (IOException e) {
//				System.out.println("Read failed::" + e.toString());
//				System.exit(1);
//			}
		}
	}

	public void listenSocket() {
		// Create socket connection
		try {
			socket = new Socket("127.0.0.1", 7777);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());

			// out = new PrintWriter(socket.getOutputStream(), true);
			// in = new BufferedReader(new
			// InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			System.out.println("Unknown host: kq6py.eng::" + e.toString());
			System.exit(1);
		} catch (IOException e) {
			System.out.println("No I/O");
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		SocketClient frame = new SocketClient();
		frame.setTitle("Client Program");
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};

		frame.addWindowListener(l);
		frame.pack();
		frame.setVisible(true);
		frame.listenSocket();
	}

	public void listener() {
		while (true) {
			try {
				IMessage inputMsg = null;
				try {
					inputMsg = (IMessage) ois.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// String line = in.readLine();
				System.out.println("Text received :" + inputMsg.getClass()
						+ "  :" + inputMsg.toString());
			} catch (IOException e) {
				System.out.println("Read failed::" + e.toString());
				System.exit(1);
			}
		}
	}
}
