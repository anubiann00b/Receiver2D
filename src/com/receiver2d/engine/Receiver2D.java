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
	public static Renderer renderer;
	public static GameUpdater gameUpdater;
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
		renderer = new Renderer();
		gameUpdater = new GameUpdater();

		// Renderer thread (everything graphics-related)
		Thread r2dRenderer = new Thread(threadList, renderer, "R2DRenderer");
		
		// Game logic updater thread (physics and other game updates)
		Thread r2dUpdater = new Thread(threadList, gameUpdater, "R2DGameUpdater");

		threadList.setMaxPriority(Thread.MAX_PRIORITY);
		r2dUpdater.start();
		r2dRenderer.start();

		Console.log(programName + " ended.");
	}
}
