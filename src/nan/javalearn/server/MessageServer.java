package nan.javalearn.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import nan.javalearn.client.QQWindow;

public class MessageServer {
	private int port = 1234;
	
	public void start() {
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Message Server Start!");
			while(true){
				Socket socket = ss.accept();
				new ServerReceiverThread(socket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
