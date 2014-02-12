package com.receiver2d.engine.entitysystem;

import java.awt.Image;

import com.receiver2d.engine.graphics.Texture2D;

public class Skybox {
	/**
	 * The background color of the Skybox, to be rendered.
	 */
	protected Texture2D texture;
	
	public float distance;
	
	/**
	 * Creates a new Skybox from a given texture at a given "distance" from the
	 * camera's main view.
	 * @param tex
	 * @param distance
	 */
	public Skybox(Texture2D tex, float distance) {
		texture = tex;
		this.distance = distance;
	}
	
	/**
	 * Creates a new Skybox from a given texture.
	 * @param tex The rendered texture to the Skybox in the game.
	 */
	public Skybox(Texture2D tex) {
		texture = tex;
		distance = 0;
	}
	
	/**
	 * Returns a rendered image of the Skybox, accounting for distance and other
	 * factors.
	 * @return A rendered image.
	 */
	public Image getSkyboxRendered() {
		return texture.getRendered(); // TODO: implement distance rendering
	}
}
