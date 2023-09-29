package main.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import org.geom.shape.rectangle.Rect2df;
import org.geom.vector.vec2d.Vec2df;
import org.javafx.game.AbstractGame;
import org.javafx.game.GameApplication;
import pan.and.zoom.PanAndZoomGraphicContext;
import pan.and.zoom.PanAndZoomUtils;

public class PanAndZoomExampleGame extends AbstractGame {

    private PanAndZoomGraphicContext pz;

    private Vec2df mouse;

    private Rect2df screen;

    private Rect2df arena;

    @Override
    public void initialize(GameApplication gc) {
        pz = new PanAndZoomGraphicContext(gc.getGraphicsContext());
        mouse = new Vec2df();
        arena = new Rect2df(0, 0, 100, 100);
    }

    @Override
    public void update(GameApplication gc, float elapsedTime) {
        pz.handlePanAndZoom(gc, MouseButton.MIDDLE, 0.001f, true, true);
        Vec2df mouse = new Vec2df((float)gc.getInput().getMouseX(), (float)gc.getInput().getMouseY());
        this.mouse.set(PanAndZoomUtils.screenToWorld(mouse, pz.getWorldOffset(), pz.getWorldScale()));
    }

    @Override
    public void render(GameApplication gc) {
        GraphicsContext g = gc.getGraphicsContext();

        // Dibujar el fondo
        screen = new Rect2df(pz.getWorldTopLeft(), pz.getWorldVisibleArea());
        g.setFill(Color.BLUE);
        pz.fillRect(screen.getPos(), screen.getSize());

        g.setStroke(Color.WHITE);

        // Dibujar el marco de la arena
        pz.getGc().setLineWidth(1);
        pz.strokeRect(arena.getPos(), arena.getSize());
    }

}
