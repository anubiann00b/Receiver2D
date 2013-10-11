package com.receiver2d.engine;

import java.util.ArrayList;

/**
 * Holds all of the game information for a particular world, including scenes,
 * entities, and more low-level stuff such as resources (loaded dynamically when
 * transitioning to new worlds).
 */
public class World {
<<<<<<< HEAD
	public ArrayList<Scene> scenes;
	private String name;
	
	/* --- Resources --- */
	private ArrayList<String> resourcePaths; //saves memory
	/* --- Resources --- */
=======
	public ArrayList<Scene>	scenes;
	public String			name;

>>>>>>> a6addbb7667eba966feb50978b72e3c4af3ce8d9
	public World(String name) {
		this.name = name;
	}
}
