package org.nextwin.service;

import org.nextwin.net.NetworkManager;
import org.nextwin.protocol.Packet;
import org.nextwin.thread.ServerThread;

public abstract class Service {
	protected NetworkManager networkManager;
	protected Packet packet;
	
	protected Service(Packet packet) {
		networkManager = ServerThread.networkManager;
		this.packet = packet;
	}
	
	/**
	 * Execute appropriate task.
	 */
	public abstract void execute();

}
