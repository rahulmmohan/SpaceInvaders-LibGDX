package com.overrideandroid.spacegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.overrideandroid.spacegame.helpers.AssetLoader;
import com.overrideandroid.spacegame.screens.GameScreen;

public class SpaceInvadersGame extends Game {
	SpriteBatch batch;
	Texture img;

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new GameScreen());

	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();

	}
	
	/*@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}*/
}
