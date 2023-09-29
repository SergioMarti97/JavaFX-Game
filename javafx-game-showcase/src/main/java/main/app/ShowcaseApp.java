package main.app;

import main.game.PanAndZoomExampleGame;
import org.javafx.game.GameApplication;

public class ShowcaseApp extends GameApplication {

    @Override
    public void init() throws Exception {
        super.init();
        setAppName("Game Showcase");
        setGame(new PanAndZoomExampleGame());
    }

}
