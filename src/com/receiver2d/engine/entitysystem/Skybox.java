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
	 * Creates a new Skybox from a given texture.
	 * @param tex The rendered texture to the Skybox in the game.
	 */
	public Skybox(Texture2D tex) {
		texture = tex;
		distance = 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public Image getSkyboxRendered() {
		return null;
	}
}
