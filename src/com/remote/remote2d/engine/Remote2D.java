/*
 * This is the main class, which should be a "control center" for the engine.
 */
package com.remote.remote2d.engine;

public class Remote2D {
	// program values
	public final static String programName = "Remote2D";
	public static long startTime;
	public static boolean running = true;
	// program values

	// engine values
	public static ThreadGroup threadList;
	// engine values

	public static long getTimeSinceLaunch() {
		return startTime - System.nanoTime();
	}

	// start the engine
	public static void StartRemote2D() {
		startTime = System.nanoTime();
		threadList = new ThreadGroup("R2DThreadGroup");
	}
}
