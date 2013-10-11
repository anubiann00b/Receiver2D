package com.receiver2d.engine;

import java.util.ArrayList;

/**
 * Holds all of the game information for a particular world, including scenes,
 * entities, and more low-level stuff such as resources (loaded dynamically when
 * transitioning to new worlds).
 */
public class World {
	public ArrayList<Scene>	scenes;
	public String			name;

	public World(String name) {
		this.name = name;
	}
}
