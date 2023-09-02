package main.app;

import game.GameApplication;
import main.game.ExampleGame;

public class ShowcaseApp extends GameApplication {

    @Override
    public void init() throws Exception {
        super.init();
        setAppName("Game Showcase");
        setGame(new ExampleGame());
    }

}
