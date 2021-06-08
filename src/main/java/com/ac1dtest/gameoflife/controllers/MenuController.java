package com.ac1dtest.gameoflife.controllers;

import com.ac1dtest.gameoflife.model.GameModel;
import com.ac1dtest.gameoflife.view.GameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioMenuItem;


public class MenuController implements EventHandler<ActionEvent> {
	GameModel model;
	GameView view;

	public MenuController(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void handle(ActionEvent actionEvent) {
		model.createGame(((RadioMenuItem)actionEvent.getSource()).getId());
		view.drawScene(model.getCurrentState(), model.getGeneration());
	}
}
