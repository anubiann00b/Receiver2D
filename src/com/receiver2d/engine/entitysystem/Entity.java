package com.receiver2d.engine.entitysystem;

import java.util.ArrayList;
import java.util.UUID;

import com.receiver2d.engine.Vector2D;

/**
 * Contains information regarding an in-game Entity. Any object instantiated in the game is, in its most basic form, an entity with certain capabilities.
 * 
 * @author princeton
 * 
 */
public class Entity {
	private String uuid;
	/**
	 * The position of the object in two-dimensional space.
	 */
	public Vector2D position; // we do not consider dimensions, which come from colliders/polygons
	/**
	 * The components attached to the entity.
	 */
	public ArrayList<Component> components;
	/**
	 * A parent entity to which the entity is attached to.
	 */
	public Entity parent = null;
	/**
	 * The name of the entity.
	 */
	public String name;

	/**
	 * Creates a new Entity (in-game object) and initializes the component list for that entity.
	 */
	public Entity(String entityName) {
		uuid = UUID.randomUUID().toString();
		components = new ArrayList<Component>();
		name = entityName;
	}

	// Entity() overrides
	public Entity() {
		uuid = UUID.randomUUID().toString();
		components = new ArrayList<Component>();
	}

	/**
	 * Add a component to the list of components for this entity.
	 * 
	 * @param comp
	 *            The component to add.
	 */
	public void attachComponent(Component comp) {
		components.add(comp);
		comp.entity = this;
	}

	/**
	 * Creates an entity whose parent is the calling instance.
	 * 
	 * @return The child entity.
	 */
	public Entity createChildEntity() {
		Entity child = new Entity();
		child.parent = this;
		return child;
	}

	/**
	 * Returns a unique ID for this entity.
	 * 
	 * @return A string containing the UUID.
	 */
	public String getUuid() {
		return uuid;
	}
}
