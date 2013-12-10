package com.receiver2d.engine.entitysystem;

/**
 * Anything that is able to be attached to an Entity, such as a Collider,
 * Rigidbody, CharacterController, et cetera, that provides additional
 * functionality in-game. This class must be extended by all components.
 */
public abstract class Component {
	/**
	 * Determines if a type is valid or not.
	 * @param t
	 * @return
	 */
	public static boolean isValidType(String t) {
		try {
			Class.forName("com.receiver2d.engine.entitysystem.components."+t);
			return true; // only works if we do not have a problem
		} catch (ClassNotFoundException e) { }
		
		return false; // by default
	}
	
	/**
	 * The current entity that the component is attached to.
	 */
	public Entity entity = null; // the entity this component is attached to

	/**
	 * The main tick function that deals with updates to the logical aspect of
	 * the component. This is called by the engine's central tick manager.
	 */
	public void tick() {

	}
}
