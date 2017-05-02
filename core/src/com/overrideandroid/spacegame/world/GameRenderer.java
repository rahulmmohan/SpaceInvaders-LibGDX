package com.overrideandroid.spacegame.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.overrideandroid.spacegame.gameobjects.Aliens;
import com.overrideandroid.spacegame.gameobjects.AliensBullet;
import com.overrideandroid.spacegame.gameobjects.Bullet;
import com.overrideandroid.spacegame.gameobjects.Bunker;
import com.overrideandroid.spacegame.gameobjects.GameController;
import com.overrideandroid.spacegame.gameobjects.SpaceShip;
import com.overrideandroid.spacegame.gameobjects.Ufo;
import com.overrideandroid.spacegame.helpers.AssetLoader;
import com.overrideandroid.spacegame.helpers.InputHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameRenderer {
	private GameWorld myWorld;
	private static OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private static SpriteBatch batcher;
	public static int i = 0, j = 0, k = 1;

	ArrayList<Bullet> bullets;
	Iterator<Bullet> bulletIterator;

	ArrayList<Aliens> aliens;
	Iterator<Aliens> aliensIterator;

	ArrayList<AliensBullet> AliensBullet;
	Iterator<AliensBullet> AliensBulletterator;

	ArrayList<Bunker> bunkers;
	Iterator<Bunker> bunkerIterator;

	ArrayList<Ufo> Ufos;
	Iterator<Ufo> UfoIterator;

	Aliens alien;
	Bullet b;
	AliensBullet alienbullet;
	Bunker bunker;
	Ufo Ufo;
	boolean ship_exploded = false;
	boolean can_fire = true;
	public static int score = 0;
	Random r;
	private int gameHeight;
	// Game Objects
	private SpaceShip plane;
	// Game Assets
	private TextureRegion[] bgs1;
	private TextureRegion bullet,  ship;
	private float gameWidth;
	Animation[] aliensAnim;
	Animation aliensAnim2, aliensAnim3;
	public TextureRegion[] bunker1;
	public TextureRegion[] bunker2;
	public TextureRegion[] bunker3;
	public TextureRegion[] bunker4;
	public TextureRegion[] bunker5;
	private TextureRegion ufo;
	float runtimeold,explosion_time=4;
	private TextureRegion life;
	private TextureRegion explosion;
	private int midPointX;
	TextureRegion ctrl;
	private TextureRegion fire;

	public GameRenderer(GameWorld gameworld, int gameHeight, int midPointY,
			float gameWidth) {
		r = new Random();
		myWorld = gameworld;
		this.gameHeight = gameHeight;
		this.gameWidth = gameWidth;
		midPointX = (int) (gameWidth / 2);
		cam = new OrthographicCamera();
		cam.setToOrtho(true, gameWidth, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		bullets = new ArrayList<Bullet>();
		aliens = new ArrayList<Aliens>();
		AliensBullet = new ArrayList<AliensBullet>();
		bunkers = new ArrayList<Bunker>();
		Ufos = new ArrayList<Ufo>();

		initGamePlay();
		

	}

	private void initGamePlay() {
		GameController.initGamePlay();
		initGameObjects();
		initAssets();
		bullets.clear();
		aliens.clear();
		AliensBullet.clear();
		bunkers.clear();
		Ufos.clear();
		initAliens();
		initBunkers();
		ship_exploded=false;
		explosion_time=4;
		can_fire=true;

	}

	private void initBunkers() {
		int X = (int) ((gameWidth / 2) - 8);
		int Y = gameHeight - 45;
		for (int i = 0; i < 3; i++) {
			if (i == 1) {
				X = (int) ((gameWidth / 4) - 16);
			} else if (i == 2) {
				X = (int) ((gameWidth / 2) + (gameWidth / 4));
			}
			bunkers.add(new Bunker(X, Y, gameHeight, gameWidth, bunker5));
			bunkers.add(new Bunker(X + 4, Y, gameHeight, gameWidth, bunker1));
			bunkers.add(new Bunker(X + 8, Y, gameHeight, gameWidth, bunker1));
			bunkers.add(new Bunker(X + 12, Y, gameHeight, gameWidth, bunker4));
			bunkers.add(new Bunker(X, Y + 4, gameHeight, gameWidth, bunker2));
			bunkers.add(new Bunker(X + 12, Y + 4, gameHeight, gameWidth,
					bunker3));

		}

	}

	private void initAliens() {
		float X = GameController.X;
		float Y = GameController.Y;
		int j = 2;

		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 11; x++) {
				aliens.add(new Aliens(X + x * 5, Y + y * 5, gameHeight,
						gameWidth, aliensAnim[j], (j * 10) + 10));

			}
			if (y == 0 || y == 2) {
				j--;
			}
		}

	}

	public void render(float runTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.begin();

		batcher.enableBlending();
		drawBackground();
		if(ship_exploded)
		{
			//batcher.draw(plane_shdw, plane.getX() + 3, plane.getY() + 3, 60, 40);
			
			batcher.draw(explosion, plane.getX(), plane.getY(), plane.getWidth(),
				plane.getHeight());
			explosion_time-=0.05;
			if(explosion_time<0)
			{
				ship_exploded=false;
				explosion_time=4;
			}
		
		}
		else
		{
			batcher.draw(ship, plane.getX(), plane.getY(), plane.getWidth(),
					plane.getHeight());
		}
		/*
		 * shapeRenderer.begin(ShapeType.FilledRectangle);
		 * shapeRenderer.setColor(Color.RED);
		 * shapeRenderer.filledRect(plane.getbounding().x,
		 * plane.getbounding().y,30,30); shapeRenderer.end();
		 */
		if (InputHandler.fire && can_fire) {
			InputHandler.fire = false;
			can_fire = false;
			bullets.add(new Bullet(plane, gameHeight));
			AssetLoader.shoot.play();

		}

		drawControls();
		drawScoreAndLifes();
		itrateAliens(runTime);
		itrateBullets(runTime);
		itrateAliensBullets(runTime);
		itrateBunkers(runTime);
		itrateUfo(runTime);
		batcher.end();

		/*
		 * for (aliensIterator = aliens.iterator(); aliensIterator.hasNext();) {
		 * alien = aliensIterator.next();
		 * shapeRenderer.begin(ShapeType.FilledRectangle);
		 * shapeRenderer.setColor(Color.RED);
		 * shapeRenderer.filledRect(alien.getbounding().x,
		 * alien.getbounding().y,5,5); shapeRenderer.end(); }
		 */
	}

	private void drawScoreAndLifes() {
		int score =GameController.score;
		AssetLoader.font.draw(batcher, score + "", 3, 0);

		for (int i = 0; i < GameController.life; i++) {
			batcher.draw(life, gameWidth - ((3 - i) * 5), 3, 5, 5);
		}

	}

	private void itrateBunkers(float runTime) {
		for (bunkerIterator = bunkers.iterator(); bunkerIterator.hasNext();) {
			bunker = bunkerIterator.next();
			batcher.draw(bunker.getTexture(), bunker.getX(), bunker.getY(), 4,
					4);
			for (bulletIterator = bullets.iterator(); bulletIterator.hasNext();) {
				b = bulletIterator.next();
				if (bunker.collides_bullet(b)) {
					can_fire = true;
					bulletIterator.remove();
					if (bunker.update() == 3) {
						bunkerIterator.remove();
					}
					break;
				}
			}
			for (AliensBulletterator = AliensBullet.iterator(); AliensBulletterator
					.hasNext();) {
				alienbullet = AliensBulletterator.next();
				if (bunker.collides_Alien_bullet(alienbullet)) {
					AliensBulletterator.remove();
					if (bunker.update() == 3) {
						bunkerIterator.remove();
					}
					break;
				}
			}
		}

	}

	private void itrateAliensBullets(float runTime) {
		for (AliensBulletterator = AliensBullet.iterator(); AliensBulletterator
				.hasNext();) {
			alienbullet = AliensBulletterator.next();
			alienbullet.update();
			batcher.draw(bullet, alienbullet.getX(), alienbullet.getY(), 3, 3);

			if (plane.collides_bullet(alienbullet)) {
				AssetLoader.ship_dead.play();
				ship_exploded=true;
				AliensBulletterator.remove();
				if (GameController.life-- == 1) {
					
					initGamePlay();
					GameController.gameOver();
				}
				break;
			}

			if (alienbullet.getY() > gameHeight) {
				AliensBulletterator.remove();
			}

		}

	}

	private void drawControls() {

		batcher.draw(ctrl, 1, gameHeight - 15, 30, 15);
		batcher.draw(fire, gameWidth-15, gameHeight - 15, 13, 13);

	}

	private void itrateBullets(float runTime) {
		for (bulletIterator = bullets.iterator(); bulletIterator.hasNext();) {
			b = bulletIterator.next();
			b.update();
			batcher.draw(bullet, b.getX(), b.getY(), 5, 5);

			for (aliensIterator = aliens.iterator(); aliensIterator.hasNext();) {

				alien = aliensIterator.next();
				if (alien.collides_bullet(b)) {
					can_fire = true;
					aliensIterator.remove();
					bulletIterator.remove();
					AssetLoader.aliens_dead.play();
					GameController.score +=  alien.getScore();
					
					if (aliens.size() % 5 == 0) {
						GameController.updateSpeed();
						
						
					}
					if (aliens.size() % 10 == 0) {

						if (GameController.isToLeft()) {
							Ufos.add(new Ufo(0, 3, gameHeight, gameWidth,
									(float) 0.5));
						} else {

							Ufos.add(new Ufo(gameWidth, 3, gameHeight,
									gameWidth, (float) -0.5));
						}

					}
					break;
				}

			}
			if (aliens.isEmpty()) {
				initGamePlay();
				GameController.nextWave();
				
			}

			if (b.getY() < 0) {
				can_fire = true;
				bulletIterator.remove();

			}

		}

	}

	private void itrateAliens(float runTime) {
		for (aliensIterator = aliens.iterator(); aliensIterator.hasNext();) {

			alien = aliensIterator.next();
			if (!ship_exploded) {
				alien.update();
			}
			batcher.draw((TextureRegion) alien.getTexture().getKeyFrame(runTime), alien.getX(),
					alien.getY(), 4, 4);
			if(alien.getY()>gameHeight - 35)
			{
				initGamePlay();
				GameController.gameOver();
				break;
			}

		}
		if (GameController.aliensGoingtoFire) {
			GameController.aliensGoingtoFire = false;
			alien = aliens.get(r.nextInt(aliens.size()));
			AliensBullet.add(new AliensBullet(alien, gameHeight));
		}

	}

	private void itrateUfo(float runTime) {
		for (UfoIterator = Ufos.iterator(); UfoIterator.hasNext();) {
			Ufo = UfoIterator.next();
			float x = Ufo.update();
			batcher.draw(ufo, Ufo.getX(), Ufo.getY(), 10, 5);
			if (x < 0 || x > gameWidth) {
				UfoIterator.remove();
			}
			if ((runTime - runtimeold) >= 0.5) {
				AssetLoader.ufo_sound.play();
				runtimeold = runTime;
			}
			for (bulletIterator = bullets.iterator(); bulletIterator.hasNext();) {
				b = bulletIterator.next();
				if (Ufo.collides_bullet(b)) {
					GameController.score += (r.nextInt(5) + 1)* 100;				
					UfoIterator.remove();
					break;
				}
			}
		}

	}

	private void initGameObjects() {
		plane = myWorld.getPlane();

	}

	private void initAssets() {
		bullet = AssetLoader.bullet;
		bgs1 = AssetLoader.bgs1;
		ship = AssetLoader.ship;
		ctrl = AssetLoader.ctrl;
		fire=AssetLoader.fire;
		aliensAnim = AssetLoader.alienAnim;

		bunker1 = AssetLoader.bunker1;
		bunker2 = AssetLoader.bunker2;
		bunker3 = AssetLoader.bunker3;
		bunker4 = AssetLoader.bunker4;
		bunker5 = AssetLoader.bunker5;

		ufo = AssetLoader.ufo;
		life = AssetLoader.life;
		explosion=AssetLoader.explosion;
	}

	private void drawBackground() {
		batcher.draw(bgs1[(GameController.wave-1) % 6], 0, 0, gameWidth,
				gameHeight);

	}

}
