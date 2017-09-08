package nan.javalearn.client;

import nan.javalearn.common.Message;

public class ClientTalkMessage extends Message {
	
	public ClientTalkMessage(String txt) {
		this.setType(Message.MESSAGE_TYPE_CLIENT_TALK);
		byte[] bytes = txt.getBytes();
		this.setContent(bytes);
	}

	public Object getData() {
		return this.getContent();
	}

}

