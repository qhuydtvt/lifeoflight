package lol.formulas;

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
        instance = Utils.parseJSON("assets/item/rate/item_combat_rate.json", CombatItemFormula.class);
    }

    private CombatItemFormula() {
    }

    private boolean evalRate() {
        return evaluate(rate) == 1.0f;
    }
}
