package com.ac1dtest.gameoflife.controllers;

import com.ac1dtest.gameoflife.model.GameModel;
import com.ac1dtest.gameoflife.view.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        int width = 20;
        int height = 20;

        GameView view = new GameView(width, height);
        GameModel model = new GameModel(width, height);
        GameController controller = new GameController(view, model);

        view.setMenuActionHandler(new MenuController(model, view));

        Scene mainScene = view.getScene();
        mainScene.setOnKeyPressed(new KeyboardListener(controller));

        stage.setScene(mainScene);
        stage.setTitle("GameOfLife");
        view.drawScene();

        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}