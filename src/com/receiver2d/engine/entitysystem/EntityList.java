package com.receiver2d.engine.entitysystem;

import java.util.ArrayList;

import com.receiver2d.engine.*;

/**
 * The default manager for all of the game's worldly entities.
 */
public class EntityList extends ArrayList<Entity> {
	public EntityList(Entity[] entities) {
		for (Entity entity : entities) 
			add(entity); //add all entities in-game
	}
	// Entity() overrides
		public EntityList(Entity entity) {
			add(entity);
		}
	public static void setScene(Scene scene, EntityList elist) {
		scene.setEntityList(elist);
	}
}