package lol;

import lol.settings.Settings;
import lol.uis.GamePanel;

import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Created by huynq on 7/28/17.
 */
public class GameWindow extends JFrame {

    GamePanel textScreenPanel;
    GamePanel commandPanel;
    GamePanel statsPanel;

    public GameWindow() {
        textScreenPanel = new GamePanel();
        textScreenPanel.getSize().set(
                Settings.TEXT_SCREEN_SCREEN_WIDTH,
                Settings.TEXT_SCREEN_SCREEN_HEIGHT);

        commandPanel = new GamePanel();
        commandPanel.getPosition().set(
                0,
                Settings.SCREEN_HEIGHT
        );
        commandPanel.getSize().set(
                Settings.CMD_SCREEN_WIDTH,
                Settings.CMD_SCREEN_HEIGHT
        );
        commandPanel.getAnchor().set(0, 1);
        commandPanel.setColor(Color.BLUE);

        statsPanel = new GamePanel();
        statsPanel.getPosition().set(
                Settings.SCREEN_WIDTH,
                0
        );
        statsPanel.getAnchor().set(1, 0);
        statsPanel.setColor(Color.CYAN);
        statsPanel.getSize().set(
                Settings.STATS_SCREEN_WIDTH,
                Settings.STATS_SCREEN_HEIGHT
        );
        statsPanel.setColor(Color.GRAY);

        setupWindow();
    }

    private void setupWindow() {
        this.setSize(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        this.setVisible(true);
        this.setTitle(Settings.GAME_TITLE);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);

        textScreenPanel.render(g2d);
        commandPanel.render(g2d);
        statsPanel.render(g2d);
    }
}
