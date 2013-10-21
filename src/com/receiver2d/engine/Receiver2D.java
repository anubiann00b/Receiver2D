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
    public static Thread loop;
	// engine values

	/**
	 * Starts the game engine.
	 */
	public static void start() {
		startTime = System.nanoTime();
		Console.log("Receiver2D started.");

        DisplayHandler.init("Test Game", false, 800, 600); // init openGL stuff
        threads = new ThreadManager(); // create new thread pool

        loop = new Thread(new Runnable() {
            @Override
            public void run() {
                gameLoop();
            }
        });
        loop.start();
	}

    public static void gameLoop(){
        System.out.println("looping");
    }

	/**
	 * Shuts down Remote2D.
	 */
	public static void stop() {
        try {
            loop.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threads.threadPool.shutdown();
		Display.destroy();
		Console.log("Receiver2D ended.");
        System.exit(0);
	}
}
