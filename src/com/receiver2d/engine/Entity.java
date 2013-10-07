package com.receiver2d.engine;

import java.util.ArrayList;
import java.util.UUID;

public class Entity {
	private String uuid;
	public Vector2D position, dimensions;
	public ArrayList<Component> components;

	public Entity() {
		/**
		 * Creates a new Entity (in-game object) and initializes the component
		 * list for that entity.
		 */
		uuid = UUID.randomUUID().toString();
		components = new ArrayList<Component>();
	}
}
