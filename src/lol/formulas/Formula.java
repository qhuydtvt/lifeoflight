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

    private static String render(String template) {
        Player player = state.getPlayer();
        return template
                .replace("{player_luck}", "" + player.getLuck());
    }

    protected static String safe(String s) {
        return String.format("(%s)", s);
    }
}
