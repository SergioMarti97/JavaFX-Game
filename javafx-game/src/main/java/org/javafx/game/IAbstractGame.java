package org.javafx.game;

public interface IAbstractGame {

    void initialize();

    void update(final float dt);

    void render();

    void stop();

}
