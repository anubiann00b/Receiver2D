/* Hi there! This is a _fork_ of Remote2D, hopefully designed
 * to address some of the inefficiencies of the original version
 * and thus to serve as an improvement. It is currently a proof-
 * of-concept, and is not intended for commercial use.
 * -------------------------------------------------------------
 * Remote2D is the basic class that initializes all of the other
 * components of the editor and the engine.
 * In Remote2D, the editor runs separately and pulls in 
 * functionality from the engine classes, like Physics, Audio,
 * Renderer, et cetera. Both groups, the engine and the editor, 
 * exist in parallel to each other and are not the same. In
 * addition, the entity system is designed with respect to the
 * engine being responsible for functionality, so that entities
 * are _not responsible for rendering themselves_, and that
 * everything can be neatly parallelized.
 * -------------------------------------------------------------
 * The original engine was created by Adrian Biagioli and can
 * be found at his GitHub repository:
 * - https://github.com/Flafla2/Remote2D-Engine
 * -------------------------------------------------------------
 * The source code for this engine can be found at the GitHub
 * repository:
 * - https://github.com/Prince781/Remote2D
 * 
 * With that, we hope that you may find our version to be 
 * useful.
 * - Ben and Princeton
 */
package com.remote.remote2d.engine;
import com.remote.remote2d.editor.Editor;
import com.remote.remote2d.engine.Console;

public class Remote2D {	
	//program values
	public static String programName = "Remote2D";
	public static long startTime = System.nanoTime();
	public static boolean running = true;
	//program values
	
	//engine values
	public static ThreadGroup threadList = new ThreadGroup("R2DThreadGroup");
	//engine values
	
	public static String getTimeSinceLaunch() {
		//formats ΔT according to minutes:seconds
		int seconds = (int) ((System.nanoTime() - startTime) / (1000 * 1000 * 100));
		return (seconds/60)+":"+(seconds % 60 < 10 ? "0" : "")+(seconds % 60);
	}
	
	
	
	//initialization function
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Console.log(programName+" started.");
		
		/* Below, we create new threads. These threads will only run as long
		 * as the program value "running" is true. As soon as "running" is
		 * false, all of the threads will exit on their own and Remote2D
		 * will subsequently quit.
		 */
		
		//Renderer thread
		Thread r2dRenderer = new Thread(threadList, "R2DRenderer") {
			
		};
		
		//GUI Editor thread
		Thread r2dEditor = new Thread(threadList, "R2DEditor") {
			@Override
			public void run() {
				Editor.run();
			}
		};
		
		Console.log(programName+" ended.");
	}
}
