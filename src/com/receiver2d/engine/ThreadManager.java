package com.receiver2d.engine;

import java.util.concurrent.*;

/**
 * Is responsible for threading all of the game's processes.
 */
public class ThreadManager {
	public ExecutorService threadPool;
	public CyclicBarrier barrier;
	private int numThreads = 4;

	/**
	 * Creates a new ThreadManager, and configures the threadPool.
	 */
	public ThreadManager() {
		threadPool = Executors.newFixedThreadPool(numThreads);
		barrier = new CyclicBarrier(numThreads, new Runnable() {
			@Override
			public void run() {
				threadPool.shutdown();
			}
		});
	}

	/**
	 * Give a task for the thread pool to queue for execution
	 *
	 * @param task the runnable to execute
	 */
	public void queueTask(Runnable task) {
		threadPool.submit(task);
	}

	/**
	 * Give a task for the thread pool to queue for execution with the ability to get back data
	 *
	 * @param task the callable to execute
	 * @return a future object with the data being returned
	 */
	public Future<?> queueTask(Callable<?> task) {
		return threadPool.submit(task);
	}
}