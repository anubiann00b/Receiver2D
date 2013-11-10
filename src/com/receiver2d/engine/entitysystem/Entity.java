package com.receiver2d.engine.entitysystem;

import java.util.ArrayList;
import java.util.UUID;

import com.receiver2d.engine.Vector2D;
import com.receiver2d.engine.physics.Polygon;

/**
 * Contains information regarding an in-game Entity. Any object instantiated in the game is, in its most basic form, an entity with certain capabilities.
 */
public class Entity {
	private String uuid = null;
	/**
	 * The position of the object in two-dimensional space.
	 */
	public Vector2D position = null; // we do not consider dimensions, which
										// come from colliders/polygons
	
	public float rotation;
	
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
	 * Whether or not the entity is visible. If not, then it will not be rendered in the next draw call.
	 */
	public boolean visible = true;

	/**
	 * The mesh of the entity. Specifies the morphology as an instance of a Polygon.
	 */
	public Polygon mesh = null;

	/**
	 * Creates a new Entity (in-game object) and initializes the component list for that entity.
	 */
	public Entity(String entityName) {
		uuid = UUID.randomUUID().toString();
		components = new ArrayList<Component>();
		name = entityName;
		mesh = new Polygon(new float[]{ 0f,0f , 0f,10f , 10f,10f , 10f,0f });
		position = new Vector2D(0f, 0f);
	}
	
	/**
	 * Creates a new Entity (in-game object) and initializes the component list
	 * for that entity.
	 */
	public Entity() {
		this("");
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
	public Entity setChildEntity(Entity child) {
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
