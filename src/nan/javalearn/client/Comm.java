package nan.javalearn.client;

import java.net.Socket;

public class Comm {
	private String IP = "127.0.0.1";
	private int port = 1235;
	private static Comm comm = null;
	
	private QQWindow qqWindow = null;
	private Socket socket = null;
	
	private ReceiverThread receiverThread = null;
	private SenderThread senderThread = null;
	
	public static Comm getInstance(QQWindow qqWindow){
		if(comm == null){
			comm = new Comm(qqWindow);
		}
		return comm;
	}

	private Comm(QQWindow qqWindow) {
		try {
			this.qqWindow = qqWindow;
			socket = new Socket(IP, port);
			// Start receiver thread.
			receiverThread = new ReceiverThread(socket, qqWindow);
			receiverThread.start();
			//Start Sender thread.
			senderThread = new SenderThread(socket);
			senderThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Send talk message.
	public void sendText(String txt) {
		senderThread.setTxt(txt);
	}
	
	
	
	
	
	
	
	
	
}
