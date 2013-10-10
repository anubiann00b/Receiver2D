package com.receiver2d.engine;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class DisplayHandler {
	public static void init() {
		try { // create display and set res
			Display.create();
			Display.setDisplayMode(new DisplayMode(1024, 768));
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		glDisable(GL_DEPTH_TEST); // 2d engine so no depth buffer needed

		// blending, for transparent sprite rendering
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // clear screen with transparent black

		// set viewport to display dimension
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
	}
}
