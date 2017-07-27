package lol;

import lol.settings.Setings;

import javax.swing.*;
import java.awt.*;

/**
 * Created by huynq on 7/28/17.
 */
public class GameWindow extends JFrame {
    public GameWindow() {
        this.setSize(1024, 768);
        this.setVisible(true);
        this.setTitle(Setings.GAME_TITLE);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
