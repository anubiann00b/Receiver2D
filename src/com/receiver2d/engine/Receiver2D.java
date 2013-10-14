package com.receiver2d.engine;

import org.lwjgl.opengl.Display;

/**
 * This is the main class, where the engine can be started and managed.
 */
public class Receiver2D {
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
	public static final int RENDER_FPS_CAP = 60;
	public static ThreadManager threads;

	// engine values

	/**
	 * Starts the game engine.
	 */
	public static void start() {
		startTime = System.nanoTime();
		Console.log("Receiver2D started.");
		init();

		// temp
		while (!Display.isCloseRequested())
			Display.update();

		Display.destroy(); // quit the display
	}

	/**
	 * Initializes the display handler. Is called automatically by start().
	 */
	public static void init() {
		DisplayHandler.init("Test Game", false, 1600, 900); // init openGL stuff
		threads = new ThreadManager(); // create new thread pool
	}

	/**
	 * Shuts down Remote2D.
	 */
	public static void stop() {
		threads.threadPool.shutdown();
		Display.destroy();
		Console.log("Receiver2D ended.");
	}
}
