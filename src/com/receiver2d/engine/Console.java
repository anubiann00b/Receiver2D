package com.receiver2d.engine;

/**
 * This class is used for logging and debug information
 */
public class Console {
	private static long logStart = System.nanoTime();
	/**
	 * Logs system information to the console with time and message.
	 * 
	 * @param message
	 *            A custom string containing the message to log.
	 * @param exception
	 *            A possible exception to include in the log.
	 */
	public static void log(String message, Exception exception, String type) {
		long nanoDeltaTime = System.nanoTime() - logStart;
		int totalSeconds = (int) (nanoDeltaTime / 1000 / 1000 / 1000);
		int minutes = totalSeconds / 60;
		int seconds = totalSeconds % 60;

		if (exception != null)
			exception.printStackTrace();
		System.out.println(minutes
				+ ":"
				+ (seconds < 10 ? "0" : "")
				+ seconds
				+ " - "
				+ (type != null ? type.toUpperCase() + ": " : "")
				+ (exception != null ? exception.getClass() + " - "
						+ exception.getMessage() + " - " : "") + message);
	}
	// log() overrides
		public static void log(String message) {
			log(message, null, null);
		}
	/**
	 * Logs a debug message to the console.
	 * @param message A description of events.
	 */
	public static void debug(String message) {
		log(message, null, "debug");
	}
	/**
	 * Logs an error to the console.
	 * @param message A custom message to include.
	 * @param exc The exception to log to the console.
	 */
	public static void logError(String message, Exception exc) {
		log(message, exc, "error");
	}
}
