package com.overrideandroid.spacegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.overrideandroid.spacegame.gameobjects.GameController;
import com.overrideandroid.spacegame.helpers.AssetLoader;
import com.overrideandroid.spacegame.helpers.InputHandler;
import com.overrideandroid.spacegame.world.GameMenuRenderer;
import com.overrideandroid.spacegame.world.GameOverRenderer;
import com.overrideandroid.spacegame.world.GameRenderer;
import com.overrideandroid.spacegame.world.GameWorld;
import com.overrideandroid.spacegame.world.ToWaveRenderer;

public class GameScreen implements Screen {

	private GameWorld gameworld;
	private GameRenderer renderer;
	GameMenuRenderer gamemenu = null;
	ToWaveRenderer towave = null;
	GameOverRenderer gameover = null;
	private float runTime = 0;
	private int gameHeight;
	private float gameWidth;
	private int midPointY;
	public static boolean playing_back_song;

	public GameScreen() {
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		gameHeight = 136;
		gameWidth = screenWidth / (screenHeight / gameHeight);

		midPointY = (int) (gameWidth / 2);

		gameworld = new GameWorld(midPointY, (int) gameHeight, (int) gameWidth);

		Gdx.input.setInputProcessor(new InputHandler(gameworld.getPlane(),
				gameHeight, gameWidth, midPointY));
		GameController.initGameState();
		playing_back_song = false;
	}

	@Override
	public void render(float delta) {
		if (GameController.isMenu()) {
			if (gamemenu == null) {
				gamemenu = new GameMenuRenderer(gameworld, (int) gameHeight,
						midPointY, gameWidth);
			}
			gamemenu.render(delta);
		} else if (GameController.isTowave()) {
			AssetLoader.ship_to_wave.play();
			if (towave == null) {
				towave = new ToWaveRenderer(gameworld, (int) gameHeight,
						midPointY, gameWidth);
			}
			towave.render(delta);

		} else if (GameController.isGamePlay()) {
			if (renderer == null) {
				renderer = new GameRenderer(gameworld, (int) gameHeight,
						midPointY, gameWidth);
			}
			runTime += delta;
			gameworld.update(delta);
			renderer.render(runTime);
			if (!playing_back_song) {
				playing_back_song = true;
				System.out.println("fd");
				new Thread(new Runnable() {
					public void run() {
						try {
							while (GameController.isGamePlay()) {
								AssetLoader.back_score.play();
								Thread.sleep(GameController.back_score_intervel);
							}

						} catch (Exception ie) {
							ie.printStackTrace();
						}
					}
				}).start();

			}
		} else if (GameController.isGameOver()) {
			if (gameover == null) {
				gameover = new GameOverRenderer(gameworld, (int) gameHeight,
						midPointY, gameWidth);
			}

			gameover.render(runTime);
		}
	}

	@Override
	public void resize(int width, int height) {
		System.out.println("GameScreen - resizing");
	}

	@Override
	public void show() {
		System.out.println("GameScreen - show called");
	}

	@Override
	public void hide() {
		System.out.println("GameScreen - hide called");
	}

	@Override
	public void pause() {
		System.out.println("GameScreen - pause called");
	}

	@Override
	public void resume() {
		System.out.println("GameScreen - resume called");
	}

	@Override
	public void dispose() {
		// Leave blank
	}

}
