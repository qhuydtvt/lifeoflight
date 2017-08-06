package lol.formulas;

import com.udojava.evalex.Expression;
import lol.gameentities.State;
import lol.gameentities.maps.Map;
import lol.gameentities.players.Player;

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
                .replace("{player.hp}", statStr(player.stat.hp))
                .replace("{player.mana}", statStr(player.stat.mana))
                .replace("{player.stamina}", statStr(player.stat.stamina))
                .replace("{player.str}", statStr(player.stat.str))
                .replace("{player.dex}", statStr(player.stat.dex))
                .replace("{player.wis}", statStr(player.stat.wis))
                .replace("{player.luck}", statStr(player.stat.luck))
                .replace("{player.vision}", statStr(player.stat.vision))
                .replace("{player.maxHp}", statStr(player.stat.maxHp))
                .replace("{player.strRate}", statStr(player.stat.strRate))
                .replace("{player.dexRate}", statStr(player.stat.dexRate))
                .replace("{player.wisRate}", statStr(player.stat.wisRate))
                .replace("{player.exp}", statStr(player.exp.toString()))
                // Map stats
                .replace("{map.currentLevel}", statStr(state.getCurrentLevel()))
                ;
    }

    public static Float evaluate(String template) {
        return new Expression(render(template)).eval().floatValue();
    }

    static String safe(String s) {
        return String.format("(%s)", s);
    }
}
