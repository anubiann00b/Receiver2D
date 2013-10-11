package com.receiver2d.engine.geometry;

import com.receiver2d.engine.Vector2D;

/**
 * A circle.
 */
public class Circle {
	/**
	 * The position of the center of the circle.
	 */
	public Vector2D center;
	/**
	 * The radius of the circle.
	 */
	public float radius;
	
	/**
	 * Creates a new circle.
	 * @param center
	 * @param radius
	 */
	public Circle(Vector2D center, float radius) {
		this.center = center;
		this.radius = radius;
	}
}
