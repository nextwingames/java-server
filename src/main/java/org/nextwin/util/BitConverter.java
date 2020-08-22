package org.nextwin.util;

public class BitConverter {
	
	/**
	 * Deserialize to integer.
	 * @param bytes
	 * @return Integer
	 */
	public static int bytesToInt(byte[] bytes) {
		return ((((int)bytes[3] & 0xff) << 24) | (((int)bytes[2] & 0xff) << 16) |
				(((int)bytes[1] & 0xff) << 8) | (((int)bytes[0] & 0xff)));
	}
	
	/**
	 * Serialize integer.
	 * @param value
	 * @return Byte array
	 */
	public static byte[] intToBytes(int value) {
		byte[] bytes=new byte[4];
        bytes[0] = (byte)((value & 0xFF000000) >> 24);
        bytes[1] = (byte)((value & 0x00FF0000) >> 16);
        bytes[2] = (byte)((value & 0x0000FF00) >> 8);
        bytes[3] = (byte)(value & 0x000000FF);

        return bytes;
	}

}
