package com.receiver2d.engine;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

/**
 * Deals with analyzing collisions between geometric figures in two-dimensional
 * space. This comes in handy for all sorts of things pertaining to the game
 * engine, such as AI and physics.
 */
public class Geometry {
	/**
	 * Checks whether or not two finite lines (Vector2f array of length 2)
	 * collide, and if they do, returns the point of their collision as a
	 * Vector2f.
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

		/*
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

			if (((x >= ln1[0].x && x <= ln1[1].x) || (x <= ln1[0].x && x >= ln1[1].x))
					&& ((x >= ln2[0].x && x <= ln2[1].x) || (x <= ln2[0].x && x >= ln2[1].x))
					&& ((y >= ln1[0].y && y <= ln1[1].y) || (y <= ln1[0].y && y >= ln1[1].y))
					&& ((y >= ln2[0].y && y <= ln2[1].y) || (y <= ln2[0].y && y >= ln2[1].y)))
				return new Vector2D(x, y);
			else
				return null;
		}
		/*
		 * slowest method: if (pointInPolygon(new Vector2D(x, y), new
		 * Vector2D[]{ ln1[0], ln1[1], ln2[0], ln2[1] })) return new Vector2D(x,
		 * y); else return null;
		 */
	}

	/**
	 * Checks whether two polygons collide. Polygons are defined as an array of
	 * vertices with each vertex (Vector2f) connected to the next one in the
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
		ArrayList<Vector2f> results = new ArrayList<Vector2f>();
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
	 *            A Vector2f array containing points of the first polygon/line.
	 * @param poly2
	 *            A Vector2f array containing points of the second polygon/line.
	 * @return "true" if the polygons collide; "false" otherwise
	 */
	public static boolean checkCollides(Vector2D[] poly1, Vector2D[] poly2) {
		if (poly1.length > 2 && poly2.length > 2)
			// we do this to save unnecessary calculations for lines
			return linearIntersectionPoint(poly1, poly2) != null;

		return polygonalIntersectionPoints(poly1, poly2) != null;
	}

	/**
	 * Determines if a point is inside a polygon using a winding number
	 * algorithm.
	 * @param pnt
	 *            A point.
	 * @param poly
	 *            An array of points, or a polygon.
	 * @return A true/false value depending on whether or not the point is
	 *         inside the polygon.
	 */
	public static boolean pointInPolygon(Vector2D pnt, Vector2D[] poly) {
		// TODO: fix this; it doesn't work
		
		float deg = 0.0f;
		for (int i=0; i<poly.length; i++) {
			//we calculate this using law of cosines:
			//c^2 = a^2 + b^2 - 2ab cos(angle)
			
		}
		return false; //fix
	}
	/**
	 * Differs from the built-in method Math.atan by providing a continuous
	 * angle ranging from 0 to 2pi, or 0 to 360. 
	 * @param x An x-coordinate.
	 * @param y A y-coordinate.
	 * @return The tangent of the angle formed.
	 */
	public static float atan(float x, float y) {
		//enables angles from 0->360, instead of Math.tan's 90->(-90)
		float deg = (float)(Math.atan(y/x) * 180/Math.PI);
		return x < 0 ? 180+deg : (y < 0 ? 360+deg : deg);
	}
}
