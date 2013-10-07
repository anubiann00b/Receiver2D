package com.receiver2d.engine;

/**
 * This class is used for logging and debug information
 */

public class Console {
	public static void log(String message, Exception exception) {
		long nanoDeltaTime = System.nanoTime() - Receiver2D.startTime;
		int totalSeconds = (int) (nanoDeltaTime / 1000 / 1000 / 1000);
		int minutes = totalSeconds / 60;
		int seconds = totalSeconds % 60;
		
		if (exception != null)
			exception.printStackTrace();
		System.out.println(minutes+":"+(seconds < 10 ? "0":"")+seconds+" - "+ 
			(exception != null ? exception.getClass()+" " : "") + message);
	}
	// log() overrides
		public static void log(String message) {
			log(message, null);
		}
}
