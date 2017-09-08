package nan.javalearn.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import nan.javalearn.util.SocketUtil;

public class MessageServer {
	public static List<String> friends = new ArrayList<String>();
	// Save all sockets who connect to server.
	public static List<Socket> sockets = new ArrayList<Socket>();
	
	private int port = 1234;
	
	public void start() {
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Message Server Start!");
			while(true){
				Socket socket = ss.accept();
				System.out.println("One client connect in.");
				sockets.add(socket);
				friends.add(SocketUtil.getHostname(socket));
				// Start a new thread for that client.
				new ServerReceiverThread(socket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
