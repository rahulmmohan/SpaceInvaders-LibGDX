package com.overrideandroid.spacegame.gameobjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Aliens {
	private Vector2 position;
	private int width, height;
	SpaceShip plane;
	private Rectangle boundingRectangle;
	float x, y;
	Animation texture;
	private int gameHeight;
	private float gameWidth;
	int score;

	public Aliens(float x, float y, int gameHeight, float gameWidth, Animation aliensAnim, int score) {
		this.x = x;
		this.y = y;
		this.texture = aliensAnim;
		this.gameHeight = gameHeight;
		this.gameWidth=gameWidth;
		this.score=score;
		
		position = new Vector2(GameController.X + x, GameController.Y + y);
		boundingRectangle = new Rectangle();
	}

	public void update() {
		if (GameController.isToLeft()) {
			position.x -= GameController.speed;
			position.y = GameController.Y + y;
		} else {
			position.x += GameController.speed;
			position.y = GameController.Y + y;
		}
		if (position.x > gameWidth-4) {
			GameController.setMovement(true);
			GameController.Y+=1;
			
		} else if (position.x <1) {
			GameController.setMovement(false);
			GameController.Y+=1;
		}

		 boundingRectangle.set(position.x, position.y, 5, 5);
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

	public Animation getTexture() {
		return texture;

	}
	
	public boolean collides_bullet(Bullet bullet) {
        if (position.x +40> bullet.getX()) {
            return (Intersector.overlaps(bullet.getbounding(), this.boundingRectangle));
        }
        return false;
    }

	public int getScore() {
		
		return score;
	}
}
