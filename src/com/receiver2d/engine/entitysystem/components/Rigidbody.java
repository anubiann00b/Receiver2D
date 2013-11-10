package com.receiver2d.engine.entitysystem.components;

import com.receiver2d.engine.Vector2D;
import com.receiver2d.engine.entitysystem.Component;

/**
 * A built-in component that enables physics support for all entities that use it.
 */
public class Rigidbody extends Component {
	/* -- physics values --- */
	/**
	 * The instantaneous velocity of the rigidbody.
	 */
	public Vector2D velocity;
	/**
	 * The center of mass relative to the transform's origin.
	 */
	public Vector2D centerOfMass;
	/**
	 * The mass of the rigidbody. Setting this to 0 effectively disables nearly all capabilities of the rigidbody, (save for gravitational acceleration).
	 */
	public float mass;

	/* -- physics values --- */

	/**
	 * Creates a new Rigidbody.
	 */
	public Rigidbody() {
		velocity = new Vector2D(0.0f, 0.0f);
		centerOfMass = new Vector2D(0.0f, 0.0f);
		mass = 1.0f;
	}
}
