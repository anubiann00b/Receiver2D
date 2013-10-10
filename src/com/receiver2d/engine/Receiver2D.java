package com.receiver2d.engine;

import org.lwjgl.opengl.Display;

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
	public static final int RENDER_FPS_CAP = 60;
	// engine values

	/**
	 * Starts the game engine.
	 */
	public static void StartReciever2D() {
		startTime = System.nanoTime();
		Console.log(programName + " started.");

		init();
		
		// temp
		while (!Display.isCloseRequested())
			Display.update();
		
		Display.destroy(); //quiz the display
		Console.log(programName + " ended.");
	}

	public static void init() {
		DisplayHandler.init(); // init openGL stuff

		Console.log(programName + " ended.");
	}
}
