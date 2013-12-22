package com.receiver2d.engine.entitysystem;

import com.receiver2d.engine.graphics.Texture2D;

public class Skybox {
	/**
	 * The background color of the Skybox, to be rendered.
	 */
	public Texture2D texture;
	
	/**
	 * Creates a new Skybox from a given texture.
	 * @param tex The rendered texture to the Skybox in the game.
	 */
	public Skybox(Texture2D tex) {
		texture = tex;
	}
	
}
