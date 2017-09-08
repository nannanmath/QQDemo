package nan.javalearn.client;

import java.net.Socket;

import nan.javalearn.common.Message;
import nan.javalearn.util.SocketUtil;

public class Comm {
	private String IP = "127.0.0.1";
	private int port = 1234;
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
			// Send RequestFriendsMessage to refresh all clients friends list.
			Message msg = new ClientRequestFriendsMessage();
			SocketUtil.sendMessage(socket.getOutputStream(), msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Send talk message.
	public void sendText(String txt) {
		senderThread.setTxt(txt);
	}
	
	
	
	
	
	
	
	
	
}
