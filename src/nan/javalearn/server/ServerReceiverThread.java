package nan.javalearn.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ServerReceiverThread extends Thread {
	private Socket socket = null;
	private InputStream is = null;

	public ServerReceiverThread(Socket socket) {
		try {
			this.socket = socket;
			this.is = socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(true){
			
		}
	}

	
	
	
}
