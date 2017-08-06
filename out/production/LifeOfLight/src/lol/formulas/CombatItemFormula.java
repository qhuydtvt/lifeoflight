package lol.formulas;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lol.bases.Utils;
import lol.gameentities.players.Player;

/**
 * Created by huynq on 8/6/17.
 */
public class CombatItemFormula extends Formula {

    @SerializedName("rate")
    public String rate;
    @SerializedName("which")
    public String which;

    public static final CombatItemFormula instance;

    static {
        instance = Utils.parseJSON("assets/item/item_battle_rate.json", CombatItemFormula.class);
    }

    private CombatItemFormula() {
    }

    private boolean evalRate() {
        return evaluate(rate) == 1.0f;
    }

    private int evalItemId(Player player) {
        return (int)evaluate(which.replace("{player.current_level}", player.currentLevel.toString()));
    }
}
