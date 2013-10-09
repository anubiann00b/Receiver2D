package com.receiver2d.engine;

/**
 * This is the main class, where the engine can be started and managed.
 */
public class Receiver2D {
	// program values
	public static final String programName = "Receiver2D";
	/**
	 * The initial time at which the program began running.
	 */
	public static long startTime = 0;
	/**
	 * Whether or not the program is running. When false, all currently-running
	 * threads will automatically close.
	 */
	public static boolean running = true;
	// program values

	// engine values
	/**
	 * A ThreadGroup containing all of the currently running threads which the
	 * game engine uses to perform updates to rendering and the world.
	 */
	public static ThreadGroup threadList;
	/**
	 * The central rendering component of the game engine. Rendering functions
	 * are abstracted to this one class.
	 */
	public static RenderThread renderer;
	/**
	 * The central logic component of the game engine, responsible for tick(),
	 * logic updates, physics updates, collision updates, and more.
	 */
	public static UpdateThread gameUpdater;

	// engine values

	/**
	 * Starts the game engine.
	 */
	public static void StartReciever2D() {
		startTime = System.nanoTime();
		Console.log(programName + " started.");

		/*
		 * Below, we create new threads. These threads will only run as long as
		 * the program value "running" is true. As soon as "running" is false,
		 * all of the threads will exit on their own and Remote2D will
		 * subsequently quit.
		 */

		threadList = new ThreadGroup("R2DThreadGroup");
		renderer = new RenderThread();
		gameUpdater = new UpdateThread();

		// Renderer thread (everything graphics-related)
		Thread r2dRenderer = new Thread(threadList, renderer, "R2DRenderer");

		// Game logic updater thread (physics and other game updates)
		Thread r2dUpdater = new Thread(threadList, gameUpdater,
				"R2DGameUpdater");

		threadList.setMaxPriority(Thread.MAX_PRIORITY);
		r2dUpdater.start();
		r2dRenderer.start();

		Console.log(programName + " ended.");
	}
}
