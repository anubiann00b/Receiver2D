package com.receiver2d.engine;

import java.util.ArrayList;

public class PolygonCollision {
	/**
	 * Checks whether two polygons collide. Polygons are defined as an array of
	 * vertices with each vertex (Vector2D) connected to the next one in the
	 * array.
	 * 
	 * Example: square - (0,0) (0,1) (1,1) (1,0)
	 * 
	 * @param polyA
	 *            the array of points for the first polygon
	 * @param polyB
	 *            the array of points for the second polygon
	 * @return The array of points where the two polygons overlap, or null if
	 *         they do not collide at all.
	 */
	public static Vector2D[] checkCollide(Vector2D[] polyA, Vector2D[] polyB) {
		ArrayList<Vector2D> result = new ArrayList<Vector2D>();
		for (int i = 0; i < polyA.length; i++) {
			for (int j = 0; j < polyB.length; j++) {

				Vector2D a1 = polyA[i]; // line from vertex poly[i] to poly[i+1]
				Vector2D a2 = polyA[(i == polyA.length - 1 ? 0 : i + 1)];
				Vector2D b1 = polyB[j]; // line from vertex poly[j] to poly[j+1]
				Vector2D b2 = polyB[(j == polyB.length - 1 ? 0 : j + 1)];

				float m_a = (a1.y - a2.y) / (a1.x - a2.x);
				float m_b = (b1.y - b2.y) / (b1.x - b2.x);
				float b_a = a1.y / a1.x / m_a;
				float b_b = b1.y / b1.x / m_b;
				float x = (b_b - b_a) / (m_a - m_b);
				float y = m_a * x + b_a;

				//if (x,y) is on line a and b
				if ((a1.x < x && x < a2.x) || (a2.x < x && x < a1.x)) {
					if ((b1.x < x && x < b2.x) || (b2.x < x && x < b1.x)) {
						if ((a1.y < y && y < a2.y) || (a2.y < y && y < a1.y)){
							if ((b1.y < y && y < b2.y) || (b2.y < y && y < b1.y)){
								result.add(new Vector2D(x, y));
							}
						}
					}
				}
				
				/*
				 * // calculations try { // we must have a try/catch block for
				 * potential division // by zero
				 * 
				 * float m_a = (a1.y - a2.y) / (a1.x - a2.x); // slope, line a
				 * float m_b = (b1.y - b2.y) / (b1.x - b2.x); // slope, line b
				 * 
				 * // TODO: check for lines where slope is undefined
				 * 
				 * float x = (m_a * a1.x - m_b * b1.x + a1.y + a1.x) / (m_a -
				 * m_b); float y = m_a * (x - a1.x) + a1.y;
				 * 
				 * intersections.add(new Vector2D(x, y)); } catch (Exception e)
				 * { }
				 */
			}
		}
		if(result.size() == 0)
			return null;
		else
			return result.toArray(new Vector2D[result.size()]);
	}
}
