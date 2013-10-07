/**
 * This is the main class, where the engine can be started and managed.
 */

package com.receiver2d.engine;

public class Receiver2D {
	// program values
	public static final String programName = "Receiver2D";
	public static long startTime = 0;
	public static boolean running = true;
	// program values

	// engine values
	public static ThreadGroup threadList;
	// engine values

	// start the engine
	public static void StartReciever2D() {
		startTime = System.nanoTime();
		Console.log(programName + " started.");

		/**
		 * Below, we create new threads. These threads will only run as long as
		 * the program value "running" is true. As soon as "running" is false,
		 * all of the threads will exit on their own and Remote2D will
		 * subsequently quit.
		 */

		threadList = new ThreadGroup("R2DThreadGroup");

		// Renderer thread (everything graphics-related)
		Thread r2dRenderer = new Thread(threadList, new RenderThread(),
				"R2DRenderer");
		
		// Game logic updater thread (physics and other game updates)
		Thread r2dUpdater = new Thread(threadList, new UpdateThread(),
				"R2DUpdater");

		r2dUpdater.start();
		r2dRenderer.start();

		Console.log(programName + " ended.");
	}
}
