package org.nextwin.thread;

import java.io.IOException;
import java.net.Socket;

import org.nextwin.net.NetworkManager;
import org.nextwin.protocol.Header;

public abstract class ServerThread extends Thread {
	
	protected Socket socket;
	protected NetworkManager networkManager;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
		networkManager = NetworkManager.getInstance();
	}
	
	/**
	 * Called after thread.start().
	 */
	@Override
	public void run() {
		try {
			work();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(socket != null)
					socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Do common work of distinct threads.
	 * @throws IOException
	 */
	private void work() throws IOException {
		try {
			onEnterServer();
			networkManager.setSocket(socket);
			
			while(networkManager.isConnected()) {
				Header header = networkManager.receive();
				byte[] data = networkManager.receive(header.getLength());
				service(header.getMsgType(), data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			onCloseServer();
			networkManager.close();
		}
	}
	
	/**
	 * Do service in order to message type of received packet.
	 * @param msgType
	 * @param data
	 * @throws IOException
	 */
	protected abstract void service(int msgType, byte[] data) throws IOException;
	
	/**
	 * Called when clients enter server.
	 */
	protected abstract void onEnterServer();
	
	/**
	 * Called when server thread closing. 
	 */
	protected abstract void onCloseServer();
	
}
