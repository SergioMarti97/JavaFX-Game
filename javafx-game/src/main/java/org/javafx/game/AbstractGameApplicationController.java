package org.javafx.game;

import javafx.fxml.Initializable;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import org.javafx.game.clock.GameClock;
import org.javafx.game.input.Input;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class AbstractGameApplicationController implements Initializable, IAbstractGame {

    protected Input input;

    protected GameClock clock;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clock = new GameClock(this::updateGame, this::render);
        this.initialize();
    }

    private void updateGame(final float dt) {
        this.update(dt);
        if (input != null) {
            input.update();
        }
    }

    @Override
    public void stop() {

    }

    // Methods

    public void startGame() {
        clock.start();
    }

    public void stopGame() {
        clock.stop();
    }

    public void writeImage(final int width, final int height, final int[] array, PixelWriter pw) {
        pw.setPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), array, 0, width);
    }

    // Getters & Setters

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public GameClock getClock() {
        return clock;
    }

    public void setClock(GameClock clock) {
        this.clock = clock;
    }

}
