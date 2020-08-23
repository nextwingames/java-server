package org.nextwin.util;

import java.io.IOException;

import org.nextwin.protocol.Header;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonManager {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * Serialize objects.
	 * @param object
	 * @return Byte array
	 * @throws JsonProcessingException
	 */
	public static byte[] objectToBytes(Object object) {
		try {
			return objectMapper.writeValueAsBytes(object);			
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Serialize header, set length of bytes array as 26.
	 * @param header
	 * @return
	 * @throws JsonProcessingException
	 */
	public static byte[] objectToBytes(Header header) {
		try {
			String json = objectMapper.writeValueAsString(header);
			
			if(header.getMsgType() < 10)
				json += ' ';
			if(header.getLength() < 10)
				json += ' ';
			
			return json.getBytes();
		} catch (Exception e) {
			return null;
		}		
	}
	
	/**
	 * Deserialize data.
	 * @param bytes
	 * @param classType
	 * @return Object
	 * @throws IOException
	 */
	public static Object bytesToObject(byte[] bytes, Class<?> classType) {
		String json = new String(bytes);
		try {
			return objectMapper.readValue(json, classType);			
		} catch (Exception e) {
			return null;
		}
	}

}
