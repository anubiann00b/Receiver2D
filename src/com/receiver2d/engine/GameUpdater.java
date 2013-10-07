package com.receiver2d.engine;

/**
 * The main thread responsible for updating all of the logic in-game. Such logic
 * includes physics, collision detection, and tick()
 */
public class GameUpdater implements Runnable {

	private long lastTickTime = Receiver2D.startTime;

	/**
	 * Gets the last time since an instance of tick() was performed.
	 * 
	 * @return A time in nanoseconds.
	 */
	public long getLastTickTime() {
		return lastTickTime;
	}

	/**
	 * The number of allowable ticks per second, in hertz.
	 */
	public int tickHertz = 100;

	/**
	 * Sets the game updater to start running in a threaded process.
	 */
	@Override
	public void run() {

	}

	/**
	 * Updates game logic instantaneously. Will automatically do nothing if the
	 * time since the last tick is less than an arbitrary minimum.
	 */
	public void tick() {
		long now = System.nanoTime();
		if ((float) ((now - lastTickTime) / 1000 / 1000) < (float) (1000 / tickHertz))
			return; // we cannot tick() yet as we are ahead of our schedule

		lastTickTime = now;
	}
}