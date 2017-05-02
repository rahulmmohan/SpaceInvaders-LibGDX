package com.overrideandroid.spacegame.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.overrideandroid.spacegame.gameobjects.Bullet;
import com.overrideandroid.spacegame.gameobjects.GameController;
import com.overrideandroid.spacegame.gameobjects.SpaceShip;
import com.overrideandroid.spacegame.world.GameRenderer;

public class InputHandler implements InputProcessor {
	private SpaceShip mybird;
	float screenWidth = Gdx.graphics.getWidth();
	float screenHeight = Gdx.graphics.getHeight();
	float gameWidth = 136;
	float sx, sy;
	private Bullet bullet;
	boolean left, right, drag;
	private float gameHeight;
	private boolean hold = false;;
	private int screenX;
	private int screenY;
	private int midPointX, midPointY;
	public static boolean fire = false;
	OrthographicCamera cam;

	public InputHandler(SpaceShip bird, float gameHeight, float gameWidth,
			int midPointY) {
		mybird = bird;
		this.midPointY = midPointY;
		midPointX = (int) (gameWidth / 2);
		left = right = drag = false;
		this.gameHeight = gameHeight;
		this.gameWidth = gameWidth;
		cam = new OrthographicCamera();
		cam.setToOrtho(true, gameWidth, gameHeight);
	}

	public Vector3 unprojectCoords(Vector3 coords) {
		cam.unproject(coords);
		return coords;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// mybird.onClick();
		Vector3 coords = new Vector3(screenX, screenY, 0);
		Vector3 coords2 = unprojectCoords(coords);
		this.screenX = (int) coords2.x;
		this.screenY = (int) coords2.y;

		if (!hold) {
			hold = true;
			new Thread(new Runnable() {
				public void run() {
					try {
						while (hold) {
							checkPress();
							Thread.sleep(100);
						}

					} catch (Exception ie) {
						ie.printStackTrace();
					}
				}
			}).start();

		}
		return false;
	}

	void checkPress() {
		if (isLeftButton()) {
			mybird.setPosition(true, false);
			
		} else if (isRightButton()) {
			mybird.setPosition(false, true);
			
		}

	}

	private boolean isLeftButton() {

		int x = 0;
		int y = (int) (gameHeight - 17);

		if ((screenX > x && screenX < (x + 15))
				&& (screenY > y && screenY < (y + 17))) {
			return true;
		}
		return false;
	}

	private boolean isRightButton() {

		int x = 20;
		int y = (int) (gameHeight - 17);

		if ((screenX > x && screenX < (x + 15))
				&& (screenY > y && screenY < (y + 17))) {
			return true;
		}
		return false;
	}

	private boolean isFireButton() {

		int x = (int) (gameWidth - 15);
		int y = (int) (gameHeight - 15);

		if ((screenX > x && screenX < (x + 25))
				&& (screenY > y && screenY < (y + 25))) {
			return true;
		}
		return false;
	}

	private boolean isPlayButton() {

		int x = midPointX - 20;
		int y = midPointY + 35;

		if ((screenX > x && screenX < (x + 40))
				&& (screenY > y && screenY < (y + 15))) {
			return true;
		}
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		GameRenderer.k = 1;
		hold = false;
		Vector3 coords = new Vector3(screenX, screenY, 0);
		Vector3 coords2 = unprojectCoords(coords);
		this.screenX = (int) coords2.x;
		this.screenY = (int) coords2.y;
		
		if (isFireButton() && GameController.isGamePlay()) {
			fire = true;
		} else if (GameController.isMenu() || GameController.isGameOver()) {
			if (isPlayButton()) {
				GameController.nextWave();
				if (GameController.score > AssetLoader.getHighScore()) {
					AssetLoader.setHighScore(GameController.score);
				}

				GameController.score = 0;
			}
		}

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 coords = new Vector3(screenX, screenY, 0);
		Vector3 coords2 = unprojectCoords(coords);
		this.screenX = (int) coords2.x;
		this.screenY = (int) coords2.y;
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
