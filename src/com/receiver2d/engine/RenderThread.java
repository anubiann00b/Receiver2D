package com.receiver2d.engine;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 * Is responsible for everything that is "seen," that is, everything that
 * involves OpenGL draw calls. At run time, this class is instantiated into
 * Receiver2D.renderer, and thus is accessible from there.
 * 
 * LWJGL is double buffered. Display.update() flips the buffer we draw to and
 * the visible one
 */
public class RenderThread implements Runnable {
	@Override
	public void run() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT); // clear screen each frame
		Display.update();
		Display.sync(Receiver2D.fps);
	}
}