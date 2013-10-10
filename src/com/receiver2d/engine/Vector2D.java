package com.receiver2d.engine;

import org.lwjgl.util.vector.Vector2f;

/**
 * Contains point data (x, y). Used for positions, velocity, forces, etc.
 */
public class Vector2D extends Vector2f {
	private static final long serialVersionUID = 7240034157894646780L;

	public Vector2D(float x, float y) {
		super(x, y);
	}

	public Vector2D(double x, double y) {
		super((float) x, (float) y);
	}

	/**
	 * Calculates the Euclidean distance between two points.
	 * 
	 * @param vec1
	 *            The first vector.
	 * @param vec2
	 *            The second vector.
	 * @return The distance between two vectors.
	 */
	public static float distance(Vector2D vec1, Vector2D vec2) {
		return (float) Math.sqrt(Math.pow(vec1.x - vec2.x, 2)
				+ Math.pow(vec1.y - vec2.y, 2));
	}

	public boolean equals(Vector2D comp) {
		return (comp.x == x && comp.y == y);
	}
}