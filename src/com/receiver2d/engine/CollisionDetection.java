package com.receiver2d.engine;

import java.util.ArrayList;

public class CollisionDetection {
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
		ArrayList<Vector2D> intersections = new ArrayList<Vector2D>();
		// line from vertex poly[i] to poly[i+1] (wraps around)
		for (int i = 0; i < polyA.length; i++) {
			Vector2D a1 = polyA[i];
			Vector2D a2 = polyA[(i == polyA.length - 1 ? 0 : i + 1)];
			// line from vertex poly[j] to poly[j+1] (wraps around)
			for (int j = 0; j < polyB.length; j++) {
				Vector2D b1 = polyB[j];
				Vector2D b2 = polyB[(j == polyB.length - 1 ? 0 : j + 1)];

				// calculations
				try {
					/*
					 * we must have a try/catch block for potential division by
					 * zero
					 */
					boolean m_a_undefined = false, m_b_undefined = false;
					float m_a = 0.0f, m_b = 0.0f;
					try {
						m_a = (a1.y - a2.y) / (a1.x - a2.x); // slope, vertex a
					} catch (Exception e) {
						m_a_undefined = true;
					}
					try {
						m_b = (b1.y - b2.y) / (b1.x - b2.x); // slope, vertex b
					} catch (Exception e) {
						m_b_undefined = true;
					}

					// TODO: check for lines where slope is undefined

					float x = (m_a * a1.x - m_b * b1.x + a1.y + a1.x)
							/ (m_a - m_b);
					float y = m_a * (x - a1.x) + a1.y;
					intersections.add(new Vector2D(x, y));
				} catch (Exception e) {
				}
			}
		}
		return intersections.toArray(new Vector2D[intersections.size()]);
	}

}
