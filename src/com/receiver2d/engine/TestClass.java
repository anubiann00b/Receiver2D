package com.receiver2d.engine;

import org.lwjgl.util.vector.Vector2f;

/**
 * This class is for testing purposes only, although it currently is responsible
 * for initializing Receiver2D. NOT FOR RELEASE
 */
public class TestClass {
	public static void main(String[] args) {
		for (Vector2f v : CollisionByBen.checkCollide(
				new Vector2f[] { new Vector2f(0, 0), new Vector2f(1, 1) },
				new Vector2f[] { new Vector2f(0, 1), new Vector2f(1, 0) })) {
			System.out.println(v.x + ", " + v.y);
		}
	}
}