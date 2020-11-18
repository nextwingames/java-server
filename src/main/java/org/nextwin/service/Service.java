package org.nextwin.service;

import org.nextwin.net.NetworkManager;
import org.nextwin.protocol.Dto;
import org.nextwin.thread.ServerThread;

public abstract class Service {
	protected NetworkManager networkManager;
	
	public Service() {
		networkManager = ServerThread.networkManager;
	}
	
	/**
	 * Execute appropriate task.
	 */
	public abstract void execute();

}
