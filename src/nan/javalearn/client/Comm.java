package nan.javalearn.client;

import java.net.Socket;

public class Comm {
	private String IP = "127.0.0.1";
	private int port = 1234;
	private static Comm comm = null;
	
	private QQWindow qqWindow = null;
	private Socket socket = null;
	
	private ReceiverThread receiverThread = null;
	private SenderThread senderThread = null;
	
	public static Comm getInstance(){
		if(comm == null){
			comm = new Comm();
		}
		return comm;
	}
	
	public QQWindow getQqWindow() {
		return qqWindow;
	}

	public void setQQWindow(QQWindow qqWindow) {
		this.qqWindow = qqWindow;
	}

	private Comm() {
		try {
			socket = new Socket(IP, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Start receiver thread.
		receiverThread = new ReceiverThread(socket, qqWindow);
		receiverThread.start();
		//Start Sender thread.
		senderThread = new SenderThread(socket);
		senderThread.start();
	}

	public void sendText(String txt) {
		senderThread.setTxt(txt);
	}
	
	
	
	
	
	
	
	
	
}
