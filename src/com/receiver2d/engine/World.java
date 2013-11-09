package com.receiver2d.engine;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Holds all of the game information for a particular world, including scenes, entities, and more low-level stuff such as resources (loaded dynamically when transitioning to new worlds).
 */
public class World {
	private String uuid;
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
	public World(String name) {
		uuid = UUID.randomUUID().toString();
		this.name = name;
		scenes = new ArrayList<Scene>();
		resourcePaths = new ArrayList<String>();
	}
	
	public World() {
		this(UUID.randomUUID().toString()); // generate unique name if not provided
	}

	/**
	 * Adds a resource path into the World. This differs from adding to an
	 * individual scene since the resource can remain in memory across multiple
	 * scenes, instead of being limited to use only for a particular scene.
	 * @param location
	 */
	public void addResourcePath(String location) {
		resourcePaths.add(location);
	}
	
	public void addScenes(Scene[] scenes) {
		for (Scene s : scenes)
			this.scenes.add(s);
	}
	
	/**
	 * Gets the unique user identifier for this world.
	 * @return A representation of this unique identifier.
	 */
	public String getUUID() {
		return uuid;
	}
}
