package com.receiver2d.engine.graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.util.Color;

import com.receiver2d.engine.io.R2DResource;

/**
 * A Texture2D is a general-purpose texture useful for entities, skyboxes,
 * and anything else that may need a texture. In contrast to an Entity, a 
 * Texture2D augments anything that is being rendered by specifying an
 * additional color or image data to be drawn on by the Renderer, as specified.
 */

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
	 * Creates a new Texture2D from dimensional and color values. The choice of
	 * floating-point dimensions rather than integer values is useful for 
	 * rounding stored results from geometric calculations, rather than losing
	 * this information by saving it as an (int).
	 * 
	 * @param width The _rendered_ width of the texture.
	 * @param height The _rendered_ height of the texture.
	 * @param r The red component of the background color [0,255].
	 * @param g The green component of the background color [0,255].
	 * @param b The blue component of the background color [0,255].
	 * @param a The alpha component of the background color [0,255].
	 */
	public Texture2D(float width, float height, byte r, byte g, byte b, byte a) {
		this(width, height, new Color(r, g, b, a));
	}
	
	/**
	 * Creates a new Texture2D from dimensional and color values. The choice of
	 * floating-point dimensions rather than integer values is useful for 
	 * rounding stored results from geometric calculations, rather than losing
	 * this information by saving it as an (int).
	 * 
	 * @param width The _rendered_ width of the texture.
	 * @param height The _rendered_ height of the texture.
	 * @param color A color object (r, g, b, a) rendered as the background.
	 */
	public Texture2D(float width, float height, Color color) {
		this.width = width;
		this.height = height;
		bgColor = color;
		image = new BufferedImage((int)(width+.5), (int)(height+.5), 
				BufferedImage.TYPE_INT_ARGB);
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
	public Texture2D(BufferedImage i, byte r, byte g, byte b, byte a) 
			throws IOException {
		this(i, new Color(r, g, b, a));
	}
	
	/**
	 * Creates a new Texture2D from a BufferedImage and a Color.
	 * @param i The BufferedImage to use.
	 * @param color The background color of the texture to render.
	 */
	public Texture2D(BufferedImage i, Color color) {
		image = new BufferedImage(i.getWidth(), i.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		
		// draw input to image
		Graphics2D g = image.createGraphics();
		g.drawImage(i, 0, 0, null);
		g.dispose();
		
		bgColor = color != null ? color : new Color(0, 0, 0, 0);
		width = image.getWidth();
		height = image.getHeight();
	}
	
	/**
	 * Creates a texture from a resource.
	 * @param res The resource to use.
	 */
	public Texture2D(R2DResource res, Color color) throws IOException {
		this(ImageIO.read(res.getFile()), color);
	}
	
	public Texture2D(R2DResource res, byte r, byte g, byte b, byte a) 
			throws IOException{
		this(ImageIO.read(res.getFile()), new Color(r, g, b, a));
	}

	/**
	 * Sets the current BufferedImage of the texture.
	 * @param i A buffered image.
	 */
	public void setImage(BufferedImage i) {
		image.flush();
		
		// draw input to image
		Graphics2D g = image.createGraphics();
		g.drawImage(i, 0, 0, null);
		g.dispose(); // clear resources
		image = i;
		
		width = image.getWidth(); // set default dimensions
		height = image.getHeight();
	}
	
	/**
	 * The rendered image applies the dimensions of the texture to the original
	 * static image, and returns the "stretched" output.
	 * @return A rendered version of the image.
	 */
	public Image getRendered() {
		return image != null ? image.getScaledInstance((int)(width+.5), 
					(int)(height+.5), Image.SCALE_SMOOTH) : null;
	}
}
