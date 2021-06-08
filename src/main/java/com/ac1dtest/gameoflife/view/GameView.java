package com.ac1dtest.gameoflife.view;

import com.ac1dtest.gameoflife.model.GameObject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;

import java.util.ArrayList;

public class GameView {
	private Scene scene;

	private final ArrayList<MenuItem> menuItems = new ArrayList<>();
	GraphicsContext context;

	private final double windowWidth = 600;
	private final double windowHeight = 625;
	private final double menuHeight = 25;
	private final double cellW;
	private final double cellH;

	private final int columns;
	private final int rows;


	public GameView(int columns, int rows) {
		init();

		this.columns = columns;
		this.rows = rows;
		cellW = windowWidth / columns;
		cellH = (windowHeight - menuHeight) / rows;
	}

	private void init() {
		Group root = new Group();
		Canvas canvas = new Canvas(windowWidth, windowHeight);
		root.getChildren().add(canvas);

		context = canvas.getGraphicsContext2D();

		MenuBar menuBar = new MenuBar();

		Menu initMenu = new Menu("Initialization type");

		//menu items
		final String GLIDER = "Glider";
		final String HIVES = "Hives";
		final String METHUSELAH = "Methuselah";
		final String RANDOM = "Random";

		RadioMenuItem glider = new RadioMenuItem(GLIDER);
		RadioMenuItem methuselah = new RadioMenuItem(METHUSELAH);
		RadioMenuItem hives = new RadioMenuItem(HIVES);
		RadioMenuItem random = new RadioMenuItem(RANDOM);

		ToggleGroup tGroupLifeInit = new ToggleGroup();
		glider.setToggleGroup(tGroupLifeInit);
		hives.setToggleGroup(tGroupLifeInit);
		methuselah.setToggleGroup(tGroupLifeInit);
		random.setToggleGroup(tGroupLifeInit);

		//add items to the menus
		menuItems.add(glider);
		menuItems.add(hives);
		menuItems.add(methuselah);
		menuItems.add(random);
		initMenu.getItems().addAll(menuItems);
		menuBar.getMenus().add(initMenu);

		glider.setId(GLIDER);
		hives.setId(HIVES);
		methuselah.setId(METHUSELAH);
		random.setId(RANDOM);

		menuBar.setMaxHeight(menuHeight);

		VBox container = new VBox();
		container.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE,
				new CornerRadii(1), new Insets(0.0))));
		container.getChildren().addAll(menuBar, root);

		scene = new Scene(container, windowWidth, windowHeight);
	}

	private void drawBackground() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if ((i + j) % 2 != 0)
					context.setFill(Color.WHITE);
				else
					context.setFill(Color.LIGHTGRAY);

				context.fillRect(i * cellW, j * cellH, cellW, cellH);
			}
		}
	}

	private void drawObjects(ArrayList<GameObject> arr) {
		context.setFill(Color.INDIANRED);

		for (GameObject item: arr) {
			context.fillOval(item.x * cellW, item.y * cellH, cellW, cellH);
		}
	}

	public void drawScene(ArrayList<GameObject> objects, int id) {
		drawBackground();
		drawObjects(objects);
		drawGenerationId(id);
	}

	public void drawScene() {
		drawBackground();
		drawGreeting();
	}

	public void drawEnding() {
		context.setFont(new Font("Berlin Sans FB", 48));
		context.setFontSmoothingType(FontSmoothingType.LCD);
		context.setFill(Color.RED);
		context.fillText("GAME OVER\n     ( ╯°□°)╯",
				windowWidth / 2 - 130, (windowHeight - menuHeight) / 2 - 20);
	}

	public void drawGreeting() {
		context.setStroke(Color.BLUE);
		context.setFont(new Font("Berlin Sans FB", 48));
		context.strokeText("Select initial state of life\n      and press space",
				windowWidth / 2 - 235, (windowHeight - menuHeight) / 2 - 80);
	}

	public void drawGenerationId(int value) {
		context.setFill(Color.BLACK);
		context.setFont(new Font("Digital-7", 13));
		context.fillText("Generation: " + value, 5, windowHeight - menuHeight - 5);
	}

	public Scene getScene() {
		return scene;
	}

	public void setMenuActionHandler(EventHandler<ActionEvent> handler) {
		for (MenuItem item: menuItems)
			item.setOnAction(handler);
	}

}
