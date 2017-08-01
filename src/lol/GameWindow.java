package lol;

import lol.bases.GameObject;
import lol.inputs.InputManager;
import lol.settings.Settings;
import lol.gameevents.GameEventManager;
import lol.uis.InputText;
import lol.uis.StatScreen;
import lol.uis.TextScreen;
import lol.uis.TextView;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by huynq on 7/28/17.
 */
public class GameWindow extends JFrame {

    TextScreen textScreenPanel;
    InputText commandPanel;
    StatScreen statsPanel;

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
        textScreenPanel = new TextScreen();
        textScreenPanel.setColor(Color.BLACK);
        textScreenPanel.getSize().set(
                Settings.TEXT_SCREEN_SCREEN_WIDTH,
                Settings.TEXT_SCREEN_SCREEN_HEIGHT);
        textScreenPanel.getOffsetText().set(20, 40);

        GameObject.add(textScreenPanel);


        commandPanel = new InputText();
        commandPanel.getPosition().set(
                0,
                Settings.SCREEN_HEIGHT
        );
        commandPanel.getOffsetText().set(20, 40);
        commandPanel.getSize().set(
                Settings.CMD_SCREEN_WIDTH,
                Settings.CMD_SCREEN_HEIGHT
        );
        commandPanel.getAnchor().set(0, 1);
        commandPanel.setColor(Color.BLACK);
        GameObject.add(commandPanel);

        statsPanel = new StatScreen();
        statsPanel.getPosition().set(
                Settings.SCREEN_WIDTH,
                0
        );

        statsPanel.getAnchor().set(1, 0);
        statsPanel.setColor(Color.BLACK);
        statsPanel.getSize().set(
                Settings.STATS_SCREEN_WIDTH,
                Settings.STATS_SCREEN_HEIGHT
        );
        GameObject.add(statsPanel);

        InputManager.instance.addCommandListener(textScreenPanel);
        InputManager.instance.addCommandListener(GameEventManager.instance);
    }


    private void setupWindow() {
        this.setSize(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        this.setVisible(true);
        this.setTitle(Settings.GAME_TITLE);
        this.addKeyListener(InputManager.instance);

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
                InputManager.instance.run();
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
