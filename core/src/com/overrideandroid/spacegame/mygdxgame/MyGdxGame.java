package com.overrideandroid.spacegame.mygdxgame;

import com.badlogic.gdx.Game;
import com.overrideandroid.spacegame.helpers.AssetLoader;
import com.overrideandroid.spacegame.screens.GameScreen;

public class MyGdxGame extends Game {

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

}
 