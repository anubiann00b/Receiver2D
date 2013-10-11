package com.receiver2d.engine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {
	public ExecutorService threadPool;
	
	public ThreadManager() {
		threadPool = Executors.newCachedThreadPool();
	}
	
	public void queueTask(Runnable task){
		threadPool.execute(task);
	}
}
