package lol.formulas;

import com.google.gson.annotations.SerializedName;
import lol.bases.Utils;

/**
 * Created by huynq on 8/7/17.
 */
public class CombatItemRateFormula extends Formula {

    @SerializedName("rate")
    public String rateFormula;

    public final static CombatItemRateFormula instance;

    static {
        instance = Utils.parseJSON("assets/item/rate/item_battle_rate.json", CombatItemRateFormula.class);
    }

    public boolean generate() {
        return evaluate(rateFormula) == 1.0f;
    }
}
