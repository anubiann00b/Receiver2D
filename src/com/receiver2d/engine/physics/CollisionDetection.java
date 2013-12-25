package com.receiver2d.engine.physics;

import java.util.ArrayList;
import com.receiver2d.engine.Vector2D;

/**
 * Deals with analyzing collisions between geometric figures in two-dimensional
 * space. This comes in handy for all sorts of things pertaining to the game
 * engine, such as AI and physics.
 */
public class CollisionDetection {
	private static int MAXIMUM_VERTICE_COUNT = 30;
	
	/**
	 * Sets the maximum number of vertices returned by the
	 * circleIntersectionPoints method, in the event that two circles lie on
	 * the same point and have the same radius.
	 * @param v The maximum number of vertices (v >= 3).
	 * @return The new value of the maximum number of vertices.
	 */
	public static int setDefaultVerticeCount(int v) {
		return MAXIMUM_VERTICE_COUNT = Math.max(v, 3);
	}
	
	/**
	 * Checks whether or not two finite lines collide, and if they do, returns 
	 * the point of their collision as a Vector2D.
	 * 
	 * @param ln1 An array containing two points on the first line.
	 * @param ln2 An array containing two points on the second line.
	 * @return A point of intersection, or null if the lines do not intersect.
	 */
	public static Vector2D linesIntersect(Polygon ln1, Polygon ln2) {
		if (ln1.length < 2 || ln2.length < 2) return null; // our parameters do
		// not contain a
		// line

		/*
		 * we assume line of format Ax + By = C 
		 * for example, with ln1: 
		 * 		x1 = ln1[0].x, x2 = ln1[1].x y1 = ln1[0].y, y2 = ln1[1].y
		 */
		float A1 = ln1.get(1).y - ln1.get(0).y;
		float B1 = ln1.get(0).x - ln1.get(1).x;
		float C1 = A1 * ln1.get(0).x + B1 * ln1.get(0).y;
		float A2 = ln2.get(1).y = ln2.get(0).y;
		float B2 = ln2.get(0).x - ln2.get(1).x;
		float C2 = A2 * ln2.get(0).x + B2 * ln2.get(0).y;
		float delta = A1 * B2 - A2 * B1;

		if (delta == 0) return null; // lines are parallel
		else {
			float x = (B2 * C1 - B1 * C2) / delta;
			float y = (A1 * C2 - A2 * C1) / delta;

			// if our x, y values are between the (x,y) for all four points
			if (((x >= ln1.get(0).x && x <= ln1.get(1).x) || (x <= ln1
					.get(0).x && x >= ln1.get(1).x))
					&& ((x >= ln2.get(0).x && x <= ln2.get(1).x) || (x <= ln2
							.get(0).x && x >= ln2.get(1).x))
					&& ((y >= ln1.get(0).y && y <= ln1.get(1).y) || (y <= ln1
							.get(0).y && y >= ln1.get(1).y))
					&& ((y >= ln2.get(0).y && y <= ln2.get(1).y) || (y <= ln2
							.get(0).y && y >= ln2.get(1).y))) 
				return new Vector2D(x, y);
			else return null;
			
			// TODO: use pnpoly implementation instead of this stupid if-statement
		}
	}

	/**
	 * Checks whether two polygons collide. Polygons are defined as an array of
	 * vertices with each vertex (Vector2f) connected to the next one in the
	 * array.
	 * <p/>
	 * Example: square - (0,0) (0,1) (1,1) (1,0)
	 * 
	 * @param polyA
	 *            the array of points for the first polygon
	 * @param polyB
	 *            the array of points for the second polygon
	 * @return The array of points where the two polygons overlap, or null if
	 *         they do not collide at all.
	 */
	public static Vector2D[] polyIntersect(Polygon polyA, Polygon polyB) {
		if (polyA.length < 2 || polyB.length < 2) return null; // we have not been given lines
		
		ArrayList<Vector2D> pnts = new ArrayList<Vector2D>();
		for (int i = 0; i < polyA.length; i++)
			for (int j = 0; j < polyB.length; j++) {

				Vector2D a1 = polyA.get(i); // line from vertex poly[i] to
				// poly[i+1]
				Vector2D a2 = polyA.get(i == polyA.length - 1 ? 0 : i + 1);
				Vector2D b1 = polyB.get(j); // line from vertex poly[j] to
				// poly[j+1]
				Vector2D b2 = polyB.get(j == polyB.length - 1 ? 0 : j + 1);

				Vector2D pnt = linesIntersect(
						new Polygon(a1, a2), new Polygon(b1, b2));
				if (pnt != null) pnts.add(pnt);
			}
		if (pnts.size() == 0) return null;
		else return pnts.toArray(new Vector2D[pnts.size()]);
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
	public static boolean checkCollides(Polygon poly1, Polygon poly2) {
		if (poly1.length > 2 && poly2.length > 2)
			// we do this to save unnecessary calculations for lines
			return linesIntersect(poly1, poly2) != null;

		return polyIntersect(poly1, poly2) != null;
	}

	/**
	 * Determines if a point is inside a polygon using a ray casting algorithm.
	 * If the point is on the edge of the polygon, the calculation returns
	 * false. See http://www.ecse.rpi.edu/~wrf/Research/Short_Notes/pnpoly.html
	 * 
	 * @param pt A point.
	 * @param poly An array of points, or a polygon.
	 * @return A true/false value depending on whether or not the point is
	 *         inside the polygon.
	 */
	public static boolean pointInPolygon(Vector2D pt, Polygon poly) {
		boolean c = false;

		// check if the point matches a vertex
		for (int i = 0; i < poly.length; i++)
			if (poly.get(i).equals(pt)) return c;

		// pnpoly
		for (int i = 0, j = poly.length - 1; i < poly.length; j = i++) {
			if (((poly.get(i).y > pt.y) != (poly.get(j).y > pt.y))
					&& (pt.x < (poly.get(j).x - poly.get(i).x)
							* (pt.y - poly.get(i).y)
							/ (poly.get(j).y - poly.get(i).y)
							+ poly.get(i).x)) c = !c;
		}
		return c;
	}

	/**
	 * Determines if two circles are colliding (intersecting).
	 * @param c1 The first circle.
	 * @param c2 The second circle.
	 * @return Whether or not the circles are colliding.
	 */
	public static boolean circlesCollide(Circle c1, Circle c2) {
		double d = Math.sqrt(Math.pow(c1.center.y - c2.center.y, 2)
				+ Math.pow(c1.center.x - c2.center.x, 2));
		
		return d <= (c1.radius + c2.radius);
	}
	
	/**
	 * Determines the points of intersection between two overlapping circles.
	 * @param c1 The first circle.
	 * @param c2 The second circle.
	 * @return The points of intersection, or null if the circles do not
	 * 		   intersect.
	 */
	public static Vector2D[] circleIntersect(Circle c1, Circle c2) {
		if (!circlesCollide(c1, c2)) return null;
		else if (c1.center.equals(c2) && c1.radius == c2.radius)
			return c1.getVertices(MAXIMUM_VERTICE_COUNT); // circles are inside
		
		// y^2 = r^2 - x^2 || r1^2 - r2^2 = x1^2 - x2^2
		
		double dr2 = Math.pow(c1.radius, 2) - Math.pow(c2.radius, 2);
		
		if (c1.center.x == c2.center.x) {
			double B1 = -2*c1.center.y, B2 = -2*c2.center.y,
				   C1 = Math.pow(c1.center.y, 2), C2 = Math.pow(c2.center.y, 2);
			double y = (dr2 - C1 + C2) / (B1 - B2);
			double x = Math.sqrt(Math.pow(c1.radius, 2) -
					Math.pow(c1.center.y - y, 2));
			return new Vector2D[]{ new Vector2D(x, y), new Vector2D(-x, y) };
		} else {
			double B1 = -2*c1.center.x, B2 = -2*c2.center.x,
				   C1 = Math.pow(c1.center.x, 2), C2 = Math.pow(c2.center.x, 2);
			double x = (dr2 - C1 + C2) / (B1 - B2);
			double y = Math.sqrt(Math.pow(c1.radius, 2) - 
					Math.pow(c1.center.x - x, 2));
			return new Vector2D[]{ new Vector2D(x, y), new Vector2D(x, -y) };
		}		
	}
}
