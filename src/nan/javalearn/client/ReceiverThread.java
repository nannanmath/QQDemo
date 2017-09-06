package nan.javalearn.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.List;

import nan.javalearn.util.SocketUtil;

public class ReceiverThread extends Thread {
	private Socket socket = null;
	private InputStream is = null;
	private QQWindow qqWindow = null;
	
	public ReceiverThread(Socket socket, QQWindow qqWindow) {
		try {
			this.socket = socket;
			this.qqWindow = qqWindow;
			this.is = socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(true){
			processMsg();
		}
	}

	private void processMsg() {
		int type = SocketUtil.readMsgType(is);
		int len = SocketUtil.readMsgLength(is);
		byte[] bytes = SocketUtil.readMsgContent(is, len);
		if(type == 0){ // friend list
			List<String> friends = SocketUtil.readFriends(bytes);
			qqWindow.refreshFriends(friends);
		} else if(type == 1){ // message content
			String message = SocketUtil.readMsgContent(bytes);
			qqWindow.appendHistory(message);
		} else {
			System.out.println("Type is Error!");
		}
	}
	
	
			
	
}
