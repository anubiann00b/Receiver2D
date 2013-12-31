package com.receiver2d.engine.graphics;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Image;

import org.lwjgl.opengl.Display;

import com.receiver2d.engine.Receiver2D;
import com.receiver2d.engine.Vector2D;
import com.receiver2d.engine.World;
import com.receiver2d.engine.entitysystem.Entity;
import com.receiver2d.engine.entitysystem.Skybox;
import com.receiver2d.engine.geometry.Point2D;

/**
 * In this class we abstract all drawing functions. We should be able to do, for
 * example, Renderer.drawQuad(Vector2D a, Vector2D b);
 */
public class Renderer {
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
		// grey
		// set viewport to display dimension
		glViewport(0, 0, Display.getWidth(), Display.getHeight());

		// testing display update
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		Display.update();

		// TODO: render all in-game elements that are visible here

		return !Display.isCloseRequested();
	}
	
	/**
	 * Draws an Entity to the display.
	 * 
	 * @param entity The Entity to draw.
	 */
	public static void drawEntity(Entity entity) {
		if (!entity.visible) return; // don't render invisible entities

		// TODO: get texture of Entity
		Image i = entity.texture.getRendered();

		glBegin(GL_POLYGON); // draw the mesh of the entity
		for (Point2D pnt : entity.getDelocalizedMesh())
			glVertex2f(pnt.x, pnt.y);
		glEnd();
	}
	
	/**
	 * Updates the visual aspects of the game.
	 */
	public static void gameUpdate() {
		World world;
		if ((world = Receiver2D.getLoadedWorld()) == null) return;
		
		// draw skybox first
		Skybox sbox = world.scenes.get(0).skybox;
//		Image i = sbox.texture.getRendered();
	}
}