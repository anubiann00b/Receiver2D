package com.receiver2d.engine;

import org.lwjgl.opengl.Display;

/**
 * Is responsible for everything that is "seen," that is, everything that
 * involves OpenGL draw calls. At run time, this class is instantiated into
 * Receiver2D.renderer, and thus is accessible from there.
 */
public class RenderThread implements Runnable {
	@Override
	public void run() {
		Display.update();
	}
}