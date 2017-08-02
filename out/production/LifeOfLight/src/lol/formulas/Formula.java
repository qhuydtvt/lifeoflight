package lol.formulas;

import com.udojava.evalex.Expression;
import lol.gameentities.State;
import lol.gameentities.players.Player;

/**
 * Created by huynq on 8/3/17.
 */
public class Formula {
    private static State state = State.instance;

    public static float evaluate(String template) {
        String expression = render(template);
        return new Expression(expression).eval().floatValue();
    }

    public static String render(String template) {
        Player player = state.getPlayer();
        return template
                .replace("{player.hp}", player.getHp().toString())
                .replace("{player.mana}", player.getMana().toString())
                .replace("{player.stamina}", player.getStamina().toString())
                .replace("{player.str}", player.getStrength().toString())
                .replace("{player.dex}", player.getDex().toString())
                .replace("{player.wis}", player.getWis().toString())
                .replace("{player.luck}", player.getLuck().toString())
                .replace("{player.vision}", player.getVision().toString())
                .replace("{player.maxHp}", player.getMaxHp().toString())
                .replace("{player.strRate}", player.getStrRate().toString())
                .replace("{player.exp}", player.getExp().toString());
    }

    protected static String safe(String s) {
        return String.format("(%s)", s);
    }
}
