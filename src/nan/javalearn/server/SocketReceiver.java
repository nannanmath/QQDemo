package nan.javalearn.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import nan.javalearn.client.QQWindow;
import nan.javalearn.util.SocketUtil;

public class SocketReceiver extends Thread {
	private Socket socket = null;
	
	public SocketReceiver(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		while(true){
			try {
				InputStream is = socket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "gbk"));
			
			} catch (IOException e) {
				e.printStackTrace();
			}							
		}
	}					

}
