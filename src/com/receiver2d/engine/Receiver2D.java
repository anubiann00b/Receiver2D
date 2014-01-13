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
	 * Whether or not the program is running. When false, all currently-running
	 * threads will automatically close.
	 */
	public static boolean running = true;
	/**
	 * Global program start time
	 */
	public static final long START_TIME = System.nanoTime();
	// program values

	// engine values
	/**
	 * The limit on frames per second for the engine to render at.
	 */
	public static final int RENDER_FPS_CAP = 60;
	public static ThreadManager threads;

	/**
	 * The list of worlds in the engine.
	 */
	private static ArrayList<World> worlds = new ArrayList<World>();
	
	/**
	 * The currently-loaded world in the engine.
	 */
	private static World loadedWorld;

	// end of engine values

	// start/stop methods
	/**
	 * Starts the game engine.
	 */
	public static void start() {
		Console.log("Starting Receiver2D", null, Console.LogLevel.INFO);

		if (Console.CURRENT_DEBUG_MODE.isTrue()) {
			if (Console.CURRENT_DEBUG_MODE == Console.DebugMode.DEBUG_R2D_AND_LWJGL)
				System.setProperty("org.lwjgl.util.Debug", "true");
			Console.CURRENT_LOG_LEVEL = Console.LogLevel.DEBUG;
		}

		threads = new ThreadManager(); // create the thread manager and pool
		Thread[] threadList = new Thread[2]; // create threads

		/*
		 * This is our logic thread. It deals with all things pertaining to the
		 * calculation of non-essential game things (non-essential meaning that
		 * the engine is not concerned with it as much as it is with rendering,
		 * physics, audio, etc).
		 */
		threadList[0] = new Thread(new Runnable() {
			@Override
			public void run() {
				Console.debug("Logic thread is running...");
				// TODO: update game logic here
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
				Console.debug("Physics thread is running...");
				while (Physics.update());
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
	// end of start/stop methods
	
	/**
	 * Loads a world into the world queue.
	 * @param world
	 */
	public static boolean loadWorld(World world) {
		// TODO: extensive check for world compatibility; update
		return worlds.add(worlds.size() == 0 ? loadedWorld = world : world);
	}
	
	/**
	 * Gets the currently-loaded world.
	 * @return
	 */
	public static World getLoadedWorld() {
		return loadedWorld;
	}
}
