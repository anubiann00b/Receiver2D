package com.receiver2d.engine;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Handles the appearance of things on-screen and draws them.
 */
public class DisplayHandler {
	public static void init() {
		try { // create display and set res
			Display.create();
			Display.setDisplayMode(new DisplayMode(1024, 768));
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		Display.setResizable(true);
		
		glDisable(GL_DEPTH_TEST); // 2d engine so no depth buffer needed

		// blending, for transparent sprite rendering
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // clear screen with transparent black

		// set viewport to display dimension
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
	}
}