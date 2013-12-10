package com.receiver2d.engine.entitysystem;

import com.receiver2d.engine.entitysystem.components.Texture2D;

public class Skybox {
	/**
	 * The background color of the Skybox, to be rendered.
	 */
	public Texture2D texture;
	
	public Skybox() {
		texture = new Texture2D();
	}
}
