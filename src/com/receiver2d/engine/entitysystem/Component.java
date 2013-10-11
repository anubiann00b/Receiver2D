package com.receiver2d.engine.entitysystem;

/**
 * Anything that is able to be attached to an Entity, such as a Collider,
 * Rigidbody, et cetera, that provides additional functionality in-game. This
 * class must be extended by all components.
 */
public abstract class Component {
	/**
	 * The current entity that the component is attached to.
	 */
	public Entity	entity	= null; // the entity this component is attached to

	/**
	 * The main tick function that deals with updates to the logical aspect of
	 * the component. This is called by the engine's central tick manager.
	 */
	public void tick() {

	}
}
