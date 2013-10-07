package com.receiver2d.engine;

public class Vector2D {
	public float x = 0.0f;
	public float y = 0.0f;
	
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D add(float x, float y) {
		this.x += x;
		this.y += y;
		return new Vector2D(this.x, this.y);
	}
	//add() overrides
		public Vector2D add(Vector2D vector) {
			return add(vector.x, vector.y);
		}
		public Vector2D add(int x, int y) {
			return add((float) x, (float) y);
		}
		public Vector2D add(double x, double y) {
			return add((float) x, (float) y);
		}
	
	public Vector2D multiply(float x, float y) {
		this.x *= x;
		this.y *= y;
		return new Vector2D(this.x, this.y);
	}
	//multiply() overrides
		public Vector2D multiply(Vector2D vector) {
			return multiply(vector.x, vector.y);
		}
		public Vector2D multiply(int x, int y) {
			return multiply((float) x, (float) y);
		}
		public Vector2D multiply(double x, double y) {
			return multiply((float) x, (float) y);
		}
	
	public Vector2D divide(float x, float y) {
		this.x /= x;
		this.y /= y;
		return new Vector2D(this.x, this.y);
	}
	//divide() overrides
		public Vector2D divide(Vector2D vector) {
			return divide(vector.x, vector.y);
		}
		public Vector2D divide(int x, int y) {
			return divide((float) x, (float) y);
		}
		public Vector2D divide(double x, double y) {
			return divide((float) x, (float) y);
		}
	
	public float distanceTo(float x, float y) {
		return (float) Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
	}
	//distanceTo() overrides
		public float distanceTo(int x, int y) {
			return distanceTo((float) x, (float) y);
		}
		public float distanceTo(double x, double y) {
			return distanceTo((float) x, (float) y);
		}
		public float distanceTo(Vector2D vector) {
			return distanceTo(vector.x, vector.y);
		}
}