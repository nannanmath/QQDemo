package nan.javalearn.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import nan.javalearn.util.SocketUtil;

public class SenderThread extends Thread {
	private Socket socket = null;
	private OutputStream os = null;
	private String txt = null;
	
	public SenderThread(Socket socket){
		try {
			this.socket = socket;
			this.os = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public void run() {
		while(true){
			if(txt != null){
				SocketUtil.sendMessage(os, txt);
				txt = "";	
			}
		}
	}
	
}
