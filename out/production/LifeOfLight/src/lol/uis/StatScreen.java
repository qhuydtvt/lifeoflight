package lol.uis;

import lol.gameentities.State;
import lol.gameentities.players.Player;
import lol.inputs.CommandListener;
import lol.inputs.InputManager;
import lol.settings.Settings;

import java.awt.*;

/**
 * Created by huynq on 8/2/17.
 */
public class StatScreen extends TextView {
    public StatScreen() {
        super();
        offsetText.set(10, Settings.STATS_SCREEN_HEIGHT / 2);
    }

    @Override
    public void render(Graphics2D g2d) {
        clear();
        Player player = State.instance.getPlayer();
        setFontMetrics(g2d.getFontMetrics());
        addText(String.format("HP: %s/%s", player.getHp(), player.getMaxHp()));
        addText(String.format("Str: %s", player.getStrength()));
        super.render(g2d);
    }
}
