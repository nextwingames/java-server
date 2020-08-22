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
	public static byte[] objectToBytes(Object object) throws JsonProcessingException {
		return objectMapper.writeValueAsBytes(object);
	}
	
	/**
	 * Serialize header, set length of bytes array as 26.
	 * @param header
	 * @return
	 * @throws JsonProcessingException
	 */
	public static byte[] objectToBytes(Header header) throws JsonProcessingException {
		String json = objectMapper.writeValueAsString(header);
		
		if(header.getMsgType() < 10)
			json += ' ';
		if(header.getLength() < 10)
			json += ' ';
		
		return json.getBytes();
	}
	
	/**
	 * Deserialize data.
	 * @param bytes
	 * @param classType
	 * @return Object
	 * @throws IOException
	 */
	public static Object bytesToObject(byte[] bytes, Class<?> classType) throws IOException {
		String json = new String(bytes);
		return objectMapper.readValue(json, classType);
	}

}
