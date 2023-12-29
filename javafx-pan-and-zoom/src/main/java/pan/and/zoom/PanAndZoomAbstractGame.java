package pan.and.zoom;

import javafx.scene.input.MouseButton;
import org.geom.shape.rectangle.Rect2df;
import org.geom.vector.vec2d.Vec2df;
import org.javafx.game.AbstractGame;
import org.javafx.game.GameApplication;
import org.javafx.game.input.Input;

public abstract class PanAndZoomAbstractGame extends AbstractGame {

    protected PanAndZoomGraphicContext pz;

    protected Vec2df mouse;

    protected Rect2df screen;

    protected Rect2df arena;

    protected void setMouse(Input i) {
        mouse.set((float) i.getMouseX(), (float) i.getMouseY());
    }

    @Override
    public void initialize(GameApplication gc) {
        final float arenaSize = 1000;
        pz = new PanAndZoomGraphicContext(gc.getGraphicsContext());
        arena = new Rect2df(0, 0, arenaSize, arenaSize);
        screen = new Rect2df(pz.getWorldTopLeft(), pz.getWorldVisibleArea());
        mouse = new Vec2df();
        setMouse(gc.getInput());
    }

    @Override
    public void update(GameApplication gc, float elapsedTime) {
        pz.handlePanAndZoom(gc, MouseButton.MIDDLE, 0.001f, true, true);
        Vec2df screenMouse = new Vec2df((float)gc.getInput().getMouseX(), (float)gc.getInput().getMouseY());
        this.mouse.set(PanAndZoomUtils.screenToWorld(screenMouse, pz.getWorldOffset(), pz.getWorldScale()));
        screen.set(pz.getWorldTopLeft(), pz.getWorldVisibleArea());
    }

    // Getters & Setters

    public PanAndZoomGraphicContext getPz() {
        return pz;
    }

    public void setPz(PanAndZoomGraphicContext pz) {
        this.pz = pz;
    }

    public Vec2df getMouse() {
        return mouse;
    }

    public void setMouse(Vec2df mouse) {
        this.mouse = mouse;
    }

    public Rect2df getScreen() {
        return screen;
    }

    public void setScreen(Rect2df screen) {
        this.screen = screen;
    }

    public Rect2df getArena() {
        return arena;
    }

    public void setArena(Rect2df arena) {
        this.arena = arena;
    }

}
