package nan.javalearn.server;

import nan.javalearn.common.Message;

public class ServerPushTalkMessage extends Message {

	public ServerPushTalkMessage(String txt) {
		this.setType(Message.MESSAGE_TYPE_SERVER_TALK);
		byte[] bytes = txt.getBytes();
		this.setLength(bytes.length);
		this.setContent(bytes);
	}

	public ServerPushTalkMessage(byte[] pack) {
		pack[0] = (byte)Message.MESSAGE_TYPE_SERVER_TALK;
		this.setPack(pack);
	}

	public byte[] getMessagePack() {
		if(this.getPack() != null){
			return this.getPack();
		} else {
			return super.getMessagePack();
		}
	}
	
	
	
}
