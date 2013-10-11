package com.receiver2d.engine;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Handles the appearance of things on-screen and draws them.
 */
public class DisplayHandler {
	public static void init(String title, boolean fullscreen, int width,
			int height) {
		try { // create display and set res
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setFullscreen(fullscreen);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		Display.setResizable(true);

		glEnable(GL_TEXTURE_2D);
		glDisable(GL_DEPTH_TEST); // 2d engine so no depth buffer needed

		// blending, for transparent sprite rendering
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // clear screen with transparent
												// black
		// set viewport to display dimension
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
	}
}