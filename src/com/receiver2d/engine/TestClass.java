package com.receiver2d.engine;

import com.receiver2d.engine.geometry.CollisionDetection;
import com.receiver2d.engine.geometry.Polygon;

/**
 * This class is for testing purposes only, although it currently is responsible
 * for initializing Receiver2D. NOT FOR RELEASE
 */
public class TestClass {
	public static void testGeometryMethods() {
		Polygon square = new Polygon(new Vector2D[] { new Vector2D(2, 1),
				new Vector2D(3, 2), new Vector2D(2, 3), new Vector2D(1, 2) });

		for (int i = 0; i <= 10; i++)
			for (int j = 0; j <= 10; j++) {
				Vector2D pnt1 = new Vector2D(i, j);
				if (CollisionDetection.pointInPolygon(pnt1, square))
					Console.debug("Point " + pnt1.toString() + " is in poly");
			}
		Polygon ln1 = new Polygon(new Vector2D[] { new Vector2D(0, 0),
				new Vector2D(10, 10) });
		Polygon ln2 = new Polygon(new Vector2D[] { new Vector2D(2, 2),
				new Vector2D(2, 4) });
		Console.debug("ln1 intersects with ln2: "
				+ (CollisionDetection.linearIntersectionPoint(ln1, ln2).toString() != null));
	}

	public static void main(String[] args) {
		Receiver2D.StartReciever2D(); // initialize the game
		testGeometryMethods(); // test all of the Geometry methods
	}
}
