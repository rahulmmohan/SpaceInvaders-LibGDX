package com.overrideandroid.spacegame.gameobjects;

import com.overrideandroid.spacegame.screens.GameScreen;

public class GameController {
	public static float X = 5;
	public static float Y = 5;
	public static Movement MoveTo;
	public static GameState state;
	public static float speed;
	public static int wave = 0;
	public static int life = 3;
	public static int back_score_intervel=1500;
	public static int score;

	public static boolean aliensGoingtoFire = false;

	public enum Movement {
		LEFT, RIGHT
	}

	public enum GameState {
		MENU, TOWAVE, GAMEPLAY, GAMEOVER
	}

	public static void initGameState() {
		state = GameState.MENU;
		MoveTo = Movement.RIGHT;
		wave=0;
		life=3;
		score=0;
	}
	public static void initGamePlay() {
		speed = (float) 0.1;
		back_score_intervel=1500;
		X=5;
		Y=5;
		
	}
	

	public static void updateSpeed() {
		speed+=0.025;
		back_score_intervel-=100;
	}

	public static boolean isToLeft() {
		return MoveTo == Movement.LEFT;
	}

	public static boolean isToRight() {
		return MoveTo == Movement.RIGHT;
	}

	public static void setMovement(boolean toleft) {
		if (toleft) {
			MoveTo = Movement.LEFT;
			
		} else {
			MoveTo = Movement.RIGHT;
		}
		aliensGoingtoFire = true;
	}

	public static boolean isMenu() {
		return state == GameState.MENU;
	}

	public static boolean isTowave() {
		return state == GameState.TOWAVE;
	}

	public static boolean isGamePlay() {
		return state == GameState.GAMEPLAY;
	}
	public static boolean isGameOver() {
		return state == GameState.GAMEOVER;
	}

	public static void nextWave() {
		state = GameState.TOWAVE;
		
		wave++;
	}

	public static void Menu() {
		state = GameState.MENU;

	}

	public static void gamePlay() {
		state = GameState.GAMEPLAY;
		GameScreen.playing_back_song=false;

	}
	public static void gameOver() {
		state = GameState.GAMEOVER;
		wave=0;
		life=3;
		
		
	}
}
