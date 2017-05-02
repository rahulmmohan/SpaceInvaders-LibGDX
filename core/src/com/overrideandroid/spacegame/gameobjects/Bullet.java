package com.overrideandroid.spacegame.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
	private Vector2 position;
	private int width, height;
	SpaceShip plane;
	private Rectangle boundingRectangle;
	public Bullet(SpaceShip plane, int gameHeight) {

		this.plane = plane;
		position = new Vector2(plane.getX() + 5, plane.getY()-3);
		boundingRectangle=new Rectangle();
	}

	public void update() {
		position.y-=2;
		boundingRectangle.set(position.x+2, position.y+2, 1, 1);
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	public Rectangle getbounding() {
		return boundingRectangle;
	}
}
