package com.receiver2d.engine;

import java.util.*;
import com.receiver2d.engine.io.R2DResource;

/**
 * Holds all of the game information for a particular world, including scenes, 
 * entities, and more low-level stuff such as resources (loaded dynamically when
 * transitioning to new worlds).
 */
public class World {
	private final String uuid;
	/**
	 * A list of scenes contained by the current world.
	 */
	public ArrayList<Scene> scenes;
	public final String name;

	private ArrayList<R2DResource> resources;

	/**
	 * Loads a world into memory and gives it a particular name.
	 * 
	 * @param name The name of the world.
	 */
	public World(String name) {
		uuid = UUID.randomUUID().toString();
		this.name = name;
		scenes = new ArrayList<Scene>();
		resources = new ArrayList<R2DResource>();
	}

	public World() {
		this(UUID.randomUUID().toString());
	}

	/**
	 * Adds a scene to the world.
	 * 
	 * @param scene
	 *            The scene to add.
	 */
	public void addScene(Scene scene) {
		scenes.add(scene);
	}

	/**
	 * Takes n scenes and adds them to the world.
	 * 
	 * @param scenes
	 *            An array of scenes to add.
	 */
	public void addScenes(Scene[] scenes) {
		for (Scene s : scenes)
			this.scenes.add(s);
	}

	/**
	 * Adds a resource to be loaded when the world is loaded. This differs from
	 * adding the resource to a particular scene, which would only be loaded
	 * when that scene is loaded.
	 * 
	 * @param res The resource to add.
	 */
	public void addResource(R2DResource res) {
		resources.add(res);
	}

	/**
	 * Gets the currently loaded/unloaded resources in the world, but not in an 
	 * individual scene.
	 * 
	 * @return The list of resources.
	 */
	public ArrayList<R2DResource> getResources() {
		return resources;
	}

	/**
	 * Gets the unique user identifier for this world.
	 * 
	 * @return A representation of this unique identifier.
	 */
	public String getUUID() {
		return uuid;
	}
}
