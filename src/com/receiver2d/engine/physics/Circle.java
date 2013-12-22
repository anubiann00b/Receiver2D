package com.receiver2d.engine.physics;

import com.receiver2d.engine.Vector2D;

/**
 * A special circle type of a polygon.
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
	 * 
	 * @param center
	 * @param radius
	 */
	public Circle(Vector2D center, float radius) {
		this.center = center;
		this.radius = radius;
	}
	
	/**
	 * Represents this circle as a polygon with a set complexity n.
	 * @param n The complexity of the polygon, or the number of vertices.
	 * @return A list of vertices, or null if n<1.
	 */
	public Vector2D[] getVertices(int n) {
		if (n < 1) return null;
		
		Vector2D[] verts = new Vector2D[n];
		for (double i=0, r=i*2*Math.PI/n; i<n; i++, r=i*2*Math.PI/n)
			verts[(int)i] = new Vector2D(
					radius * Math.cos(r),
					radius * Math.sin(r));
		
		return verts;
	}
	
	/**
	 * Converts this Circle into a Polygon.
	 * @param n The complexity of the Polygon, or the number of vertices.
	 * @return A polygonal representation of this circle.
	 */
	public Polygon asPolygon(int n) {
		return new Polygon(getVertices(n));
	}
	
	@Override
	public String toString() {
		return "(" + center.x + ", " + center.y + ", r = " + radius + ")";
	}
}
