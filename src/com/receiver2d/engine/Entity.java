package com.receiver2d.engine;

import java.util.ArrayList;
import java.util.UUID;

public class Entity {
	private String uuid;
	Vector2D position, dimension;
	ArrayList<Component> components;

	public Entity() {
		uuid = UUID.randomUUID().toString();
	}
}
