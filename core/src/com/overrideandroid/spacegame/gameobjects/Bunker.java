package com.overrideandroid.spacegame.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bunker {
	private Vector2 position;
	private int width, height;
	SpaceShip plane;
	private Rectangle boundingRectangle;
	float x, y;
	private int gameHeight;
	private float gameWidth;
	TextureRegion[] texture;
	private int k;

	public Bunker(float x, float y, int gameHeight, float gameWidth, TextureRegion[] texture) {
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.gameHeight = gameHeight;
		this.gameWidth=gameWidth;
		
		position = new Vector2(x,y);
		boundingRectangle = new Rectangle();
		boundingRectangle.set(position.x, position.y, 5, 5);
		k=0;
	}

	public int update() {
		k++;
		return k;
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

	public TextureRegion getTexture() {
		return texture[k];

	}
	
	public boolean collides_bullet(Bullet bullet) {
        if (position.x +40> bullet.getX()) {
            return (Intersector.overlaps(bullet.getbounding(), this.boundingRectangle));
        }
        return false;
    }

	public boolean collides_Alien_bullet(AliensBullet alienbullet) {
		// TODO Auto-generated method stub
		 if (position.x +40> alienbullet.getX()) {
	            return (Intersector.overlaps(alienbullet.getbounding(), this.boundingRectangle));
	        }
		return false;
	}
}
