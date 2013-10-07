package com.receiver2d.engine.entitysystem;

import java.util.ArrayList;
import java.util.UUID;

import com.receiver2d.engine.*;

public class Entity extends Transform2D {
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
