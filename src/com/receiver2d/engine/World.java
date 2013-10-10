package com.receiver2d.engine;

import java.util.*;

/**
 * Holds all of the game information for a particular world, including scenes,
 * entities, and more low-level stuff such as resources (loaded dynamically
 * when transitioning to new worlds).
 */
public class World {
	public ArrayList<Scene> scenes;
	private String name;
	
	/* --- Resources --- */
	
	/* --- Resources --- */
	public World(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
