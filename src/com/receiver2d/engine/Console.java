package com.receiver2d.engine;

import java.util.ArrayList;

/**
 * This class is used for logging and debugging information.
 */
public class Console {
	/**
	 * Involves specificity for messages, allowing them to be grouped
	 * accordingly, in addition to introducing special behavior for the Console.
	 */
	public enum LLevel {
		/**
		 * Any other miscellaneous message that does not fit a category.
		 */
		UNDEFINED(0),
		
		/**
		 * Critical errors that affect the user
		 */
		ERROR(1),
		
		/**
		 * Engine and game non-critical info
		 */
		INFO(2),
		
		/**
		 * Debugging information, specific and ONLY FOR DEBUGGING
		 */
		DEBUG(3);

		public int val; // the severity level

		private LLevel(int val) {
			this.val = val;
		}

		/**
		 * Determines if we should print.
		 * @param cmp The log level.
		 * @return
		 */
		public boolean shouldPrint(LLevel cmp) {
			// example: if level cmp is debug, we should print error msgs
			return this.val >= cmp.val;
		}
	}

	/**
	 * The current log level that the Console is on. Any messages that are
	 * above this log level will be ignored.
	 */
	public static LLevel level = LLevel.ERROR;

	/**
	 * A Console message object, containing various types of information that
	 * may be useful for storing in the backlog.
	 */
	public static class Message {
		private Long time = null; // delta time in nanoseconds since game started
		private String msg = null;

		/**
		 * Creates a new message. Typically, in order to load this into the
		 * Console, one would immediately call Console.load(message) after.
		 * @param message The text of the message.
		 * @param time The time (in nanoseconds) of the log.
		 */
		public Message(String message, long time) {
			this.time = time - Receiver2D.START_TIME;
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
		 * Gets the string representation of the message, which differs from
		 * getMessage() in that it also includes the timeStamp information in
		 * the returned text.
		 */
		@Override
		public String toString() {
			int totalSeconds = (int) (time / 1000 / 1000 / 1000);
			int hours = totalSeconds / 60 / 60;
			int minutes = (totalSeconds - (hours * 60 * 60)) / 60;
			int seconds = totalSeconds % 60;

			return hours + ":" + (minutes < 10 ? "0" + minutes : minutes) + ":"
					+ (seconds < 10 ? "0" + seconds : seconds) + " - " + msg;
		}

		/**
		 * Returns the string representation of the time (number of nanoseconds
		 * since the start of logging, in which this message was logged).
		 * @return A String representation of the time in nanoseconds.
		 */
		public String toStringNS() {
			return Long.toString(time);
		}
	}

	private static ArrayList<Message> messages = new ArrayList<Message>();

	/**
	 * Logs system information to the console with time and message.
	 * 
	 * @param message  	A custom string containing the message to log.
	 * @param exception	A possible exception to include in the log.
	 */
	public synchronized static void log(String message, Exception exception,
			LLevel type) {
		if (!level.shouldPrint(type)) return;

		Message m = new Message(type.toString() + ": " + message,
				System.nanoTime());
		
		messages.add(m);
		
		System.out.println(m.toString()); // TODO: handle this with text or gui
											// mode option
		if (exception != null && level.val >= 3) //debug mode or higher
			exception.printStackTrace();
	}

	// log() overrides
	/**
	 * Simply logs a generic message to the Console.
	 * @param message  A custom message to include.
	 */
	public synchronized static void log(String message) {
		log(message, null, LLevel.INFO);
	}
	
	/**
	 * Log a debug message to the Console.
	 * @param message A custom message to include.
	 */
	public synchronized static void debug(String message) {
		log(message, null, LLevel.DEBUG);
	}

	/**
	 * Logs an error to the Console.
	 * 
	 * @param message	A custom message to include.
	 * @param exc		The exception to log to the console.
	 */
	public synchronized static void error(String message, Exception exc) {
		log(message, exc, LLevel.ERROR);
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
