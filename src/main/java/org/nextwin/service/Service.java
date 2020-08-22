package org.nextwin.service;

import org.nextwin.net.NetworkManager;

public abstract class Service {
	protected NetworkManager networkManager;
	
	protected Service() {
		networkManager = NetworkManager.getInstance();
	}
	
	/**
	 * Execute appropriate task.
	 */
	public abstract void execute();

}
