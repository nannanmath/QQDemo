package nan.javalearn.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import nan.javalearn.common.Message;
import nan.javalearn.common.MessageFactory;
import nan.javalearn.util.SocketUtil;

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
		while(true) {
			Message msg = MessageFactory.transformPack(is);
			int type = msg.getType();
			switch(type){
			case Message.MESSAGE_TYPE_SERVER_PUSH_FRIENDS:
				pushMessageToAll(msg);
				break;
			case Message.MESSAGE_TYPE_SERVER_TALK:
				pushMessageToAll(msg);
				break;
			}
		}
	}

	/**
	 * Send message to each client who connects to server by threads.
	 * @param msg
	 */
	private void pushMessageToAll(final Message msg) {
		for(final Socket s : MessageServer.sockets){
			new Thread(){
			public void run() {
				try {
					SocketUtil.sendMessage(s.getOutputStream(), msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			}.start();
		}
		
	}

	
	
	
}
