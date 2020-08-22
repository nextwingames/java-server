package org.nextwin.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.nextwin.protocol.Header;
import org.nextwin.util.JsonManager;

import com.fasterxml.jackson.core.JsonProcessingException;

public class NetworkManager {

	public static final int MAIN_PORT = 8899;
	public static final int LOCAL_PORT = 8890;
	public static int GAME_PORT = 9000;
	
	private static NetworkManager instance;
	
	private Socket socket;
	private OutputStream sender;
	private InputStream receiver;
		
	private NetworkManager() {}
	
	/**
	 * Return singleton instance of NetworkManager.
	 * @return Singleton instance of NetworkManager
	 */
	public static NetworkManager getInstance() {
		if(instance == null)
			instance = new NetworkManager();
		return instance;
	}
	
	/**
	 * Set socket and create OutputStream, InputStream.
	 * @param socket
	 * @throws IOException
	 */
	public void setSocket(Socket socket) throws IOException {
		this.socket = socket;
		sender = socket.getOutputStream();
		receiver = socket.getInputStream();
	}
	
	/**
	 * Check socket is connected.
	 * @return socket.isConnected() && !socket.isClosed()
	 */
	public boolean isConnected() {
		return (socket.isConnected() && !socket.isClosed());
	}
	
	/**
	 * Receive data except header.
	 * @return received data, null when fail
	 * @throws IOException
	 */
	public byte[] receive(int length) throws IOException {
		if(socket.isClosed())
			return null;
		
		byte[] data = new byte[length];
		if(receiver.read(data, 0, length) == -1)
			return null;
		
		return data;
	}
	
	/**
	 * Receive header of packets.
	 * @return received header, null when fail
	 * @throws IOException
	 */
	public Header receive() throws IOException{
		if(socket.isClosed())
			return null;
		
		byte[] head = new byte[Header.HEADER_LENGTH];
		if(receiver.read(head, 0, Header.HEADER_LENGTH) == -1)
			return null;

		Header header = (Header)JsonManager.bytesToObject(head, Header.class);
		return header;
	}
	
	/**
	 * Create appropriate header of data and combine them. Finally send one.
	 * @param msgType
	 * @param object
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public void send(int msgType, Object object) throws IOException, JsonProcessingException {
		byte[] data = JsonManager.objectToBytes(object);
		
		Header header = new Header(msgType, data.length);
		byte[] head = JsonManager.objectToBytes(header);
		
		// 최종 전송할 패킷(헤더 + 데이터) 직렬화
		byte[] packet = new byte[head.length + data.length];
		System.arraycopy(head, 0, packet, 0, head.length);
		System.arraycopy(data, 0, packet, head.length, data.length);
		
		sender.write(packet);
	}
	
	/**
	 * Close socket, inputStream and outputStream.
	 */
	public void close() {
		try {
			if(socket != null)
				socket.close();
			if(sender != null)
				sender.close();
			if(receiver != null)
				receiver.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void checkSocketState() {
		System.out.println("socket.isConnected " + socket.isConnected());
		System.out.println("socket.isClosed " + socket.isClosed());
	}
	
}