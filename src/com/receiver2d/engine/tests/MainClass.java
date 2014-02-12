package com.receiver2d.engine.tests;

// mainclass - test
import com.receiver2d.engine.*;
import com.receiver2d.engine.io.*;
import com.receiver2d.engine.graphics.*;

import java.io.IOException;

public class MainClass {
	public static boolean resourceTest(String worldLocation) {
		World world = null;
		try {
			world = FileManager.loadWorld(worldLocation);
		} catch (Exception e) {
			Console.error("Exception caught while loading test world "
			              + worldLocation, e);
			return false;
		}		
		return true;
	}
	public static void main(String[] args) throws IOException {
		Console.CURRENT_LOG_LEVEL = Console.LogLevel.DEBUG;
		Console.CURRENT_DEBUG_MODE = Console.DebugMode.DEBUG_R2D; // necessary

		// resourceTest("res/test_world.r2dw"); // load resources from ex. world

		World world;
		try {
			world = FileManager.loadWorld("res/test_world.r2dw");
		} catch (Exception e) {
			Console.error("FileManager: could not load world resource", e);
			return;
		}
		
		if (!Receiver2D.loadWorld(world)) {
			Console.log("Could not load world resource.");
			return;
		}
		
		Receiver2D.start();
		Receiver2D.stop();
	}
}