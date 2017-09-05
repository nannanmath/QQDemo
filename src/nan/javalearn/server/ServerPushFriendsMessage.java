package nan.javalearn.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import nan.javalearn.common.Message;

public class ServerPushFriendsMessage extends Message {

	public ServerPushFriendsMessage(List<String> friends) {
		this.setType(Message.MESSAGE_TYPE_SERVER_PUSH_FRIENDS);
		this.setContent(serializeFriends(friends));
	}
	
	private byte[] serializeFriends(List<String> friends) {
		ByteArrayOutputStream bao;
		try {
			bao = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bao);
			oos.writeObject(friends);
			oos.close();
			bao.close();
			
			return bao.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
