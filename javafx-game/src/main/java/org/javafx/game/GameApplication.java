package org.javafx.game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.javafx.game.clock.GameClock;
import org.javafx.game.input.Input;

public class GameApplication extends Application {

    // Settings of the application

    protected String appName = "default";

    protected int width = 800;

    protected int height = 600;

    // Classes which manages internally the game

    protected AbstractGame game;

    protected Input input;

    protected GameClock clock;

    // Scene layout

    protected Scene scene;

    protected StackPane stackPane;

    protected Canvas canvas;

    @Override
    public void start(Stage stage) throws Exception {
        // ----------------------------- //
        // - Set the application scene - //
        // ----------------------------- //

        // Set the canvas
        canvas = new Canvas();
        canvas.setFocusTraversable(true);

        // Choose between canvas or image view...
        stackPane = new StackPane(canvas);

        // bing width and height properties to the graphics display
        canvas.widthProperty().bind(stackPane.widthProperty());
        canvas.heightProperty().bind(stackPane.heightProperty());

        // Input
        input = new Input(canvas);

        // Game Clock
        clock = new GameClock(this::update, this::render);

        // Set the scene
        scene = new Scene(stackPane, width, height);
        stage.setScene(scene);
        stage.setTitle(appName);

        // Show the scene
        if (game != null) {
            game.initialize(this);
            clock.start();
            stage.show();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        game.stop(this);
    }

    // Update and Render methods

    protected void update(float elapsedTime) {
        game.update(this, elapsedTime);
        input.update();
    }

    protected void render() {
        game.render(this);
    }

    // Getters and Setters

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AbstractGame getGame() {
        return game;
    }

    public void setGame(AbstractGame game) {
        this.game = game;
    }

    public Input getInput() {
        return input;
    }

    public GameClock getClock() {
        return clock;
    }

    public GraphicsContext getGraphicsContext() {
        return canvas.getGraphicsContext2D();
    }

}
