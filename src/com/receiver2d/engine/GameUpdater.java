package com.receiver2d.engine;

/**
 * The main thread responsible for updating all of the logic in-game. Such logic
 * includes physics, collision detection, and tick()
 */
public class GameUpdater implements Runnable {

	private long lastTickTime = 0L;

	/**
	 * Gets the last time since an instance of tick() was performed.
	 * 
	 * @return A time in nanoseconds.
	 */
	public long getLastTickTime() {
		return lastTickTime;
	}
	
	private float interpolation;
	
	/**
	 * Manually set the current interpolation coefficient of the graphics
	 * update. This ensures that the graphics update looks as "smooth" as
	 * possible with the fixed-time tick() instance. By default, an
	 * interpolation value is calculated by the engine automatically.
	 * 
	 * @param interp An interpolation value.
	 */
	public void setInterpolation(float interp) {
		interpolation = interp;
	}

	/**
	 * The number of allowable ticks per second, in hertz.
	 */
	public int tickHertz = 100;

	@Override
	public void run() {
		tick();
	}
	
	/**
	 * Updates game logic instantaneously. Will automatically do nothing if the
	 * time since the last tick is less than an arbitrary minimum. The tick()
	 * function operates on a fixed-time loop.
	 */
	public void tick() {
		long now = System.nanoTime();
		float tickTime = 1000/tickHertz; //in milliseconds
		float deltaTick = (float) ((now - lastTickTime)/1000000); //in milliseconds
		
		// compares in milliseconds
		if (deltaTick < tickTime)
			return; // we cannot tick() yet as we are ahead of our schedule
		
		//yield thread until our time has passed
		//if (deltaTick ) {
			
		//}
		//lastTickTime = now;
	}
	
	/**
	 * Performs a logic-based update of the game engine. For example, a ball 
	 * @param interp
	 */
	public void logicUpdate(float interp) {
		
	}
	// logicUpdate() overrides
		public void logicUpdate() {
			logicUpdate(1.0f);
		}
}