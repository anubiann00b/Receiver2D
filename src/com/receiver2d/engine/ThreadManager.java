package com.receiver2d.engine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Is responsible for threading all of the game's processes.
 */
public class ThreadManager {
	public ExecutorService	threadPool;

	/**
	 * Creates a new ThreadManager, and configures the threadPool.
	 */
	public ThreadManager() {
		// using cached thread pools because we will be queueing small,
		// inexpensive operations
		threadPool = Executors.newCachedThreadPool();
	}

	/**
	 * Sets a task to be queued in the game's thread pool.
	 * 
	 * @param task
	 *            A method.
	 */
	public void queueTask(Runnable task) {
		threadPool.execute(task);
	}
}
