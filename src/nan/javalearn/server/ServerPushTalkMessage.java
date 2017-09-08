package nan.javalearn.server;

import nan.javalearn.common.Message;

public class ServerPushTalkMessage extends Message {

	public ServerPushTalkMessage(String txt) {
		this.setType(Message.MESSAGE_TYPE_SERVER_TALK);
		byte[] bytes = txt.getBytes();
		this.setContent(bytes);
	}

	public ServerPushTalkMessage(byte[] pack) {
		pack[0] = (byte)Message.MESSAGE_TYPE_SERVER_TALK;
		this.setPack(pack);
	}
	
	public ServerPushTalkMessage(int type, byte[] bytes) {
		this.setType(Message.MESSAGE_TYPE_SERVER_TALK);
		this.setContent(bytes);
	}

	public byte[] getMessagePack() {
		if(this.getPack() != null){
			return this.getPack();
		} else {
			return super.getMessagePack();
		}
	}

	public Object getData() {
		String txt = new String(getContent());
		return txt;
	}
}
