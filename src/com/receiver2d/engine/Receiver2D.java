package com.receiver2d.engine;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.lwjgl.opengl.Display;

import com.receiver2d.engine.Console.LogLevel;

/**
 * This is the main class, where the engine can be started and managed.
 */
public class Receiver2D {
	// program values
	/**
	 * Whether or not the program is running. When false, all currently-running threads will automatically close.
	 */
	public static boolean running = true;
	/**
	 * Should debug messages be printed?
	 */
	public static boolean DEBUG_MODE = false;
	/**
	 * Global program start time
	 */
	public static final long START_TIME = System.nanoTime();
	// program values

	// engine values
	public static final int RENDER_FPS_CAP = 60;
	public static ThreadManager threads;

	/**
	 * The list of currently-loaded worlds in the engine.
	 */
	private static ArrayList<World> worlds = new ArrayList<World>();
	public static Thread[] threadList = new Thread[2];
	private static World loadedWorld;

	// engine values

	/**
	 * Starts the game engine.
	 */
	public static void start() {
		Console.log("Starting Receiver2D", null, Console.LogLevel.INFO);

		if (DEBUG_MODE) {
//			Console.debug("Setting org.lwjgl.util.Debug to "
//					+ System.setProperty("org.lwjgl.util.Debug", "true"));
			Console.level = Console.LogLevel.DEBUG;
		}

		threads = new ThreadManager(); // create the thread manager and pool

		/*
		 * This is our logic thread. It deals with all things pertaining to the
		 * calculation of non-essential game things (non-essential meaning that
		 * the engine is not concerned with it as much as it is with rendering,
		 * physics, audio, etc).
		 */
		threadList[0] = new Thread(new Runnable() {
			@Override
			public void run() {
				Console.log("Logic thread is running...", null, LogLevel.DEBUG);
			}
		});

		/*
		 * This is our physics thread. It deals with all updates pertaining to
		 * the _dynamic_ and interactive rendering of entities that have a
		 * Rigidbody component attached to them.
		 */
		threadList[1] = new Thread(new Runnable() {
			@Override
			public void run() {
				Console.log("Physics thread is running...", null,
						LogLevel.DEBUG);
				// TODO: while (Physics.update());
			}
		});

		// TODO: audio thread

		for (Thread t : threadList)
			threads.queueTask(t);
		
		Console.log("Receiver2D started");

		DisplayHandler.init("Test Game", false, 1280, 720);
	}

	/**
	 * Stops the game engine.
	 */
	public static void stop() {
		threads.threadPool.shutdown();
		try {
			threads.threadPool.awaitTermination(777, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			Console.error("Could not terminate threads.", e);
		}
		Display.destroy();
		Console.log("Receiver2D ended.");
		System.exit(0);
	}
	
	/**
	 * Loads a world into the world queue.
	 * @param world
	 */
	public static boolean loadWorld(World world) {
		// TODO: extensive check for world compatibility; update
		return worlds.add(loadedWorld = world);
	}
	
	/**
	 * Gets the currently-loaded world.
	 * @return
	 */
	public static World getLoadedWorld() {
		return loadedWorld;
	}
}
