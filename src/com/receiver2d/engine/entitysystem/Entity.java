package com.receiver2d.engine.entitysystem;

import java.util.ArrayList;
import java.util.UUID;

import com.receiver2d.engine.Vector2D;

public class Entity {
<<<<<<< HEAD
	private String uuid;
	public Vector2D position, dimensions;
	public ArrayList<Component> components;
	public Entity parent = null;
	public String name;
=======
	private String				uuid;
	public Vector2D				position, dimensions;
	public ArrayList<Component>	components;
	public Entity				parent	= null;
>>>>>>> a6addbb7667eba966feb50978b72e3c4af3ce8d9

	/**
	 * Creates a new Entity (in-game object) and initializes the component list
	 * for that entity.
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
	 * @return the child entity
	 */
	public Entity createChildEntity() {
		Entity child = new Entity();
		child.parent = this;
		return child;
	}

	public String getUuid() {
		return uuid;
	}
}
