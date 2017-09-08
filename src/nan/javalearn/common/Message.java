package nan.javalearn.common;

import nan.javalearn.util.SocketUtil;

public abstract class Message {
	// Client push talk message to server.
	public static final int MESSAGE_TYPE_CLIENT_TALK = 0;
	// Client request for friends list.
	public static final int MESSAGE_TYPE_CLIENT_REQUEST_FRIENDS = 1;
	// Server push friends list to clients.
	public static final int MESSAGE_TYPE_SERVER_PUSH_FRIENDS = 2;
	// Server push client message to others.
	public static final int MESSAGE_TYPE_SERVER_TALK = 3;
	
	private int type;
	private int length; // It will be set by SetContent() automatically.
	private byte[] content = null;
	private byte[] pack = null; // Message pack.
	
	
	public byte[] getPack() {
		return pack;
	}


	public void setPack(byte[] pack) {
		this.pack = pack;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public int getLength() {
		return length;
	}

	public byte[] getContent() {
		return content;
	}


	public void setContent(byte[] content) {
		this.content = content;
		this.length = content.length; // Set length.
	}

	public byte[] getMessagePack(){
		pack  = new byte[1 + 4 + length];
		// Message type.
		pack[0] = (byte)type;
		// Message length.
		byte[] bytLen = SocketUtil.int2byteArray(length);
		System.arraycopy(bytLen, 0, pack , 1, 4);
		// Message body.
		System.arraycopy(content, 0, pack, 5, length);
		
		return pack ;
	}
	
	public abstract Object getData();
	
	
}
