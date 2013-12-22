package com.receiver2d.engine.physics;

import java.util.Iterator;
import java.util.LinkedList;

import com.receiver2d.engine.Vector2D;

/**
 * Any n-sided polygon, irregular or regular.
 */
public class Polygon implements Iterable<Vector2D> {
	public LinkedList<Vector2D> points = new LinkedList<Vector2D>();
	/**
	 * The number of points in the polygon.
	 */
	public int length;

	/**
	 * Creates a new polygon from a list of points.
	 * 
	 * @param points
	 *            A set of vertices for the polygon.
	 */
	public Polygon(Vector2D... points) {
		for (Vector2D point : points) {
			this.points.add(point);
			this.length++;
		}
	}

	/**
	 * Creates a new polygon from a list of points.
	 * 
	 * @param points
	 *            A set of vertices for the polygon.
	 */
	public Polygon(double... points) {
		for (int i = 0; i + 1 < points.length; i += 2) {
			this.points.add(new Vector2D((float) points[i], (float) points[i + 1]));
			this.length++;
		}
	}

	/**
	 * Creates a new polygon from a list of points.
	 * 
	 * @param points
	 *            A set of vertices for the polygon.
	 */
	public Polygon(float... points) {
		for (int i = 0; i + 1 < points.length; i += 2) {
			this.points.add(new Vector2D(points[i], points[i + 1]));
			this.length++;
		}
	}

	/**
	 * Gets a vertex from the polygon.
	 * 
	 * @param index
	 * @return A point representing the coordinates of a vertex in the polygon.
	 */
	public Vector2D get(int index) {
		return points.get(index);
	}

	/**
	 * Allow {@code for(Vector2D v : p)}
	 */
	@Override
	public Iterator<Vector2D> iterator() {
		return points.iterator();
	}

}
