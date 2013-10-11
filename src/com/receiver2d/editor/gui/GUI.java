package com.receiver2d.editor.gui;

import com.receiver2d.engine.Vector2D;

/**
 * Deals with all elements and components of the user interface drawn by the 
 * display.
 */
// TODO: break away from ENGINE DEPENDENCY
public abstract class GUI {
	/**
	 * The position of the GUI element in the display.
	 */
	public Vector2D position;

	/**
	 * Creates a new GUI element and assigns a position to it.
	 * @param x The x-coordinate of the element.
	 * @param y The y-coordinate of the element.
	 */
	public GUI(float x, float y) {
		position = new Vector2D(x, y);
	}
}
