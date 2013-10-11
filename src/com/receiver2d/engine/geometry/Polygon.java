package com.receiver2d.engine.geometry;

import java.util.LinkedList;

import com.receiver2d.engine.Vector2D;

/**
 * Any n-sided polygon, irregular or regular.
 * @author princeton
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
	 * @param points A set of vertices for the polygon.
	 */
	public Polygon(Vector2D[] points) {
		for (int i = 0; i < points.length; i++) {
			this.points.add(points[i]);
			this.length++;
		}
	}

	/**
	 * Gets a vertice from the polygon.
	 * @param index
	 * @return A point representing the coordinates of a vertice in the polygon.
	 */
	public Vector2D getPt(int index) {
		return points.get(index);
	}
}
