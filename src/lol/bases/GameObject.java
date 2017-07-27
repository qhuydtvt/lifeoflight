package lol.bases;

import lol.bases.renderers.Renderer;

import java.awt.*;

/**
 * Created by huynq on 7/28/17.
 */
public class GameObject {

    private Vector2D position;
    protected Renderer renderer;

    public GameObject() {
        position = new Vector2D();
    }

    public Vector2D getPosition() {
        return position;
    }

    public void render(Graphics2D g2d) {
        if (renderer != null) {
            renderer.render(g2d, position);
        }
    }

    public void run() {

    }
}
