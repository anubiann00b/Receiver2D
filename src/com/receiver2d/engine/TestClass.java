package com.receiver2d.engine;

/**
 * This class is for testing purposes only, although it currently is responsible
 * for initializing Receiver2D. NOT FOR RELEASE
 */
public class TestClass {
	public static void main(String[] args) {
		Receiver2D.StartReciever2D(); // initialize the game
		Vector2D[] ln1 = new Vector2D[2];
			ln1[0] = new Vector2D(0, 5);
			ln1[1] = new Vector2D(8, -4);
		Vector2D[] ln2 = new Vector2D[2];
			ln2[0] = new Vector2D(-2, 2);
			ln2[1] = new Vector2D(10, 1);
		Vector2D iPnt = Geometry.linearIntersectionPoint(ln1, ln2);
		System.out.print("Lines collide: ");
		System.out.println(iPnt.toString());
		System.out.println(Vector2D.angle(ln1[0], ln1[1]) * 180/Math.PI);
	}
}
