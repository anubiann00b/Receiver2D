package com.receiver2d.engine;

import java.util.ArrayList;

/**
 * This class is used for logging and debug information
 */
public class Console {
	public enum LogLevel {
		UNDEFINED(0), // for completeness
		ERROR(1), // critical errors that affect the user
		INFO(2), // engine and game noncritical info
		DEBUG(3); // debugging information, specific and ONLY FOR DEBUGGING

		int val; // the severity level

		private LogLevel(int val) {
			this.val = val;
		}

		boolean shouldPrint(LogLevel cmp) {
			return this.val >= cmp.val; // example: if level is debug, we should print error msgs
		}
	}

	public static LogLevel level = LogLevel.ERROR;

	/**
	 * A Console message object, containing various types of information that may be useful for storing in the backlog.
	 */
	public static class Message {
		private Long time = null; // delta time in nanoseconds since game started
		private String msg = null;

		/**
		 * Creates a new message. Typically, in order to load this into the Console, one would immediately call Console.load(message) after.
		 * 
		 * @param message
		 *            the message to be stored
		 * @param deltaTime
		 *            the time since the start of the game
		 */
		public Message(String message, long time) {
			this.time = time;
			msg = message;
		}

		/**
		 * Gets the time at which the console message was invoked.
		 * 
		 * @return The number of nanoseconds since logging began.
		 */
		public long getTime() {
			return time;
		}

		/**
		 * Gets the original text of the console message.
		 * 
		 * @return The message text.
		 */
		public String getMessage() {
			return msg;
		}

		/**
		 * Gets the string representation of the message, which differs from getMessage() in that it also includes the timeStamp information in the returned text.
		 */
		@Override
		public String toString() {
			return toStringHumanReadable() + " - " + msg;
		}

		public String toStringHumanReadable() {
			int totalSeconds = (int) (time / 1000 / 1000 / 1000);
			int hours = totalSeconds / 60 / 60;
			int minutes = (totalSeconds - (hours * 60 * 60)) / 60;
			int seconds = totalSeconds % 60;

			return hours + ":" + (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds);
		}

		public String toStringNS() {
			return Long.toString(time);
		}
	}

	private static ArrayList<Message> messages = new ArrayList<Message>();
	/**
	 * Whether or not to print error stack traces.
	 */
	public static boolean PRINT_STACK_TRACES = false;

	/**
	 * Logs system information to the console with time and message.
	 * 
	 * @param message
	 *            A custom string containing the message to log.
	 * @param exception
	 *            A possible exception to include in the log.
	 */
	public static void log(String message, Exception exception, LogLevel type) {
		if (level.shouldPrint(type)) {
			long nanoDeltaTime = System.nanoTime() - Receiver2D.START_TIME;

			Message m = new Message("R2D " + type.toString() + ": " + message, nanoDeltaTime);
			messages.add(m);
			System.out.println(m.toString()); // TODO: handle this with text or gui mode option

			if (exception != null && PRINT_STACK_TRACES) exception.printStackTrace();
		}
	}

	@Deprecated
	// switch to method above
	public static void log(String message, Exception exception, String type) {
		LogLevel l = LogLevel.UNDEFINED;

		if (type.equalsIgnoreCase("ERROR")) l = LogLevel.ERROR;
		else if (type.equalsIgnoreCase("INFO")) l = LogLevel.INFO;
		else if (type.equalsIgnoreCase("DEBUG")) l = LogLevel.DEBUG;
		else l = LogLevel.UNDEFINED;
		log(message, exception, l);
	}

	// log() overrides
	/**
	 * Simply logs a generic message to the Console.
	 * 
	 * @param message
	 *            The message text.
	 */
	public static void log(String message) {
		log(message, null, LogLevel.INFO);
	}

	/**
	 * Logs an error to the console.
	 * 
	 * @param message
	 *            A custom message to include.
	 * @param exc
	 *            The exception to log to the console.
	 */
	public static void logError(String message, Exception exc) {
		log(message, exc, LogLevel.ERROR);
	}

	/**
	 * Gets all of the Console's currently-stored messages.
	 * 
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
