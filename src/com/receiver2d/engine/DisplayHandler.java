package com.receiver2d.engine;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.receiver2d.engine.graphics.Renderer;

/**
 * Handles the appearance of things on-screen and draws them.
 */
public class DisplayHandler {
	private static int width = 0;
	private static int height = 0;
	private static boolean hasInitialized = false;

	/**
	 * Initiates the display, which is responsible for all visual output of rendering.
	 * 
	 * @param title
	 *            The title of the window.
	 * @param fullscreen
	 *            Whether or not the window starts off full-screen.
	 * @param width
	 *            The width of the window, in pixels.
	 * @param height
	 *            The height of the window, in pixels.
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
			Console.log("Created LWJGL test window");
		} catch (LWJGLException e) {
			Console.error("Failed to create display.", e);
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

		while (Renderer.update()); // set to currently update the display
	}

	/**
	 * Changes the state of the window by toggling its fullscreen component.
	 * 
	 * @param fullscreen
	 *            Is true for fullscreen; false for window mode.
	 */
	public static void updateFullscreen(boolean fullscreen) {
		try {
			Display.setFullscreen(fullscreen);
		} catch (Exception e) {}
	}

	/**
	 * Updates the display geometry in pixels. Any zero values are unrecognized.
	 * 
	 * @param pixelWidth
	 *            The width of the display.
	 * @param pixelHeight
	 *            The height of the display.
	 */
	public static void resize(int pixelWidth, int pixelHeight) {
		// TODO: enable more robust resizing than simply changing display height
		width = pixelWidth == 0 ? width : pixelWidth;
		height = pixelHeight == 0 ? height : pixelHeight;
	}
}