package com.receiver2d.engine;

/**
 * This class is used for logging and debug information
 */

public class Console {
	public static void log(String message) {
		long nanoDeltaTime = System.nanoTime() - Receiver2D.startTime;
		int totalSeconds = (int) (nanoDeltaTime / 1000 / 1000 / 1000);
		int minutes = totalSeconds / 60;
		int seconds = totalSeconds % 60;
		
		System.out.println(minutes + ":" + (seconds < 10 ? "0":"") + seconds + " - " + message);
	}
}
