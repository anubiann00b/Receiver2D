package com.receiver2d.engine;

import com.receiver2d.engine.entitysystem.EntityList;

import java.util.HashMap;
import java.util.UUID;

/**
 * A particular scene within a world, containing a list of entities and other
 * attributes.
 */
@SuppressWarnings("unused")
public class Scene {
	private String uuid;
	private String name = "";
	private EntityList entityList;

	/* --- Scene variables --- */
	private HashMap<String, Object> values;
	/* --- Scene variables --- */

	/**
	 * Loads a scene into memory and gives it a name.
	 *
	 * @param name The name of the scene.
	 */
	public Scene (String name) {
		uuid = UUID.randomUUID().toString();
		this.name = name;

		// initializers
		values = new HashMap<String, Object>();
	}

	/**
	 * Gets the current name of the scene.
	 *
	 * @return The name of the scene.
	 */
	public String getName () {
		return name;
	}

	/**
	 * Sets the current entity list of the scene to whatever is provided.
	 *
	 * @param entityList An EntityList, containing entities.
	 */
	public void setEntityList (EntityList entityList) {
		this.entityList = entityList;
	}

	/**
	 * Sets a variable for a particular scene.
	 *
	 * @param variable The name of the variable.
	 * @param value    The value of the variable.
	 */
	public void setValue (String variable, Object value) {
		values.put(variable, value);
	}

	/**
	 * Gets a specific scene-based variable. Very useful for having variables
	 * that only correspond to one type of scene in a game.
	 *
	 * @param variable The name of the stored variable.
	 * @return A found value, or null if no variable can be found.
	 */
	public Object getValue (String variable) {
		return values.get(variable);
	}
}
