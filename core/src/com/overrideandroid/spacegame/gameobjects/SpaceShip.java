package com.overrideandroid.spacegame.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class SpaceShip {

	private Vector2 position, velocity, acceleration;
	private float rotation;
	private int width, height;
	private Rectangle boundingRectangle;
	int gameWidth;
	public SpaceShip(int gameWidth,float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		boundingRectangle=new Rectangle();
		this.gameWidth=gameWidth;
	}

	public void update(float delta) {
		boundingRectangle.set(position.x, position.y+5, 15, 10);
		
	}

	public boolean isFalling() {
		return velocity.y > 110;
	}

	public boolean shouldntFlap() {
		return velocity.y > 70;
	}

	public Rectangle getbounding() {
		return boundingRectangle;
	}
	public void setPosition(boolean left, boolean right) {

		if (left) {
			position.x-=3;
			if(position.x<1)
			{
				position.x=1;
			}
		}
		if(right)
		{
			position.x+=3;
			if(position.x>gameWidth-getWidth())
			{
				position.x=gameWidth-getWidth();
			}
		}
	}
	public boolean collides_bullet(AliensBullet bullet) {
        if (position.x +20> bullet.getX()) {
            return (Intersector.overlaps(bullet.getbounding(), this.boundingRectangle));
        }
        return false;
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

	public float getRotation() {
		return rotation;
	}

}
