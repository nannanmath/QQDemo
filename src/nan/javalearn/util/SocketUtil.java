package nan.javalearn.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import nan.javalearn.common.Message;

public class SocketUtil {
	/**
	 * 
	 * @param socket intput socket.
	 * @return A String combined by IP address and port.
	 */
	public static String getAddr(Socket socket){
		return socket.getInetAddress().getHostName() + ":" + socket.getPort();
	}
	
	/**
	 * Read message type from the first byte of stream.
	 * @param is input stream.
	 * @return Convert the byte to <code>int</code> and return.
	 * 		   <code>0</code> means list; <code>1</code> means content.
	 * 	       If read false will return <code>-1</code>;
	 */
	public static int readMsgType(InputStream is){
		try {
			return is.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Read the length of the message.
	 * @param is Input stream.
	 * @return The length of message. If faild return <code>-1</code>.
	 */
	public static int readMsgLength(InputStream is){
		try {
			byte[] bytes = new byte[4];
			is.read(bytes);
			
			int s0 = bytes[0] << 24;
			int s1 = (bytes[1] & 0xFF) << 16;
			int s2 = (bytes[2] & 0xFF) << 8;
			int s3 = bytes[3] & 0xFF;
			
			return s0 | s1 | s2 | s3;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Read message content from <code>Inputstream</code>.
	 * @param is Inputstream source.
	 * @param length The length of bytes need to read.
	 * @return The bytes read from Inputstream.
	 */
	public static byte[] readMsgContent(InputStream is, int length){
		try {
			byte[] buf = new byte[length];
			is.read(buf);
			return buf;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Read message from bytes.
	 * @param bytes Byte array source.
	 * @return Message String.
	 */
	public static String readMsgContent(byte[] bytes){
		try {
			String message = new String(bytes);
			return message;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Read message from bytes as specified charset.
	 * @param bytes Byte array source.
	 * @param charSet Specified charset.
	 * @return Message String.
	 */
	public static String readMsgContent(byte[] bytes, String charSet){
		try {
			String message = new String(bytes, charSet);
			return message;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Read friend list from bytes by deserialization.
	 * @param bytes Bytes need to be deserialized.
	 * @param length The length of bytes.
	 * @return String list.
	 */
	public static List<String> readFriends(byte[] bytes){
		try {
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
			List<String> friends = (List<String>)ois.readObject();
			ois.close();
			return friends;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String sendMessage(OutputStream os, Message msg){
		try {
			os.write(msg.getMessagePack());
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Convert a <code>int</code> to <code>byte[]</code> as Big Endian.
	 */
	public static byte[] int2byteArray(int i){
		byte[] bytes = new byte[4];
		bytes[0] = (byte)(i >> 24);
		bytes[1] = (byte)(i >> 16);
		bytes[2] = (byte)(i >> 8);
		bytes[3] = (byte)(i);
		
		return bytes;
	}
}
