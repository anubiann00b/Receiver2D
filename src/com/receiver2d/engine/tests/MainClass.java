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
		resourceTest("res/test_world.r2dw"); // load resources from ex. world
		Receiver2D.start();
		Receiver2D.stop();
	}
}