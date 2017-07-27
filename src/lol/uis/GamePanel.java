package lol.uis;

import lol.bases.GameObject;
import lol.bases.Vector2D;
import lol.bases.renderers.SolidRectRenderer;

import java.awt.*;
import java.util.Vector;

/**
 * Created by huynq on 7/28/17.
 */
public class GamePanel extends GameObject {

    private SolidRectRenderer solidRectRenderer;

    public GamePanel() {
        super();
        solidRectRenderer = new SolidRectRenderer();
        this.renderer = solidRectRenderer;
    }

    public Vector2D getSize() {
        return this.solidRectRenderer.getSize();
    }

    public Vector2D getAnchor() {
        return this.solidRectRenderer.getAnchor();
    }

    public void setColor(Color c) {
        this.solidRectRenderer.setColor(c);
    }
}
