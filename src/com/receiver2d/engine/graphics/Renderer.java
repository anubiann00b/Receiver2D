package com.receiver2d.engine.graphics;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

import com.receiver2d.engine.Vector2D;
import com.receiver2d.engine.entitysystem.Entity;

/**
 * In this class we abstract all drawing functions. We should be able to do, for
 * example, Renderer.drawQuad(Vector2D a, Vector2D b);
 */
public class Renderer {
	/**
	 * Draws an Entity to the display.
	 * @param entity The Entity to draw.
	 */
	public static void drawEntity(Entity entity) {
		if (!entity.visible) return; //don't render invisible entities
		
		//TODO: get color of entity
		
		
		glBegin(GL_POLYGON); // draw the mesh of the entity
		for (Vector2D pnt : entity.mesh.points)
			glVertex2f(pnt.x, pnt.y);
		glEnd();
	}
	
	/**
	 * Performs a rendering update, in which the Display is cleared and new
	 * things are drawn on screen.
	 * 
	 * @return Whether or not the next rendering update is available (based on
	 *         if the user prompted a display close event). If false, then the
	 *         next render is prevented and the display (along with the rest of
	 *         the program) quits.
	 */
	public static boolean update() {
		
		glClearColor(0.5f, 0.5f, 0.5f, 0f); // clear screen with transparent
		// black
		// set viewport to display dimension
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		Display.update();

		// testing display update
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		//render all in-game elements that are visible
		
		
		return !Display.isCloseRequested();
	}
}