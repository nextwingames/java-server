package org.nextwin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void log(String tag, String message) {
		Date now = new Date();
		String time = format.format(now);
		System.out.println(time + " [" + tag + "]  " + message);
	}
	
}
