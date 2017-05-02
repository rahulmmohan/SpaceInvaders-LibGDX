package com.overrideandroid.spacegame.gameobjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ufo {
	private Vector2 position;
	private int width, height;
	SpaceShip plane;
	private Rectangle boundingRectangle;
	float x, y;
	Animation texture;
	private int gameHeight;
	private float gameWidth;
	float d;

	public Ufo(float x, float y, int gameHeight, float gameWidth, float d) {
		this.x = x;
		this.y = y;
		this.gameHeight = gameHeight;
		this.gameWidth=gameWidth;
		this.d=d;
		
		position = new Vector2( x, y);
		boundingRectangle = new Rectangle();
	}

	public float update() {
		
			
			position.x += d;
		
		 boundingRectangle.set(position.x, position.y, 7, 5);
		 return position.x;
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

	
	
	public boolean collides_bullet(Bullet bullet) {
        if (position.x +40> bullet.getX()) {
            return (Intersector.overlaps(bullet.getbounding(), this.boundingRectangle));
        }
        return false;
    }
}
