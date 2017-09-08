package nan.javalearn.common;

import java.io.InputStream;

import nan.javalearn.server.MessageServer;
import nan.javalearn.server.ServerPushFriendsMessage;
import nan.javalearn.server.ServerPushTalkMessage;
import nan.javalearn.util.SocketUtil;

public class MessageFactory {
	/**
	 * Transform pack from client to server. This method is for Server ONLY.
	 * @param is Pack stream from client.
	 * @return Message transformed from client pack stream.
	 */
	public static Message transformPack(InputStream is) {
		Message msg = null;
		int type = SocketUtil.readMsgType(is);
		switch(type) {
			// This is for server, generate Server Talk Msg.
			case Message.MESSAGE_TYPE_CLIENT_TALK:
				int length = SocketUtil.readMsgLength(is);
				byte[] content = SocketUtil.readMsgContent(is, length);
				msg = new ServerPushTalkMessage(length, content);
				break;
			// This is for server, generate Server Push Friends Msg.
			case Message.MESSAGE_TYPE_CLIENT_REQUEST_FRIENDS:
				msg = new ServerPushFriendsMessage(MessageServer.friends);
				break;
		}
		return msg;
	}
	
	/**
	 * Parse pack stream from Server to Message. This method is for Client ONLY.
	 * @param is Pack stream from server.
	 * @return Message parsed from server pack stream.
	 */
	public static Message parsePack(InputStream is) {
		Message msg = null;
		
		int type = SocketUtil.readMsgType(is);
		int length = SocketUtil.readMsgLength(is);
		byte[] content = SocketUtil.readMsgContent(is, length);
		
		switch(type) {
			// This is for client, generate friends list as an object.
			case Message.MESSAGE_TYPE_SERVER_PUSH_FRIENDS:
				msg = new ServerPushFriendsMessage(content);
				break;
			// This is for client, 
			case Message.MESSAGE_TYPE_SERVER_TALK:
				msg = new ServerPushTalkMessage(type, content);
				break;
		}
		return msg;
	}
}
