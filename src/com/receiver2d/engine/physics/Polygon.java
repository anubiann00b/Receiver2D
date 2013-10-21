package com.receiver2d.engine.physics;

import java.util.LinkedList;

import com.receiver2d.engine.Vector2D;

/**
 * Any n-sided polygon, irregular or regular.
 * 
 */
public class Polygon {
	public LinkedList<Vector2D> points = new LinkedList<Vector2D>();
	/**
	 * The number of points in the polygon.
	 */
	public int length;

	public Polygon() {
	}

	/**
	 * Creates a new polygon from a list of points.
	 * 
	 * @param points
	 *            A set of vertices for the polygon.
	 */
	public Polygon(Vector2D[] points) {
		for (int i = 0; i < points.length; i++) {
			this.points.add(points[i]);
			this.length++;
		}
	}

	/**
	 * Gets a vertex from the polygon.
	 * 
	 * @param index
	 * @return A point representing the coordinates of a vertex in the polygon.
	 */
	public Vector2D getPt(int index) {
		return points.get(index);
	}
}
