package com.receiver2d.engine.entitysystem.components;

import com.receiver2d.engine.entitysystem.Component;
import com.receiver2d.engine.geometry.Polygon;

/**
 * A built-in component enabling entities that attach it to implement collision detection with other objects. This should be idealistically used in conjunction with Rigidbody components to provide a realistic simulation.
 */
public class Collider extends Component {
	public Polygon mesh;
}
