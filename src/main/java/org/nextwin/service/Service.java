package org.nextwin.service;

import org.nextwin.net.NetworkManager;
import org.nextwin.protocol.Dto;
import org.nextwin.thread.ServerThread;

public abstract class Service {
	protected NetworkManager networkManager;
	protected Dto dto;
	
	public Service(Dto dto) {
		networkManager = ServerThread.networkManager;
		this.dto = dto;
	}
	
	/**
	 * Execute appropriate task.
	 */
	public abstract void execute();

}
