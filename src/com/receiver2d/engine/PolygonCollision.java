package com.receiver2d.engine;

public class PolygonCollision {
	/**
	 * Checks whether two polygons collide.
	 * Polygons are defined as an array of vectors with each vector connected to the next one in the array.
	 * Example: square - (0,0) (0,1) (1,1) (1,0)
	 * 
	 * @param polyA
	 *            - the array of points for the first polygon
	 * @param polyB
	 *            - the array of points for the second polygon
	 * @return the position where the two polygons collide, or null if they
	 *         don't
	 */
	public static Vec2d checkCollide(Vec2d[] polyA, Vec2d[] polyB) {
		
		// line from point poly[i] to poly[i+1] (wraps around)
		for (int edgeA = 0; edgeA < polyA.length; edgeA++) {
			Vec2d a1 = polyA[edgeA];
			Vec2d a2 = polyA[(edgeA == polyA.length ? 0 : edgeA + 1)];
			for (int edgeB = 0; edgeB < polyB.length; edgeB++) {
				Vec2d b1 = polyB[edgeB];
				Vec2d b2 = polyB[(edgeB == polyB.length ? 0 : edgeB + 1)];
				
				//calculations
				
			}
		}
		return null;
	}
}
