package com.receiver2d.engine.entitysystem.components;

import java.awt.image.BufferedImage;

import org.lwjgl.util.Color;

/**
 * A Texture2D allows an Entity to change its appearance without changing its
 * morphology. 
 */

// TODO: multi-texture support
public class Texture2D {
	/**
	 * The background color of the texture, which is rendered with first
	 * priority.
	 */
	public Color background;

	private BufferedImage image;
	
	/**
	 * Creates a new Texture2D.
	 */
	public Texture2D() {
		
	}
	
}
