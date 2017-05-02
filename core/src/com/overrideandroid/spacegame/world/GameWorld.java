package com.overrideandroid.spacegame.world;

import com.overrideandroid.spacegame.gameobjects.SpaceShip;

public class GameWorld {

	private SpaceShip plane;

	int gameWidth;

	public GameWorld(int midPointY, int gameHeight, int gameWidth) {
		this.gameWidth = gameWidth;
		plane = new SpaceShip(gameWidth, (gameWidth / 2) - 7, gameHeight - 30,
				15, 15);

	}

	public void update(float delta) {
		plane.update(delta);

	}
	
	public SpaceShip getPlane(){
		return plane;
	}
}
