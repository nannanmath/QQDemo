package nan.javalearn.client;

public class StartClient {
	public static void main(String[] args) {
		QQWindow qqWindow = new QQWindow();
		Comm comm = Comm.getInstance(qqWindow);
	}
}
