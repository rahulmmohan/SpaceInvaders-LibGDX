package com.overrideandroid.spacegame.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class AliensBullet {
	private Vector2 position;
	private int width, height;
	Aliens alien;
	private Rectangle boundingRectangle;
	public AliensBullet(Aliens alien, int gameHeight) {

		this.alien = alien;
		position = new Vector2(alien.getX() + 7, alien.getY()-3);
		boundingRectangle=new Rectangle();
	}

	public void update() {
		position.y++;
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
