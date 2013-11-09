package com.receiver2d.engine;

/**
 * This class is used for logging and debug information
 */
public class Console {
	private static long logStart = 0;
	
	/**
	 * If set to true, all instances of Console.debug() will be logged.
	 */
	public static boolean DEBUG = false;
	
	/**
	 * Whether or not to print error stack traces.
	 */
	public static boolean PRINT_STACK_TRACE = false;
	
	/**
	 * Sets the time at which the Console will assume a recording of events.
	 * This property is immutable if it has already been set by Receiver2D.
	 * 
	 * @param time The time, in nanoseconds, since the engine has started.
	 */
	public static void setStartTime(long time) {
		logStart = logStart == 0 ? time : logStart;
	}

	/**
	 * Logs system information to the console with time and message.
	 *
	 * @param message   A custom string containing the message to log.
	 * @param exception A possible exception to include in the log.
	 */
	public static void log(String message, Exception exception, String type) {
		long nanoDeltaTime = System.nanoTime() - logStart;
		int totalSeconds = (int) (nanoDeltaTime / 1000 / 1000 / 1000);
		int minutes = totalSeconds / 60;
		int seconds = totalSeconds % 60;

		System.out.println(minutes + ":" + (seconds < 10 ? "0" : "") + seconds
				+ " - " + (type != null ? type.toUpperCase() + ": " : "")
				+ message);
		
		if (exception != null && PRINT_STACK_TRACE)
			exception.printStackTrace();
	}

	// log() overrides
	public static void log(String message) {
		log(message, null, null);
	}

	/**
	 * Logs a debug message to the console.
	 * 
	 * @param message A description of events.
	 */
	public static void debug(String message) {
		if (DEBUG)
			log(message, null, "debug");
	}

	/**
	 * Logs an error to the console.
	 *
	 * @param message A custom message to include.
	 * @param exc     The exception to log to the console.
	 */
	public static void logError(String message, Exception exc) {
		log(message, exc, "error");
	}
}
