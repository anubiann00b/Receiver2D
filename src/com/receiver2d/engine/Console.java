package com.receiver2d.engine;

import java.util.ArrayList;

/**
 * This class is used for logging and debug information
 */
public class Console {
	private static long logStart = 0;
	
	/**
	 * A Console message object, containing various types of information that 
	 * may be useful for storing in the backlog.
	 */
	public static class Message {
		private long time = 0;
		private String msg = null;
		private String timeStamp = null;
		
		/**
		 * Creates a new message. Typically, in order to load this into the
		 * Console, one would immediately call Console.load(message) after.
		 * @param message The text of the message.
		 * @param time The time (in nanoseconds) of the log.
		 */
		public Message(String message, long time) {
			this.time = time - logStart;
			msg = message;
			
			int totalSeconds = (int) (this.time / 1000 / 1000 / 1000);
			int hours = totalSeconds / 3600;
			int minutes = (totalSeconds / 60) % 60;
			int seconds = totalSeconds % 60;
			
			timeStamp = (hours < 10 ? "0" : "") + hours + ":"
					+ (minutes < 10 ? "0" : "") + minutes + ":"
					+ (seconds < 10 ? "0" : "") + seconds;
		}
		
		/**
		 * Gets the time at which the console message was invoked.
		 * @return The number of nanoseconds since logging began.
		 */
		public long getTime() {
			return time;
		}
		
		/**
		 * Gets the original text of the console message.
		 * @return The message text.
		 */
		public String getMessage() {
			return msg;
		}
		
		/**
		 * Gets the string representation of the message, which differs from
		 * getMessage() in that it also includes the timeStamp information in
		 * the returned text.
		 */
		public String toString() {
			return timeStamp + " - " + msg;
		}
	}
	private static ArrayList<Message> messages = new ArrayList<Message>();
	
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
	public synchronized static void log(String message, Exception exception,
			String type) {
		Message m = new Message((type != null ? type.toUpperCase() + ": " : "")
				+ message, System.nanoTime());
		messages.add(m);
		System.out.println(m.toString());

		if (exception != null && PRINT_STACK_TRACE)
			exception.printStackTrace();
	}

	// log() overrides
	/**
	 * Simply logs a generic message to the Console.
	 * @param message The message text.
	 */
	public synchronized static void log(String message) {
		log(message, null, null);
	}

	/**
	 * Logs a debug message to the console.
	 * 
	 * @param message A description of events.
	 */
	public synchronized static void debug(String message) {
		if (DEBUG)
			log(message, null, "debug");
	}

	/**
	 * Logs an error to the console.
	 *
	 * @param message A custom message to include.
	 * @param exc     The exception to log to the console.
	 */
	public synchronized static void logError(String message, Exception exc) {
		log(message, exc, "error");
	}
	
	/**
	 * Loads a new message into the Console's list, which will now be accessible
	 * for various purposes (debugging, logging in Editor, etc).
	 * @param message The message to load into the Console's backlog.
	 */
	public synchronized static void load(Message message) {
		messages.add(message);
	}
	
	/**
	 * Gets all of the Console's currently-stored messages.
	 * @return The list of messages.
	 */
	public static ArrayList<Message> getMessages() {
		return messages;
	}
	
	/**
	 * Clears all messages from the Console's backlog.
	 */
	public static void clear() {
		messages.clear();
	}
}
