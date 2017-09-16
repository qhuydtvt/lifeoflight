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
        offsetText.set(20, Settings.STATS_SCREEN_HEIGHT / 2);
    }

    @Override
    public void render(Graphics2D g2d) {
        clear();
        Player player = State.instance.getPlayer();
        setFontMetrics(g2d.getFontMetrics());
        addText(String.format("HP: %s/%s", player.getStat().hp, player.getStat().maxHp));
        addText(String.format("LVL: %s", player.currentLevel + 1));
        addText(String.format("EXP: %s/%s", player.exp, player.getNextLevelMinExp()));
        addText(String.format("STR: %s", player.getStat().str));
        addText(String.format("DEX: %s", player.getStat().dex));
        addText(String.format("WIS: %s", player.getStat().wis));
        addText(String.format("STAMINA: %s", player.getStat().stamina));
        addText(String.format("MANA: %s", player.getStat().mana));
        addText(String.format("LUCK: %s", player.getStat().luck));
        addText(String.format("VISION: %s", player.getStat().vision));
        addText(String.format("SKILL UPGRADES: %s", player.getSkillUpradesLeft()));
        super.render(g2d);
    }
}
