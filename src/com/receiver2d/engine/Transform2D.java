package com.receiver2d.engine;

/**
 * This abstract class deals with all entities/objects that are translatable in
 * two-dimensional space. Anything that does not abstract from Transform2D is
 * unable to move in-game.
 */
public abstract class Transform2D {
	/**
	 * The position of the object in two-dimensional space.
	 */
	public Vector2D position = new Vector2D(0.0f, 0.0f);
	/**
	 * We only have one axis of rotation to keep a two-dimensional view, and it
	 * is perpendicular to the x, y axes.
	 */
	public float rotation;

	/**
	 * Take the current rotation of the entity and apply a new rotation to it,
	 * therefore making its final rotation a sum of the original and the
	 * specified rotation value, in degrees.
	 */
	public void rotate(float degrees) {
		rotation = (rotation+degrees) % 360;
	}
}
