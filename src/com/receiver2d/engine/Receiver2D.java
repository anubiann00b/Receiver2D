/**
 * This is the main class, which should be a "control center" for the engine.
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

	// returns the time in nanoseconds since the program was launched
	public static long getTimeSinceLaunch() {
		return System.nanoTime() - startTime;
	}

	// for testing, not for release
	public static void main(String[] args){
		StartReciever2D();
	}
	
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
		// Renderer thread
		Thread r2dRenderer = new Thread(threadList, "R2DRenderer") {

		};

		// Collision detection thread
		Thread r2dCollisionDetection = new Thread(threadList,
				"R2DCollisionDetection") {

		};

		// Physics thread
		Thread r2dPhysics = new Thread(threadList, "R2DPhysics") {

		};

		Console.log(programName + " ended.");
	}
}
