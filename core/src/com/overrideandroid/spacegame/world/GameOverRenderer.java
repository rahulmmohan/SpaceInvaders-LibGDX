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

public class GameOverRenderer {
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
	private TextureRegion ship,play;
	private float gameWidth;
	private int midPointY,midPointX;
	private TextureRegion back;

	public GameOverRenderer(GameWorld gameworld, int gameHeight, int midPointY, float gameWidth) {
		r = new Random();
		myWorld = gameworld;
		this.gameHeight = gameHeight;
		this.gameWidth=gameWidth;
		this.midPointY = midPointY;
		midPointX = (int) (gameWidth/2);
		
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
		
		
		
	}

	
	
	public void render(float runTime) {
		score=GameController.score;
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.begin();
		batcher.enableBlending();
		drawBackground();
		String txt="Game Over";
		if(score>AssetLoader.getHighScore())
		{
			txt="High Score";
		}
	
		String highScore = ""+score;
		AssetLoader.font2.draw(batcher, txt,
				midPointX - (txt.length()*3), midPointY);
		// Draw text
		AssetLoader.font.draw(batcher, highScore,
				midPointX - (highScore.length()*2), midPointY+20);
		batcher.draw(play, midPointX-13, midPointY+35,30, 10);
		batcher.draw(ship, midPointX-7, gameHeight-50, plane.getWidth(),plane.getHeight());
		batcher.end();	
	}

	private void drawBackground() {
		batcher.draw(back,0, 0, gameWidth, gameHeight);
		
	}

	private void initGameObjects() {
		plane = myWorld.getPlane();
		

	}

	private void initAssets() {
		
		ship=AssetLoader.ship;
		play=AssetLoader.play;
		back=AssetLoader.menu_back;
	}



}
