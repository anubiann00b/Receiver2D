package com.receiver2d.engine.entitysystem;

import java.util.ArrayList;
import java.util.UUID;

import com.receiver2d.engine.Vector2D;

public class Entity {
	private String uuid;
	public Vector2D position, dimensions;
	public ArrayList<Component> components;
	public Entity parent = null;

	public Entity() {
		/**
		 * Creates a new Entity (in-game object) and initializes the component
		 * list for that entity.
		 */
		uuid = UUID.randomUUID().toString();
		components = new ArrayList<Component>();
	}

	/**
	 * Add a component to the list of components for this entity.
	 * 
	 * @param comp
	 *            the component to add
	 */
	void attachComponent(Component comp) {
		comp.entity = this;
		components.add(comp);
	}

	/**
	 * Creates an entity who's parent is the calling instance
	 * 
	 * @return the child entity
	 */
	Entity createChildEntity() {
		Entity child = new Entity();
		child.parent = this;
		return child;
	}

	public String getUuid() {
		return uuid;
	}
}
