package com.receiver2d.engine;

/**
 * This class is for testing purposes only, although it currently is responsible
 * for initializing Receiver2D. NOT FOR RELEASE
 */
public class TestClass {
	public static void testGeometryMethods() {
		Vector2D[] square = new Vector2D[] {
		new Vector2D(0, 0), new Vector2D(0, 10), new Vector2D(10, 10),
				new Vector2D(10, 0) };
		Vector2D pnt1 = new Vector2D(0, 5);
		Console.debug("Geometry.pointInPolygon test: "
				+ Geometry.pointInPolygon(pnt1, square));

		Vector2D[] ln1 = new Vector2D[] { new Vector2D(0, 0),
				new Vector2D(10, 10) };
		Vector2D[] ln2 = new Vector2D[] { new Vector2D(2, 2),
				new Vector2D(2, 4) };
		Console.debug("Geometry.linearIntersectionPoint test: "
				+ (Geometry.linearIntersectionPoint(ln1, ln2).toString() != null));
	}

	public static void main(String[] args) {
		// Receiver2D.StartReciever2D(); // initialize the game
		testGeometryMethods(); // test all of the Geometry methods
	}
}
