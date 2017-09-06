package nan.javalearn.common;

import java.io.InputStream;

import nan.javalearn.server.MessageServer;
import nan.javalearn.server.ServerPushFriendsMessage;
import nan.javalearn.server.ServerPushTalkMessage;
import nan.javalearn.util.SocketUtil;

public class MessageFactory {
	/**
	 * Transform pack from client to server. This method is for Server ONLY.
	 * @param is Stream from client.
	 * @return Message object transformed from client pack.
	 */
	public static Message transformPack(InputStream is) {
		Message msg = null;
		int type = SocketUtil.readMsgType(is);
		switch(type) {
			// This is for server, generate Server Talk Msg.
			case Message.MESSAGE_TYPE_CLIENT_TALK:
				int length = SocketUtil.readMsgLength(is);
				byte[] content = SocketUtil.readMsgContent(is, length);
				msg = new ServerPushTalkMessage(length, length, content);
				break;
			// This is for server, generate Server Push Friends Msg.
			case Message.MESSAGE_TYPE_CLIENT_REQUEST_FRIENDS:
				msg = new ServerPushFriendsMessage(MessageServer.friends);
				break;
		}
		return msg;
	}
	
	public static Object parsePack(InputStream is) {
		Object obj = null;
		
		int type = SocketUtil.readMsgType(is);
		int length = SocketUtil.readMsgLength(is);
		byte[] content = SocketUtil.readMsgContent(is, length);
		
		switch(type) {
			// This is for client, generate friends list as an object.
			case Message.MESSAGE_TYPE_SERVER_PUSH_FRIENDS:
				obj = SocketUtil.readFriends(content);
			// This is for client, 
			case Message.MESSAGE_TYPE_SERVER_TALK:
				obj = SocketUtil.readMsgContent(content);
				break;
		}
		return obj;
	}
}
