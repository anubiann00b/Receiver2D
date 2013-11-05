package com.receiver2d.engine.physics;

import com.receiver2d.engine.Vector2D;

import java.util.ArrayList;

/**
 * Deals with analyzing collisions between geometric figures in two-dimensional
 * space. This comes in handy for all sorts of things pertaining to the game
 * engine, such as AI and physics.
 */
public class CollisionDetection {
	/**
	 * Checks whether or not two finite lines (Vector2f array of length 2)
	 * collide, and if they do, returns the point of their collision as a
	 * Vector2f.
	 *
	 * @param ln1 An array containing two points on the first line.
	 * @param ln2 An array containing two points on the second line.
	 * @return A point of intersection, or null otherwise.
	 */
	public static Vector2D linearIntersectionPoint (Polygon ln1, Polygon ln2) {
		if (ln1.length < 2 || ln2.length < 2) return null; // our parameters do
		// not contain a
		// line

		/*
		 * we assume line of format Ax + By = C for example, with ln1: x1 =
		 * ln1[0].x, x2 = ln1[1].x y1 = ln1[0].y, y2 = ln1[1].y
		 */
		float A1 = ln1.getPt(1).y - ln1.getPt(0).y;
		float B1 = ln1.getPt(0).x - ln1.getPt(1).x;
		float C1 = A1 * ln1.getPt(0).x + B1 * ln1.getPt(0).y;
		float A2 = ln2.getPt(1).y = ln2.getPt(0).y;
		float B2 = ln2.getPt(0).x - ln2.getPt(1).x;
		float C2 = A2 * ln2.getPt(0).x + B2 * ln2.getPt(0).y;
		float delta = A1 * B2 - A2 * B1;

		if (delta == 0) return null; // lines are parallel
		else {
			float x = (B2 * C1 - B1 * C2) / delta;
			float y = (A1 * C2 - A2 * C1) / delta;

			// if is on both lines
			if (((x >= ln1.getPt(0).x && x <= ln1.getPt(1).x) || (x <= ln1
					.getPt(0).x && x >= ln1.getPt(1).x))
					&& ((x >= ln2.getPt(0).x && x <= ln2.getPt(1).x) || (x <= ln2
					.getPt(0).x && x >= ln2.getPt(1).x))
					&& ((y >= ln1.getPt(0).y && y <= ln1.getPt(1).y) || (y <= ln1
					.getPt(0).y && y >= ln1.getPt(1).y))
					&& ((y >= ln2.getPt(0).y && y <= ln2.getPt(1).y) || (y <= ln2
					.getPt(0).y && y >= ln2.getPt(1).y))) return new Vector2D(
					x, y);
			else return null;
		}
	}

	/**
	 * Checks whether two polygons collide. Polygons are defined as an array of
	 * vertices with each vertex (Vector2f) connected to the next one in the
	 * array.
	 * <p/>
	 * Example: square - (0,0) (0,1) (1,1) (1,0)
	 *
	 * @param polyA the array of points for the first polygon
	 * @param polyB the array of points for the second polygon
	 * @return The array of points where the two polygons overlap, or null if
	 *         they do not collide at all.
	 */
	public static Vector2D[] polygonalIntersectionPoints (Polygon polyA,
														  Polygon polyB) {
		if (polyA.length < 2 || polyB.length < 2) return null; // we have not
		// been given
		// lines
		ArrayList<Vector2D> results = new ArrayList<Vector2D>();
		for (int i = 0; i < polyA.length; i++)
			for (int j = 0; j < polyB.length; j++) {

				Vector2D a1 = polyA.getPt(i); // line from vertex poly[i] to
				// poly[i+1]
				Vector2D a2 = polyA.getPt(i == polyA.length - 1 ? 0 : i + 1);
				Vector2D b1 = polyB.getPt(j); // line from vertex poly[j] to
				// poly[j+1]
				Vector2D b2 = polyB.getPt(j == polyB.length - 1 ? 0 : j + 1);

				Vector2D pnt = linearIntersectionPoint(new Polygon(
						new Vector2D[] {a1, a2}), new Polygon(new Vector2D[] {
						b1, b2}));
				if (pnt != null) results.add(pnt);
			}
		if (results.size() == 0) return null;
		else return results.toArray(new Vector2D[results.size()]);
	}

	/**
	 * Returns a true/false value determining whether or not two polygons or
	 * lines collide.
	 *
	 * @param poly1 A Vector2f array containing points of the first polygon/line.
	 * @param poly2 A Vector2f array containing points of the second polygon/line.
	 * @return "true" if the polygons collide; "false" otherwise
	 */
	public static boolean checkCollides (Polygon poly1, Polygon poly2) {
		if (poly1.length > 2 && poly2.length > 2)
			// we do this to save unnecessary calculations for lines
			return linearIntersectionPoint(poly1, poly2) != null;

		return polygonalIntersectionPoints(poly1, poly2) != null;
	}

	/**
	 * Determines if a point is inside a polygon using a ray casting algorithm.
	 * If the point is on the edge of the polygon, the calculation returns
	 * false; See http://www.ecse.rpi.edu/~wrf/Research/Short_Notes/pnpoly.html
	 *
	 * @param pt   A point.
	 * @param poly An array of points, or a polygon.
	 * @return A true/false value depending on whether or not the point is
	 *         inside the polygon.
	 */
	public static boolean pointInPolygon (Vector2D pt, Polygon poly) {
		boolean c = false;

		// check if the point matches a vertex
		for (int i = 0; i < poly.length; i++)
			if (poly.getPt(i).equals(pt)) return c;

		// pnpoly
		for (int i = 0, j = poly.length - 1; i < poly.length; j = i++) {
			if (((poly.getPt(i).y > pt.y) != (poly.getPt(j).y > pt.y))
					&& (pt.x < (poly.getPt(j).x - poly.getPt(i).x)
					* (pt.y - poly.getPt(i).y)
					/ (poly.getPt(j).y - poly.getPt(i).y)
					+ poly.getPt(i).x)) c = !c;
		}
		return c;
	}

	// public static Vector2D circleCollide(Circle c1, Circle c2) {
	public static boolean circleCollide (Circle c1, Circle c2) {
		double d = Math.sqrt(Math.pow(c1.center.y - c2.center.y, 2)
				+ Math.pow(c1.center.x - c2.center.x, 2));
		double r = c1.radius + c2.radius;

		// Vector2D estimate;
		// if (d < r) { // collision
		// estimate.x = (c1.center.x - c2.center.x)
		// }
		//
		// return estimate;

		return d <= r;
	}
}
