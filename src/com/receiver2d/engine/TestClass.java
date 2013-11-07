package com.receiver2d.engine;

import com.receiver2d.engine.io.FileManager;
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
		Polygon square = new Polygon(new Vector2D[] {new Vector2D(2, 1),
				new Vector2D(3, 2), new Vector2D(2, 3), new Vector2D(1, 2)});

		for (int i = 0; i <= 10; i++)
			for (int j = 0; j <= 10; j++) {
				Vector2D pnt1 = new Vector2D(i, j);
				if (CollisionDetection.pointInPolygon(pnt1, square))
					Console.debug("Point " + pnt1.toString() + " is in poly");
			}

		Polygon ln1 = new Polygon(new Vector2D[] {Vector2D.ZERO,
				new Vector2D(10, 10)});
		Polygon ln2 = new Polygon(new Vector2D[] {new Vector2D(2, 2),
				new Vector2D(2, 4)});
		Console.debug("ln1 intersects with ln2: "
				+ (CollisionDetection.linearIntersectionPoint(ln1, ln2)
				.toString() != null));
	}
	
	/**
	 * Tests the FileManager and prints output.
	 */
	public static void testWorldLoad() {
		World world = new World("Test");
		try {
			world = FileManager.loadWorld("src/res/test_scene.r2dw");
		} catch (Exception e) {
			Console.logError("Exception!", e);
		}
		if (world == null) {
			Console.debug("World file is null.");
			return;
		}
		// now, let's try accessing some stuff from our new world
		Console.debug("Scene length: "+world.scenes.size());
		//Console.log("Scene 0 has name "+world.scenes.get(0).getName());
	}

	public static void main(String[] args) {
		Console.DEBUG = true;
		Receiver2D.start(); // initialize the game
		
		testWorldLoad(); // test our world loading
		
		Receiver2D.stop();
	}
}
