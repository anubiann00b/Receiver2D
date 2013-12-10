package com.receiver2d.engine.entitysystem.components;

import java.awt.image.BufferedImage;

import org.lwjgl.util.Color;

import com.receiver2d.engine.io.R2DResource;

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
	public Color bgColor;

	/**
	 * The image information stored in the texture.
	 */
	private BufferedImage image;
	
	/**
	 * The _rendered_ width of the texture on the Entity.
	 */
	private float width;
	
	/**
	 * The _rendered_ height of the texture.
	 */
	private float height;
	
	/**
	 * Creates a new Texture2D.
	 */
	public Texture2D() {
		bgColor = new Color(0, 0, 0, 0); // black transparent
		width = 0;
		height = 0;
		image = new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
		// image type is _not_ premultiplied with alpha value
	}
	
	/**
	 * Creates a new Texture2D from a BufferedImage and color values.
	 * 
	 * @param i The BufferedImage to use.
	 * @param r A red value (0 through 255).
	 * @param g A green value (0 through 255).
	 * @param b A blue value (0 through 255).
	 * @param a An alpha value (0 through 255), a 255 being completely opaque 
	 * 			and a value of 0 being completely transparent.
	 */
	public Texture2D(BufferedImage i, byte r, byte g, byte b, byte a) {
		image = i;
		bgColor = new Color(r, g, b, a);
		width = i.getWidth();
		height = i.getHeight();
	}
	
	/**
	 * Creates a new Texture2D from a BufferedImage and a Color.
	 * @param i The BufferedImage to use.
	 * @param color The background color of the texture to render.
	 */
	public Texture2D(BufferedImage i, Color color) {
		image = i;
		bgColor = color;
		width = i.getWidth();
		height = i.getHeight();
	}
	
	/**
	 * Creates a texture from a resource.
	 * @param res The resource to use.
	 */
	public Texture2D(R2DResource res) {
		// TODO: create Texture2D from R2DResource
	}
}
