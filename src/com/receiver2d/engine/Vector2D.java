package com.receiver2d.engine;

import org.lwjgl.util.vector.Vector2f;

/**
 * Contains point data (x, y). Used for positions, velocity, forces, etc.
 */
public class Vector2D extends Vector2f {

	/**
	 * A specific type of Vector2D with a magnitude of 0.
	 */
	public final static Vector2D ZERO = new Vector2D(0.0f, 0.0f);

	/**
	 * A specific type of Vector2D with each component having a magnitude of 1.
	 */
	public final static Vector2D ONE = new Vector2D(1.0f, 1.0f);

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
		return (float) Math.sqrt(Math.pow(vec1.x - vec2.x, 2) + Math.pow(vec1.y - vec2.y, 2));
	}

	/**
	 * Determines if the instantiated Vector2D equals the given Vector2D.
	 * 
	 * @param otherVec
	 *            A Vector2D to compare to.
	 * @return True if the Vector2D is equivalent; false if otherwise.
	 */
	public boolean equals(Vector2D otherVec) {
		return (this.x == otherVec.x && this.y == otherVec.y);
	}
}