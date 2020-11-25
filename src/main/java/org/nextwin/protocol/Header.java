package org.nextwin.protocol;


public class Header {
	
	public static final int HEADER_LENGTH = 30;

	private int msgType;
	private int length;
	
	public Header() {}
	
	public Header(int msgType, int length) {
		this.msgType = msgType;
		this.length = length;
	}
	
	/**
	 * Return message type of packet.
	 * @return msgType
	 */
	public int getMsgType() {
		return msgType;
	}
	
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	
	/**
	 * Return length of data of received packet.
	 * @return
	 */
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
}