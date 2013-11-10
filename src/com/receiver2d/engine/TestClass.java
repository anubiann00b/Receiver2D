package com.receiver2d.engine;

import com.receiver2d.engine.entitysystem.Entity;
import com.receiver2d.engine.io.*;
import com.receiver2d.engine.physics.*;

/**
 * This class is for testing purposes only, although it currently is responsible
 * for initializing Receiver2D. NOT FOR RELEASE
 */
public class TestClass {
	/**
	 * Tests various geometry methods.
	 */
	public static void testGeometryMethods() {
		Polygon square = new Polygon(new Vector2D[] { new Vector2D(2, 1),
				new Vector2D(3, 2), new Vector2D(2, 3), new Vector2D(1, 2) });

		for (int i = 0; i <= 10; i++)
			for (int j = 0; j <= 10; j++) {
				Vector2D pnt1 = new Vector2D(i, j);
				if (CollisionDetection.pointInPolygon(pnt1, square))
					Console.debug("Point " + pnt1.toString() + " is in poly");
			}

		Polygon ln1 = new Polygon(new Vector2D[] { Vector2D.ZERO,
				new Vector2D(10, 10) });
		Polygon ln2 = new Polygon(new Vector2D[] { new Vector2D(2, 2),
				new Vector2D(2, 4) });
		Console.debug("ln1 intersects with ln2: "
				+ (CollisionDetection.linearIntersectionPoint(ln1, ln2)
						.toString() != null));
	}

	/**
	 * Tests the FileManager and prints output.
	 */
	public static void resourceTest() {
		// first, test world loading
		World world = null;
		try {
			//load the first world
			world = FileManager.loadWorld("res/test_scene.r2dw");
		} catch (Exception e) {
			Console.logError("Exception!", e);
		}
		if (world == null) {
			Console.debug("World file is null.");
			return;
		}
		// now, let's try accessing some stuff from our new world
		Console.debug("Scene length: " + world.scenes.size());
		Scene scene = world.scenes.get(0);
		Console.debug("Scene 0 has name \"" + scene.getName() + "\"");
		Console.debug("Player name is " + scene.getValue("Player Name"));
		Console.debug("Default enemy strength is "+scene.getValue("Default Enemy Strength"));
		int des = (int) scene.getValue("Default Enemy Strength");
		Console.debug("DES + 1 = "+ ++des);
		Console.debug("Getting resource 0 path: "+world.getResources().get(0).getPath());
		
		// here we begin to test Entity loading
		Entity boss = scene.getEntityList().get(0); // this should be "Boss"
		Console.debug("Getting Entity 0, aka \""+boss.name+"\"");
		Console.debug(boss.name+" has rotation "+boss.rotation);
		
		// test loading a new resource
		R2DResource r = new R2DResource("res/test_scene.r2dw");
		Console.debug(r.load() ? "Loading file \"" + r.getPath()
				+ "\" was successful." : "Loading file \"" + r.getPath()
				+ "\" failed.");

	}

	public static void main(String[] args) {
		System.out.println(System.getProperty("java.vm.name"));
		Console.DEBUG = true;
		Console.PRINT_STACK_TRACE = true;
		Receiver2D.start(); // initialize the game

		resourceTest(); // test our world loading

		Receiver2D.stop();
	}
}
