package lol;

import lol.bases.GameObject;
import lol.bases.Utils;
import lol.settings.Settings;
import lol.uis.GamePanel;
import lol.uis.TextPanel;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by huynq on 7/28/17.
 */
public class GameWindow extends JFrame {

    GamePanel textScreenPanel;
    GamePanel commandPanel;
    GamePanel statsPanel;

    BufferedImage backbufferImage;
    Graphics2D backBufferGraphics;

    long lastTimeUpdate = -1;

    public GameWindow() {
        setupFont();
        setupPanels();
        setupWindow();
    }

    private void setupFont() {

    }

    private void setupPanels() {
        textScreenPanel = new TextPanel();
        textScreenPanel.getSize().set(
                Settings.TEXT_SCREEN_SCREEN_WIDTH,
                Settings.TEXT_SCREEN_SCREEN_HEIGHT);
        GameObject.add(textScreenPanel);


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
        GameObject.add(commandPanel);

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
        GameObject.add(statsPanel);
    }


    private void setupWindow() {
        this.setSize(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        this.setVisible(true);
        this.setTitle(Settings.GAME_TITLE);

        backbufferImage = new BufferedImage(this.getWidth(),this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics = (Graphics2D) backbufferImage.getGraphics();
    }

    public void gameLoop() {
        while(true) {
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.currentTimeMillis();
            }

            long currentTime = System.currentTimeMillis();

            if(currentTime - lastTimeUpdate > 17) {
                lastTimeUpdate = currentTime;
                GameObject.runAll();

                render(backBufferGraphics);
                repaint();
            }
        }
    }

    public void render(Graphics2D g2d) {
        g2d.setFont(Settings.DEFAULT_FONT);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);

        GameObject.renderAll(g2d);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backbufferImage, 0, 0, null);
    }
}
