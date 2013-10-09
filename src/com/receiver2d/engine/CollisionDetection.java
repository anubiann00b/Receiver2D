package com.receiver2d.engine;

import java.util.ArrayList;

/**
 * Deals with analyzing collisions between geometric figures in two-dimensional
 * space. This comes in handy for all sorts of things pertaining to the game
 * engine, such as AI and physics.
 */
public class CollisionDetection {
	/**
	 * Checks whether or not two finite lines (Vector2D array of length 2)
	 * collide, and if they do, returns the point of their collision as a
	 * Vector2D.
	 * 
	 * @param ln1
	 *            An array containing two points on the first line.
	 * @param ln2
	 *            An array containing two points on the second line.
	 * @return A point of intersection, or null otherwise.
	 */
	public static Vector2D linearIntersectionPoint(Vector2D[] ln1,
			Vector2D[] ln2) {
		if (ln1.length < 2 || ln2.length < 2)
			return null; // our parameters do not contain a line

		/**
		 * we assume line of format Ax + By = C for example, with ln1: x1 =
		 * ln1[0].x, x2 = ln1[1].x y1 = ln1[0].y, y2 = ln1[1].y
		 */
		float A1 = ln1[1].y - ln1[0].y;
		float B1 = ln1[0].x - ln1[1].x;
		float C1 = A1 * ln1[0].x + B1 * ln1[0].y;
		float A2 = ln2[1].y - ln2[0].y;
		float B2 = ln2[0].x - ln2[1].x;
		float C2 = A2 * ln2[0].x + B2 * ln2[0].y;
		float delta = A1 * B2 - A2 * B1;

		if (delta == 0)
			return null; // lines are parallel
		else {
			float x = (B2 * C1 - B1 * C2) / delta;
			float y = (A1 * C2 - A2 * C1) / delta;
			// TODO: return null if x, y are out of range for our finite
			// lines

			return new Vector2D(x, y);
		}
	}

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
	public static Vector2D[] polygonalIntersectionPoints(Vector2D[] polyA,
			Vector2D[] polyB) {
		if (polyA.length < 2 || polyB.length < 2)
			return null; // we have not been given lines
		ArrayList<Vector2D> results = new ArrayList<Vector2D>();
		for (int i = 0; i < polyA.length; i++)
			for (int j = 0; j < polyB.length; j++) {

				Vector2D a1 = polyA[i]; // line from vertex poly[i] to poly[i+1]
				Vector2D a2 = polyA[(i == polyA.length - 1 ? 0 : i + 1)];
				Vector2D b1 = polyB[j]; // line from vertex poly[j] to poly[j+1]
				Vector2D b2 = polyB[(j == polyB.length - 1 ? 0 : j + 1)];

				Vector2D pnt = linearIntersectionPoint(
						new Vector2D[] { a1, a2 }, new Vector2D[] { b1, b2 });
				if (pnt != null)
					results.add(pnt);
			}
		if (results.size() == 0)
			return null;
		else
			return results.toArray(new Vector2D[results.size()]);
	}

	/**
	 * Returns a true/false value determining whether or not two polygons or
	 * lines collide.
	 * 
	 * @param poly1
	 *            A Vector2D array containing points of the first polygon/line.
	 * @param poly2
	 *            A Vector2D array containing points of the second polygon/line.
	 * @return "true" if the polygons collide; "false" otherwise
	 */
	public static boolean checkCollides(Vector2D[] poly1, Vector2D[] poly2) {
		if (poly1.length > 2 && poly2.length > 2)
			return linearIntersectionPoint(poly1, poly2) != null;
		// we do this to save unnecessary calculations for lines
		return polygonalIntersectionPoints(poly1, poly2) != null;
	}

	/**
	 * Determines if a point is inside a polygon using a winding algorithm.
	 * 
	 * @param pnt
	 *            A point.
	 * @param poly
	 *            An array of points, or a polygon.
	 * @return A true/false value depending on whether or not the point is
	 *         inside the polygon.
	 */
	public static boolean pointInPolygon(Vector2D pnt, Vector2D[] poly) {
		// compute each of the angles between "pnt" and poly[i]
		float[] degs = new float[poly.length];
		float deg = 0.0f;
		for (int i = 0; i < poly.length; i++) {
			// TODO: implement wrap-around and fix algorithm
			float d = (float) Math.atan((poly[i].x - pnt.x)
					/ (poly[i].y - pnt.y));
			degs[i] = d;
			deg += i == 0 ? degs[i] : degs[i] - degs[i - 1];
		}
		return (int) deg == 0;
	}
}
