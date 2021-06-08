package com.ac1dtest.gameoflife.controllers;

import com.ac1dtest.gameoflife.model.GameModel;
import com.ac1dtest.gameoflife.view.GameView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameController {
	private final GameView view;
	private final GameModel model;
	private Timeline timeline;
	private boolean isNowPlaying = false;
	private final double duration;

	public GameController(GameView view, GameModel model) {
		this.view = view;
		this.model = model;
		duration = 300;
	}

	public void start() {
		if (model.isInited() && !isNowPlaying) {
			timeline = new Timeline(new KeyFrame(Duration.millis(duration), e -> play()));
			timeline.setCycleCount(Animation.INDEFINITE);
			isNowPlaying = true;
			timeline.play();
		}
	}

	public void play() {
		if (!model.isGameOver()) {
			model.onTurn();
			view.drawScene(model.getCurrentState(), model.getGeneration());
		} else {
			view.drawEnding();
			isNowPlaying = false;
			timeline.stop();
		}
	}
}