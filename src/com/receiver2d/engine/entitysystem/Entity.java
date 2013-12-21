package com.receiver2d.engine.entitysystem;

import java.util.HashMap;
import java.util.UUID;

import com.receiver2d.engine.Transform2D;
import com.receiver2d.engine.Vector2D;
import com.receiver2d.engine.graphics.Texture2D;
import com.receiver2d.engine.physics.Polygon;

/**
 * Contains information regarding an in-game Entity. Any object instantiated in
 * the game is, in its most basic form, an entity with certain capabilities.
 */
public class Entity extends Transform2D {
	private UUID uuid;

	/**
	 * The components attached to the entity.
	 */
	private HashMap<String, Component> components;

	/**
	 * A parent entity to which the entity is attached to.
	 */
	public Entity parent;

	/**
	 * The name of the entity.
	 */
	public String name;

	/**
	 * Whether or not the entity is visible. If not, then it will not be
	 * rendered in the next draw call.
	 */
	public boolean visible = true;

	/**
	 * The mesh of the entity. Specifies the morphology as an instance of a
	 * Polygon.
	 */
	public Polygon mesh;
	
	/**
	 * The texture of the entity. Specifies the visual morphology as an instance
	 * of a Texture.
	 */
	public Texture2D texture;
	
	/**
	 * Creates a new Entity (in-game object) and initializes the component list
	 * for that entity.
	 */
	public Entity(String entityName) {
		uuid = UUID.randomUUID();
		components = new HashMap<String, Component>();
		name = entityName;
		mesh = new Polygon(0f,0f , 0f,10f , 10f,10f , 10f,0f);
		position = Vector2D.ZERO;
		rotation = 0f;
	}

	/**
	 * Creates a new Entity (in-game object) and initializes the component list
	 * for that entity.
	 */
	public Entity() {
		this("Untitled ");
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
	 * Sets an entity to be a child of this entity.
	 * 
	 * @param child The child to make as an entity.
	 * @return The same entity, but now as a child of the main entity.
	 */
	public void setChildEntity(Entity child) {
		child.parent = this;
	}

	/**
	 * Sets the entity to be a child of the given entity, parent.
	 * 
	 * @param parentEntity
	 *            The parent entity to be a child of.
	 */
	public void attachToParent(Entity parentEntity) {
		this.parent = parentEntity;
	}

	/**
	 * Returns a unique ID for this entity.
	 * 
	 * @return A string containing the UUID.
	 */
	public UUID getUuid() {
		return uuid;
	}
	
	/**
	 * Adds a component to the Component list.
	 * @param name The component name type.
	 * @param comp The component to add.
	 */
	public void attachComponent(Component comp) {
		String name = comp.getClass().getSimpleName();
		// a component must be removed the standard way first
		if (components.containsKey(name)) return;
		comp.entity = this;
		components.put(name, comp);
	}
	
	/**
	 * Gets the current Component attached to the Entity.
	 * @param name The current Component attached to the Entity.
	 * @return The Component matching the specified name, or null otherwise.
	 */
	public Component getComponent(String name) {
		return components.get(name);
	}
	
	/**
	 * Removes a Component from the Entity.
	 * @param name The Component's name type.
	 * @return The removed Component, or null if the Component did not exist.
	 */
	public Component removeComponent(String name) {
		if (!components.containsKey(name)) return null;
		Component c = components.remove(name);
		c.entity = null;
		return c;
	}
	
	/**
	 * @return All of the current Component items attached to the entity.
	 */
	public HashMap<String, Component> getComponents() {
		return components;
	}
}
