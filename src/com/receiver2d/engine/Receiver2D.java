package com.receiver2d.engine;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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

	/**
	 * The list of currently-loaded worlds in the engine.
	 */
	public static ArrayList<World> worlds = new ArrayList<World>();
	public static Thread[] threadList = new Thread[2];
	// engine values

	/**
	 * Starts the game engine.
	 */
	public static void start() {
		threads = new ThreadManager(); // create the thread manager and pool

		Console.setStartTime(startTime = System.nanoTime());
		Console.log("Receiver2D started.");
		
		/*
		 * This is our logic thread. It deals with all things pertaining to
		 * the calculation of non-essential game things (non-essential meaning
		 * that the engine is not concerned with it as much as it is with
		 * rendering, physics, audio, etc).
		 */
		threadList[0] = new Thread(new Runnable() {
			@Override
			public void run() {
				Console.debug("Logic thread is running...");
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
				// TODO: while (Physics.update());
			}
		});
		
		// TODO: audio thread
		
		for (Thread t : threadList) {
			threads.queueTask(t);
			t.start();
		}
		
		// this comes last
		Console.debug("Initialized rendering in main thread.");
		DisplayHandler.init("Test Game", false, 1280, 720);
	}

	/**
	 * Shuts down Remote2D.
	 */
	public static void stop() {
		try {
			threads.threadPool.awaitTermination(777, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			Console.logError("Could not terminate threads.", e);
		}
		
		threads.threadPool.shutdown();
		Display.destroy();
		Console.log("Receiver2D ended.");
		System.exit(0);
	}
}
