package nan.javalearn.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.List;

import nan.javalearn.common.Message;
import nan.javalearn.common.MessageFactory;
import nan.javalearn.server.ServerPushFriendsMessage;
import nan.javalearn.server.ServerPushTalkMessage;
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
		Message msg = MessageFactory.parsePack(is);
		int type = msg.getType();
		switch(type){
		case Message.MESSAGE_TYPE_SERVER_PUSH_FRIENDS:
			List<String> friends = (List<String>)((ServerPushFriendsMessage)msg).getData();
			qqWindow.refreshFriends(friends);
			break;
		case Message.MESSAGE_TYPE_SERVER_TALK:
			String txt = (String)((ServerPushTalkMessage)msg).getData();
			qqWindow.appendHistory(txt);
			break;
		}
	}
	
	
			
	
}
