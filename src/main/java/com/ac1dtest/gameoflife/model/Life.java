package com.ac1dtest.gameoflife.model;

import java.util.ArrayList;


public class Life {
	private int generationCounter;
	private final int width, height;

	private boolean[][] cells;
	private boolean[][] nextGen;
	private boolean alive;

	private final ArrayList<GameObject> life = new ArrayList<>();

	public Life(String type, int width, int height) {
		this.width = width;
		this.height = height;
		cells = new boolean[width][height];
		nextGen = new boolean[width][height];
		generationCounter = 1;
		alive = true;

		int pivotX = (width - 1) / 2;
		int pivotY = (height - 1) / 2;

		switch(type) {
			case "Glider":
				cells[pivotX][pivotY] = true;
				cells[pivotX][pivotY - 1] = true;
				cells[pivotX][pivotY - 2] = true;
				cells[pivotX - 1][pivotY] = true;
				cells[pivotX - 2][pivotY - 1] = true;
				break;
			case "Hives":
				cells[pivotX - 6][pivotY] = true;
				cells[pivotX - 5][pivotY - 1] = true;
				cells[pivotX - 4][pivotY - 1] = true;
				cells[pivotX - 3][pivotY] = true;
				cells[pivotX - 5][pivotY + 1] = true;
				cells[pivotX - 4][pivotY + 1] = true;

				cells[pivotX][pivotY - 6] = true;
				cells[pivotX][pivotY - 3] = true;
				cells[pivotX - 1][pivotY - 5] = true;
				cells[pivotX - 1][pivotY - 4] = true;
				cells[pivotX + 1][pivotY - 5] = true;
				cells[pivotX + 1][pivotY - 4] = true;

				cells[pivotX + 6][pivotY] = true;
				cells[pivotX + 5][pivotY - 1] = true;
				cells[pivotX + 4][pivotY - 1] = true;
				cells[pivotX + 5][pivotY + 1] = true;
				cells[pivotX + 4][pivotY + 1] = true;
				cells[pivotX + 3][pivotY] = true;

				cells[pivotX][pivotY + 6] = true;
				cells[pivotX][pivotY + 3] = true;
				cells[pivotX - 1][pivotY + 5] = true;
				cells[pivotX - 1][pivotY + 4] = true;
				cells[pivotX + 1][pivotY + 5] = true;
				cells[pivotX + 1][pivotY + 4] = true;
				break;
			case "Methuselah":
				cells[pivotX][pivotY] = true;
				cells[pivotX][pivotY - 1] = true;
				cells[pivotX][pivotY + 1] = true;
				cells[pivotX - 1][pivotY + 1] = true;
				cells[pivotX + 1][pivotY] = true;
				break;
			case "Random":
			default:
				for (int i = 0; i < 35; i++)
					cells[(int)(Math.random() * width)][(int)(Math.random() * width)] = true;
		}

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if (cells[i][j])
					life.add(new GameObject(i, j));
			}
		}
	}

	public void newGeneration() {
		int aliveNeighbors;
		boolean changed = false;
		life.clear();

		generationCounter++;

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				aliveNeighbors = 0;

				for (int xi = i - 1; xi <= i + 1; xi++) {
					for (int xj = j - 1; xj <= j + 1; xj++) {
						if (xi == i && xj == j)
							continue;

						int torI = emulateI(xi);
						int torJ = emulateJ(torI, xj);

						if (cells[torI][torJ])
							aliveNeighbors++;
					}
				}

				if (cells[i][j]) {
					if (aliveNeighbors == 3 || aliveNeighbors == 2) {
						nextGen[i][j] = true;
						life.add(new GameObject(i, j));
					}
					else
						changed = true;
				} else if (aliveNeighbors == 3) {
					nextGen[i][j] = true;
					life.add(new GameObject(i, j));
					changed = true;
				}
			}
		}

		cells = nextGen;
		nextGen = new boolean[width][height];

		alive = changed && !life.isEmpty();
	}

	private int emulateI(int index) {
		int result = index;

		if (index < 0)
			result = cells.length - 1;
		else if (index > cells.length - 1)
			result = 0;

		return result;
	}

	private int emulateJ(int i, int index) {
		int result = index;

		if (index < 0)
			result = cells[i].length - 1;
		else if (index > cells[i].length - 1)
			result = 0;

		return result;
	}

	public ArrayList<GameObject> dump() {
		return life;
	}

	public int getGeneration() {
		return generationCounter;
	}

	public boolean isAlive() {
		return alive;
	}
}
