package com.receiver2d.engine.entitysystem;

import java.util.ArrayList;
import java.util.UUID;

import com.receiver2d.engine.Transform2D;
import com.receiver2d.engine.Vector2D;
import com.receiver2d.engine.physics.Polygon;

/**
 * Contains information regarding an in-game Entity. Any object instantiated in
 * the game is, in its most basic form, an entity with certain capabilities.
 */
public class Entity extends Transform2D {
	private UUID uuid = null;

	/**
	 * The components attached to the entity.
	 */
	public ArrayList<Component> components = null;

	/**
	 * A parent entity to which the entity is attached to.
	 */
	public Entity parent = null;

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
	public Polygon mesh = null;
	
	/**
	 * Creates a new Entity (in-game object) and initializes the component list
	 * for that entity.
	 */
	public Entity(String entityName) {
		uuid = UUID.randomUUID();
		components = new ArrayList<Component>();
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
	 * @param child
	 *            The child to make as an entity.
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
}
