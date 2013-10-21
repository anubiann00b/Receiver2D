package com.receiver2d.engine;

import java.util.ArrayList;

/**
 * Holds all of the game information for a particular world, including scenes, entities, and more low-level stuff such as resources (loaded dynamically when transitioning to new worlds).
 */
public class World {
	/**
	 * A list of scenes contained by the current world.
	 */
	public ArrayList<Scene> scenes;
	public String name;

	/* --- Resources --- */
	private ArrayList<String> resourcePaths; // saves memory

	/* --- Resources --- */

	/**
	 * Loads a world into memory and gives it a particular name.
	 *
	 * @param name The name of the world.
	 */
	public World (String name) {
		this.name = name;
	}
}
