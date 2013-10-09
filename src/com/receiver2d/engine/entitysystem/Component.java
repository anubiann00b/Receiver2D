package com.receiver2d.engine.entitysystem;

/**
 * Anything that is able to be attached to an Entity, such as a Collider,
 * Rigidbody, et cetera, that provides additional functionality in-game. This
 * class should be extended
 */
public class Component {
	Entity entity; // the entity this component is attached to

	public void tick() {
		// each tick do this
	}
}
