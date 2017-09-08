package nan.javalearn.client;

import nan.javalearn.common.Message;

public class ClientRequestFriendsMessage extends Message {

	public ClientRequestFriendsMessage() {
		this.setType(Message.MESSAGE_TYPE_CLIENT_REQUEST_FRIENDS);
	}

	public byte[] getMessagePack() {
		byte[] bytes = new byte[1];
		bytes[0] = (byte)this.getType();
		return bytes;
	}

	// No need.
	public Object getData() {
		return null;
	}
	
	
}
