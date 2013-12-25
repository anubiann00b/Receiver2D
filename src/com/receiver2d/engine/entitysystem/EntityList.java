package com.receiver2d.engine.entitysystem;

import com.receiver2d.engine.Scene;

import java.util.ArrayList;

/**
 * The default manager for all of the game's worldly entities.
 */
public class EntityList extends ArrayList<Entity> {
	private static final long serialVersionUID = -5861454509544255719L;
	
	protected Scene scene;
	
	// TODO: add Entity familial (parent-child) accounting in here

	/**
	 * Creates a new, blank list of entities.
	 */
	public EntityList() {}

	/**
	 * Creates a new list based on an array of entities.
	 * @param entities An array of entities.
	 */
	public EntityList(Entity[] entities) {
		for (Entity entity : entities)
			add(entity);
	}

	// Entity() overrides
	/**
	 * Creates a new list with a single Entity.
	 * @param entity The Entity to add.
	 */
	public EntityList(Entity entity) {
		add(entity);
	}

	/**
	 * Sets the given EntityList for the given Scene.
	 * @param scene The Scene to modify.
	 * @param elist The EntityList to set as default for the scene.
	 */
	public static void setScene(Scene scene, EntityList elist) {
		(elist.scene = scene).setEntityList(elist);
	}
	
	/**
	 * Sets the current EntityList (instantiated) to be the default one for the
	 * given Scene.
	 * @param scene The Scene to modify.
	 */
	public void setScene(Scene scene) {
		(this.scene = scene).setEntityList(this);
	}
}