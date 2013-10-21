package com.receiver2d.engine;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Handles the appearance of things on-screen and draws them.
 */
public class DisplayHandler {
	public static int width = 800;
	public static int height = 600;

	public static void init(String title, boolean fullscreen, int width, int height) {
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

	public static boolean renderUpdate() {
		// does GL calls

		glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // clear screen with transparent
												// black
		// set viewport to display dimension
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		Display.update();

		// testing display update
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		// set the color of the quad
		glColor3f(0.05f, 0.05f, 1.0f);
		/*
		 * glBegin(GL_QUADS); glVertex2f(100, 100); glVertex2f(100+200, 100); glVertex2f(100+200, 100+200); glVertex2f(100, 100+200); glEnd();
		 */

		return !Display.isCloseRequested();
	}

	public static void updateFullscreen(boolean fullscreen) {
		try {
			Display.setFullscreen(fullscreen);
		} catch (Exception e) {}
	}
}