package com.receiver2d.engine;

import org.lwjgl.util.vector.Vector2f;

/**
 * Contains point data (x, y). Useful for positions, velocity, forces, etc.
 */
public class Vector2D extends Vector2f {
	private static final long serialVersionUID = 1181019950100L; // "vec2d" in ascii

	/**
	 * A specific type of Vector2D with a magnitude of 0.
	 */
	public final static Vector2D ZERO = new Vector2D(0.0f, 0.0f);

	/**
	 * A specific type of Vector2D with each component having a magnitude of 1.
	 */
	public final static Vector2D ONE = new Vector2D(1.0f, 1.0f);

	/**
	 * Creates a new Vector2D from given points.
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 */
	public Vector2D(float x, float y) {
		super(x, y);
	}
	

	/**
	 * Creates a new Vector2D from an existing Vector2f.
	 * @param v2f The vector to model.
	 */
	public Vector2D(Vector2f v2f){
		this(v2f.x, v2f.y);
	}

	/**
	 * Calculates the Euclidean distance between two points.
	 * 
	 * @param vec1 The first vector.
	 * @param vec2 The second vector.
	 * @return The distance between two vectors.
	 */
	public static float distance(Vector2D vec1, Vector2D vec2) {
		return (float) Math.sqrt(Math.pow(vec1.x - vec2.x, 2) 
				+ Math.pow(vec1.y - vec2.y, 2));
	}

	/**
	 * Determines if the instantiated Vector2D equals the given Vector2D.
	 * 
	 * @param other A Vector2D to compare to.
	 * @return True if the Vector2D is equivalent; false if otherwise.
	 */
	public boolean equals(Vector2D other) {
		return (this.x == other.x && this.y == other.y);
	}
	
	/**
	 * Takes the given Vector2D and adds its x, y coordinates to the
	 * instantiated Vector2D.
	 * @param other The other Vector2D to add as a delta.
	 */
	public void add(Vector2D other) {
		x += other.x;
		y += other.y;
	}
}