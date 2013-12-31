package com.receiver2d.engine.tests;

import static org.junit.Assert.*;

import org.junit.*;

import com.receiver2d.engine.Console;
import com.receiver2d.engine.Vector2D;
import com.receiver2d.engine.geometry.CollisionDetection;
import com.receiver2d.engine.geometry.Polygon;

public class GeometryTest {
	
	@Before
	public void setUp() throws Exception {
		Console.CURRENT_LOG_LEVEL = Console.LogLevel.DEBUG;
	}
	
	@Test
	public void testPtInPoly() {
		Polygon square = new Polygon(2,1, 3,2, 2,3, 1,2);

		for (int i = 0; i <= 10; i++)
			for (int j = 0; j <= 10; j++)
				assertEquals(i==2 && j==2, CollisionDetection.
									pointInPolygon(new Vector2D(i, j), square));
	}
	
	@Test
	public void testLineIntersect(){
		Polygon ln1 = new Polygon(0,0, 10,10);
		Polygon ln2 = new Polygon(2,2, 2,4);
		assertEquals(new Vector2D(2,2), CollisionDetection.
													linesIntersect(ln1, ln2));
	}

}
