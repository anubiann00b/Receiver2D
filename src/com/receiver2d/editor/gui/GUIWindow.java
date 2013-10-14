package com.receiver2d.editor.gui;

import com.receiver2d.engine.Vector2D;

/**
 * A GUI element of the window type.
 */
//TODO: break away from ENGINE DEPENDENCY
public class GUIWindow extends GUI {
	/**
	 * The dimensions of the window.
	 */
	public Vector2D dimensions;
	public GUIWindow(float x, float y, float width, float height) {
		super(x, y);
	}

}
