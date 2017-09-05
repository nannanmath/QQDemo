package nan.javalearn.client;

import nan.javalearn.server.MessageServer;

public class App {
	public static void main(String[] args) {
		QQWindow qqWindow = new QQWindow();
		Comm comm = Comm.getInstance();
		comm.setQQWindow(qqWindow);
	}
}
