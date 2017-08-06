package lol.formulas;

import com.udojava.evalex.Expression;
import lol.gameentities.State;
import lol.gameentities.players.Player;

/**
 * Created by huynq on 8/3/17.
 */
public class Formula {

    static float evaluate(String formula) {
        return new Expression(formula).eval().floatValue();
    }

    static String statStr(Object i) {
        if (i == null) return "0";
        return i.toString();
    }


//    public static String render(String template) {
//        Player player = state.getPlayer();
//        return template
//                .replace("{player.hp}", player.stat.hp.toString())
//                .replace("{player.mana}", player.stat.mana.toString())
//                .replace("{player.stamina}", player.stat.stamina.toString())
//                .replace("{player.str}", player.stat.str.toString())
//                .replace("{player.dex}", player.stat.dex.toString())
//                .replace("{player.wis}", player.stat.wis.toString())
//                .replace("{player.luck}", player.stat.luck.toString())
//                .replace("{player.vision}", player.stat.vision.toString())
//                .replace("{player.maxHp}", player.stat.maxHp.toString())
//                .replace("{player.strRate}", player.stat.strRate.toString())
//                .replace("{player.exp}", player.exp.toString());
//    }

    static String safe(String s) {
        return String.format("(%s)", s);
    }
}
