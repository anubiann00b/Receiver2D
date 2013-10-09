package com.receiver2d.engine;

/**
 * This class is for testing purposes only, although it currently is responsible
 * for initializing Receiver2D. NOT FOR RELEASE
 */
public class TestClass {
	/**
	 * Used to test that each of the methods in Geometry work properly. This
	 * method will be removed later.
	 */
	public static void testGeometryMethods() {
		Vector2D[] square = new Vector2D[] { //10x10 square
			new Vector2D(0, 0), new Vector2D(0, 10),
			new Vector2D(10, 10), new Vector2D(10, 0)
		};
		Vector2D pnt1 = new Vector2D(4, 4);
		Console.debug("Geometry.pointInPolygon test: ");
		Console.debug(""+Geometry.pointInPolygon(pnt1, square)); //is false
		
		Vector2D[] ln1 = new Vector2D[] {
			new Vector2D(0, 0), new Vector2D(10, 10)
		};
		Vector2D[] ln2 = new Vector2D[] {
			new Vector2D(0, 5), new Vector2D(1, 5)
		};
		Console.debug("Geometry.linearIntersectionPoint test: ");
		//Console.debug(""+Geometry.linearIntersectionPoint(ln1, ln2).toString());
	}
	public static void main(String[] args) {
		//Receiver2D.StartReciever2D(); // initialize the game
		testGeometryMethods(); //test all of the Geometry methods
	}
}
