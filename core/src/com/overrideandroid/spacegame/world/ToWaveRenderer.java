package com.overrideandroid.spacegame.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.overrideandroid.spacegame.gameobjects.Aliens;
import com.overrideandroid.spacegame.gameobjects.Bullet;
import com.overrideandroid.spacegame.gameobjects.GameController;
import com.overrideandroid.spacegame.gameobjects.SpaceShip;
import com.overrideandroid.spacegame.helpers.AssetLoader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ToWaveRenderer {
	private GameWorld myWorld;
	private static OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private static SpriteBatch batcher;
	public static int i = 0, j = 0, k = 0;

	ArrayList<Bullet> bullets;
	Iterator<Bullet> bulletIterator;

	ArrayList<Aliens> aliens;
	Iterator<Aliens> aliensIterator;
	Aliens alien;
	Bullet b;
	public static int score = 0;
	Random r;
	private int gameHeight;
	// Game Objects
	private SpaceShip plane;
	// Game Assets
	private TextureRegion[] bgs1;
	private TextureRegion ship;
	private float gameWidth;
	private int midPointY, midPointX;
	float y;
	private boolean stopAtPosition = false;
	private String wave;
	private TextureRegion back;

	public ToWaveRenderer(GameWorld gameworld, int gameHeight, int midPointY,
			float gameWidth) {
		r = new Random();
		myWorld = gameworld;
		this.gameHeight = gameHeight;
		this.gameWidth = gameWidth;
		this.midPointY = midPointY;
		midPointX = (int) (gameWidth / 2);

		cam = new OrthographicCamera();
		cam.setToOrtho(true, gameWidth, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		// Call helper methods to initialize instance variables
		initGameObjects();
		initAssets();
		bullets = new ArrayList<Bullet>();
		aliens = new ArrayList<Aliens>();
		if (GameController.wave == 0) {
			y = gameHeight - 50;
		} else {
			y = plane.getY();
		}
		wave = "Wave : " + (GameController.wave);
	}

	public void render(float runTime) {
		y--;
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.begin();
		batcher.enableBlending();
		drawBackground();

		if (y < 0) {
			y = gameHeight;
			stopAtPosition = true;
			// GameController.wave++;

		}

		if (stopAtPosition && (y == plane.getY())) {
			stopAtPosition = false;
			GameController.gamePlay();

		}
		wave = "Wave : " + (GameController.wave);
		AssetLoader.font.draw(batcher, wave, midPointX - (wave.length() * 2),
				11);

		batcher.draw(ship, plane.getX(), y, plane.getWidth(), plane.getHeight());
		batcher.end();
	}

	private void initGameObjects() {
		plane = myWorld.getPlane();

	}

	private void initAssets() {
		bgs1 = AssetLoader.bgs1;
		ship = AssetLoader.ship;
		back = AssetLoader.menu_back;
	}

	private void drawBackground() {
		if (GameController.wave == 0) {
			batcher.draw(back, 0, 0, gameWidth, gameHeight);
		} else {
			batcher.draw(bgs1[(GameController.wave - 1) % 6], 0, 0, gameWidth,
					gameHeight);
		}

	}
}
