package com.ac1dtest.gameoflife.model;

import java.util.ArrayList;

public class GameModel {
	private final int WIDTH;
	private final int HEIGHT;

	private ArrayList<GameObject> currentState;

	private boolean gameOver;
	private boolean inited = false;

	private Life life;

	public GameModel(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
	}

	public void createGame(String type) {
		//инициализация начальных параметров
		gameOver = false;
		inited = true;

		life = new Life(type, WIDTH, HEIGHT);

		currentState = life.dump();
	}

	public void onTurn() {
		life.newGeneration();

		if (!life.isAlive()) {
			gameOver = true;
			inited = false;
		}

		currentState = life.dump();
	}

	public ArrayList<GameObject> getCurrentState() {
		return currentState;
	}

	public int getGeneration() {
		return life.getGeneration();
	}

	public boolean isInited() {
		return inited;
	}

	public boolean isGameOver() {
		return gameOver;
	}
}
