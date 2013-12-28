package com.receiver2d.engine.geometry;

import com.receiver2d.engine.Vector2D;

/**
 * A special case of a Vector2D, a Point2D is useful for geometry, which unlike
 * a Vector2D, is linked to other points. This enables fast search-and-replace
 * methods within a large n-sided polygon.
 */
public class Point2D extends Vector2D {
	private Point2D from;
	private Point2D to;
	
	/**
	 * Creates a new point from a Vector2D.
	 * @param vertex The vertex to extend from.
	 */
	public Point2D(Vector2D vertex) {
		super(vertex.x, vertex.y);
	}
	
	/**
	 * Creates a new point from a Vector2D and sets it to extend _to_ another
	 * point "to", and _from_ another point "from".
	 * @param vertex The point for the Point2D.
	 * @param to The point to extend to.
	 * @param from The point to extend from.
	 */
	public Point2D(Vector2D vertex, Point2D to, Point2D from) {
		this(vertex.x, vertex.y);
		this.to = to;
		to.from = this;
		this.from = from;
		from.to = this;
	}
	
	/**
	 * Creates a new point from x and y coordinates.
	 * @param x The x-coordinate of the point.
	 * @param y The y-coordinate of the point.
	 */
	public Point2D(float x, float y) {
		super(x, y);
	}
	
	/**
	 * Creates a new point from x and y coordinates.
	 * @param x The x-coordinate of the point.
	 * @param y The y-coordinate of the point.
	 */
	public Point2D(double x, double y) {
		super(x, y);
	}
	
	/**
	 * Sets the given point, p, to extend _from this point_, and sets this point
	 * to extend to the given point.
	 * @param p The point to extend to.
	 */
	public void pointTo(Point2D p) {
		to = p;
		p.from = this;
	}
	
	/**
	 * Sets the given point, p, to extend _to this point_, and sets this point
	 * to extend from the given point.
	 * @param p The point to extend from.
	 */
	public void pointFrom(Point2D p) {
		from = p;
		p.to = this;
	}
}
