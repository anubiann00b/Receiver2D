package com.receiver2d.engine;

import org.lwjgl.util.vector.Vector2f;

/**
 * Contains point data (x, y). Very useful for positions, velocity, forces, etc.
 */
@SuppressWarnings("serial")
public class Vector2D extends Vector2f {
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	// Vector2D() overrides
		public Vector2D(Vector2f vec) {
			this.x = vec.x;
			this.y = vec.y;
		}
	/**
	 * Calculates the Euclidean distance between two points.
	 * @param vec1 The first vector.
	 * @param vec2 The second vector.
	 * @return The distance between two vectors.
	 */
	public static float distance(Vector2D vec1, Vector2D vec2) {
		return (float) Math.sqrt(
				Math.pow(vec1.x - vec2.x, 2) +
				Math.pow(vec1.y - vec2.y, 2));
	}
}