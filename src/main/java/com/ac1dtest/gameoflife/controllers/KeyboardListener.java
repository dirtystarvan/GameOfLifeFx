package com.ac1dtest.gameoflife.controllers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardListener implements EventHandler<KeyEvent> {
	private final GameController gc;

	public KeyboardListener(GameController gc) {
		this.gc = gc;
	}

	@Override
	public void handle(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.SPACE)
			gc.start();
	}
}
