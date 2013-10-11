package com.receiver2d.engine.geometry;

import java.util.LinkedList;

import com.receiver2d.engine.Vector2D;

public class Polygon {
	public LinkedList<Vector2D>	points	= new LinkedList<Vector2D>();
	public int					length;

	public Polygon() {}

	public Polygon(Vector2D[] points) {
		for (int i = 0; i < points.length; i++) {
			this.points.add(points[i]);
			this.length++;
		}
	}

	public Vector2D getPt(int index) {
		return points.get(index);
	}
}
