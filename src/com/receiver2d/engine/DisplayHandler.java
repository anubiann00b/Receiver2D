package com.receiver2d.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

import com.receiver2d.engine.entitysystem.Entity;

/**
 * Handles the appearance of things on-screen and draws them.
 */
public class DisplayHandler {
	private static int width = 0;
	private static int height = 0;
	private static boolean hasInitialized = false;
	
	/**
	 * Initiates the display, which is responsible for all visual output of
	 * rendering.
	 * @param title The title of the window.
	 * @param fullscreen Whether or not the window starts off full-screen.
	 * @param wd The width of the window, in pixels.
	 * @param ht The height of the window, in pixels.
	 */
	public static void init(String title, boolean fullscreen, int width, int height) {
		if (hasInitialized) return;
		hasInitialized = true;
		
		DisplayHandler.width = width;
		DisplayHandler.height = height;
		try {
			Display.setTitle(title);
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setFullscreen(fullscreen);
			Display.create();
		} catch (LWJGLException e) {
			Console.log("Failed to create display.", e, "Error");
		}

		Display.setResizable(false);

		// basic GL calls
		glEnable(GL_TEXTURE_2D);
		glDisable(GL_DEPTH_TEST); // 2d engine so no depth buffer needed

		// blending, for transparent sprite rendering
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		// set up a orthographic projection
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, 0, height, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		while (renderUpdate()); // set to currently update the display
	}
	
	/**
	 * Performs a rendering update.
	 * 
	 * @return Whether or not the next rendering update is available (based on
	 *         if the user prompted a display close event). If false, then the
	 *         next render is prevented and the display (along with the rest of
	 *         the program) quits.
	 */

	public static boolean renderUpdate () {

		glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // clear screen with transparent
		// black
		// set viewport to display dimension
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		Display.update();

		// testing display update
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		//render all in-game elements that are visible
		
		
		return !Display.isCloseRequested();
	}
	
	/**
	 * Draws an entity to the display.
	 * @param entity
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
	 * Changes the state of the window by toggling its fullscreen component.
	 * @param fullscreen Is true for fullscreen; false for window mode.
	 */
	public static void updateFullscreen(boolean fullscreen) {
		try {
			Display.setFullscreen(fullscreen);
		} catch (Exception e) {}
	}
	
	/**
	 * Updates the display geometry in pixels. Any zero values are unrecognized.
	 * @param pixelWidth The width of the display.
	 * @param pixelHeight The height of the display.
	 */
	public static void resize(int pixelWidth, int pixelHeight) {
		//TODO: enable more robust resizing than simply changing display height
		width = pixelWidth == 0 ? width : pixelWidth;
		height = pixelHeight == 0 ? height : pixelHeight;
	}
}