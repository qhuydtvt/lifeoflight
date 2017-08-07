package lol.formulas;

import com.udojava.evalex.Expression;
import lol.gameentities.State;
import lol.gameentities.maps.Map;
import lol.gameentities.players.Player;
import lol.settings.Settings;

/**
 * Created by huynq on 8/3/17.
 */
public class Formula {

    static String statStr(Object i) {
        if (i == null) return "0";
        return i.toString();
    }

    public static String render(String template) {
        State state = State.instance;
        Player player = State.instance.getPlayer();
        return template
                // Player stats
                .replace("{player.hp}", statStr(player.getStat().hp))
                .replace("{player.mana}", statStr(player.getStat().mana))
                .replace("{player.stamina}", statStr(player.getStat().stamina))
                .replace("{player.str}", statStr(player.getStat().str))
                .replace("{player.dex}", statStr(player.getStat().dex))
                .replace("{player.wis}", statStr(player.getStat().wis))
                .replace("{player.luck}", statStr(player.getStat().luck))
                .replace("{player.vision}", statStr(player.getStat().vision))
                .replace("{player.maxHp}", statStr(player.getStat().maxHp))
                .replace("{player.strRate}", statStr(player.getStat().strRate))
                .replace("{player.dexRate}", statStr(player.getStat().dexRate))
                .replace("{player.wisRate}", statStr(player.getStat().wisRate))
                .replace("{player.exp}", statStr(player.exp.toString()))
                // Map stats
                .replace("{map.currentLevel}", statStr(state.getCurrentLevel()))
                ;
    }

    public static Float evaluate(String template) {
        if (Settings.DEBUG) {
            System.out.println(template);
        }
        return round(new Expression(render(template)).eval().floatValue());
    }

    private static float round(float f) {
        int i = (int)(f * 10);
        return (float)i / 10;
    }

    static String safe(String s) {
        return String.format("(%s)", s);
    }
}
