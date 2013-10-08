package com.receiver2d.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

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

	// engine values

	/**
	 * Starts the game engine.
	 */
	public static void StartReciever2D() {
		startTime = System.nanoTime();
		Console.log(programName + " started.");
		
		init();

		Console.log(programName + " ended.");
	}

	public static void init() {
		try {
			Display.setDisplayMode(new DisplayMode(1024, 768));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		/*
		 * Below, we create new threads. These threads will only run as long as
		 * the program value "running" is true. As soon as "running" is false,
		 * all of the threads will exit on their own and Remote2D will
		 * subsequently quit.
		 */

		threadList = new ThreadGroup("R2DThreadGroup");

		// Renderer thread (everything graphics-related)
		Thread r2dRenderer = new Thread(threadList, new RenderThread(), "R2DRenderer");

		// Game logic updater thread (physics and other game updates)
		Thread r2dUpdater = new Thread(threadList, new UpdateThread(),
				"R2DGameUpdater");

		threadList.setMaxPriority(Thread.MAX_PRIORITY);
		r2dUpdater.start();
		r2dRenderer.start();
	}
}
