package com.overrideandroid.spacegame.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	public static Texture background_texture, plane_texture, texture;

	public static TextureRegion[] bgs1 = new TextureRegion[6];
	public static TextureRegion ship, ctrl, play, fire;
	public static Animation blasting;
	public static Animation alienAnim[] = new Animation[3];
	public static TextureRegion bullet, ufo, life, explosion, menu_back;
	public static BitmapFont font, font2;

	public static TextureRegion[] bunker1 = new TextureRegion[3];
	public static TextureRegion[] bunker2 = new TextureRegion[3];
	public static TextureRegion[] bunker3 = new TextureRegion[3];
	public static TextureRegion[] bunker4 = new TextureRegion[3];
	public static TextureRegion[] bunker5 = new TextureRegion[3];

	public static Sound ship_to_wave, shoot, aliens_dead, ship_dead, ufo_sound,
			back_score;

	private static Preferences prefs;

	public static void load() {
		loadAssets();
		loadSounds();
		loadBunkers();
		loadBackgrounds();
		loadPreference();

		plane_texture = new Texture(Gdx.files.internal("data/fire.png"));
		plane_texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		bullet = new TextureRegion(plane_texture, 0, 0, 32, 32);
		bullet.flip(false, true);

		font = new BitmapFont(Gdx.files.internal("data/font16.fnt"), false);
		//font.setScale(.35f, -.35f);
		font2 = new BitmapFont(Gdx.files.internal("data/text.fnt"), false);
		//font2.setScale(.15f, -.15f);

	}

	private static void loadPreference() {
		// Create (or retrieve existing) preferences file
		prefs = Gdx.app.getPreferences("space_invaders");

		if (!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}

	}

	public static void setHighScore(int val) {
		prefs.putInteger("highScore", val);
		prefs.flush();
	}

	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}

	private static void loadBackgrounds() {
		for (int i = 0; i < 6; i++) {
			background_texture = new Texture(
					Gdx.files.internal("background/back" + i + ".png"));
			background_texture.setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);
			bgs1[i] = new TextureRegion(background_texture, 0, 0, 64, 128);
			bgs1[i].flip(false, true);
		}

	}

	private static void loadBunkers() {
		for (int i = 0; i < 3; i++) {
			texture = new Texture(Gdx.files.internal("Bunkers/BunkerA-" + i
					+ ".png"));
			texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			bunker1[i] = new TextureRegion(texture, 0, 0, 16, 16);
			bunker1[i].flip(false, true);
		}
		for (int i = 0; i < 3; i++) {
			texture = new Texture(Gdx.files.internal("Bunkers/BunkerB-" + i
					+ ".png"));
			texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			bunker2[i] = new TextureRegion(texture, 0, 0, 16, 16);
			bunker2[i].flip(false, true);
		}
		for (int i = 0; i < 3; i++) {
			texture = new Texture(Gdx.files.internal("Bunkers/BunkerC-" + i
					+ ".png"));
			texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			bunker3[i] = new TextureRegion(texture, 0, 0, 16, 16);
			bunker3[i].flip(false, true);
		}
		for (int i = 0; i < 3; i++) {
			texture = new Texture(Gdx.files.internal("Bunkers/BunkerD-" + i
					+ ".png"));
			texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			bunker4[i] = new TextureRegion(texture, 0, 0, 16, 16);
			bunker4[i].flip(false, true);
		}
		for (int i = 0; i < 3; i++) {
			texture = new Texture(Gdx.files.internal("Bunkers/BunkerE-" + i
					+ ".png"));
			texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			bunker5[i] = new TextureRegion(texture, 0, 0, 16, 16);
			bunker5[i].flip(false, true);
		}

	}

	private static void loadSounds() {
		ship_to_wave = Gdx.audio.newSound(Gdx.files
				.internal("sounds/ship_to_wave.wav"));
		shoot = Gdx.audio.newSound(Gdx.files.internal("sounds/shoot.wav"));
		aliens_dead = Gdx.audio.newSound(Gdx.files
				.internal("sounds/aliens_dead.wav"));
		ufo_sound = Gdx.audio.newSound(Gdx.files.internal("sounds/ufo.wav"));
		ship_dead = Gdx.audio.newSound(Gdx.files
				.internal("sounds/ship_dead.wav"));
		back_score = Gdx.audio.newSound(Gdx.files
				.internal("sounds/back_score.wav"));

	}

	private static void loadAssets() {

		TextureRegion[] aliens;
		for (int i = 0; i < 3; i++) {
			aliens = new TextureRegion[2];
			texture = new Texture(Gdx.files.internal("aliens/alien" + (i + 1)
					+ "a.png"));
			texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			aliens[0] = new TextureRegion(texture, 0, 0, 64, 64);
			aliens[0].flip(false, true);

			texture = new Texture(Gdx.files.internal("aliens/alien" + (i + 1)
					+ "b.png"));
			texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			aliens[1] = new TextureRegion(texture, 0, 0, 64, 64);
			aliens[1].flip(false, true);

			alienAnim[i] = new Animation(0.9f, aliens);
			alienAnim[i].setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		}

		texture = new Texture(Gdx.files.internal("ship/Spaceship.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		ship = new TextureRegion(texture, 0, 0, 64, 64);
		ship.flip(true, true);

		texture = new Texture(Gdx.files.internal("controls/left-right.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		ctrl = new TextureRegion(texture, 0, 0, 128, 64);
		ctrl.flip(false, false);

		texture = new Texture(Gdx.files.internal("controls/fire.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		fire = new TextureRegion(texture, 0, 0, 64, 64);
		fire.flip(false, false);

		texture = new Texture(Gdx.files.internal("controls/play.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		play = new TextureRegion(texture, 0, 0, 128, 64);
		play.flip(false, true);

		texture = new Texture(Gdx.files.internal("aliens/ufo.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		ufo = new TextureRegion(texture, 0, 0, 64, 64);
		ufo.flip(false, true);

		texture = new Texture(Gdx.files.internal("ship/life.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		life = new TextureRegion(texture, 0, 0, 64, 64);
		life.flip(false, true);

		texture = new Texture(Gdx.files.internal("ship/Explosion.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		explosion = new TextureRegion(texture, 0, 0, 32, 32);
		explosion.flip(false, true);

		texture = new Texture(Gdx.files.internal("background/menu_back.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		menu_back = new TextureRegion(texture, 0, 0, 128, 256);
		menu_back.flip(false, true);

	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		background_texture.dispose();
		plane_texture.dispose();
	}
}
