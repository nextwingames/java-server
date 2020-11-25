package org.nextwin.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import org.nextwin.net.NetworkManager;
import org.nextwin.thread.ServerThread;
import org.nextwin.util.Logger;

public abstract class Server {
	
	private final String TAG = "Server";
	private String ip;
	private int port;
	
	/**
	 * To execute server process, this method should be called.
	 * The server classes should inherit org.nextwin.server.Server class so they have same go() method. 
	 */
	public void go() {
		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			serverSocket = new ServerSocket();
			
			ip = getIp();
			port = getPort();
			Logger.log(TAG, ip + ":" + port);
			serverSocket.bind(new InetSocketAddress(ip, port));
			
			while(true) {
				socket = serverSocket.accept();
				ServerThread serverThread = createServerThread(socket);
				serverThread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(socket != null)
					socket.close();
				if(serverSocket != null)
					serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			onTerminate();
		}
	}
	
	/**
	 * Create appropriate server thread.
	 * @param socket
	 * @return serverThread
	 */
	protected abstract ServerThread createServerThread(Socket socket);
	
	/**
	 * Called when the server is terminated.
	 */
	protected abstract void onTerminate();
	
	/**
	 * Return port number of the server.
	 * @return port
	 */
	protected abstract int getPort();	
	
	/**
	 * Return IP address of the server.
	 * @return
	 */
	protected abstract String getIp();
	
}