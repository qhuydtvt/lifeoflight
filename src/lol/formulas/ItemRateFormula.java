package lol.formulas;

import com.google.gson.annotations.SerializedName;
import lol.bases.Utils;

/**
 * Created by huynq on 8/7/17.
 */
public class ItemRateFormula extends Formula {

    @SerializedName("key")
    public String key;
    @SerializedName("null")
    public String nullFormula;
    @SerializedName("eatAble")
    public String eatAbleFormula;
    @SerializedName("equipAble")
    public String equipAbleFormula;
    @SerializedName("lengendary")
    public String lengendaryFormula;

    public final static ItemRateFormula combatInstance;
    public final static ItemRateFormula eventInstance;

    public static final int TYPE_NULL = -1;
    public static final int TYPE_EATABLE = 0;
    public static final int TYPE_EQUIPABLE = 1;
    public static final int TYPE_LEGENDARY = 2;

    static {
        combatInstance = Utils.parseJSON("assets/item/rate/item_combat_rate.json", ItemRateFormula.class);
        eventInstance = Utils.parseJSON("assets/item/rate/item_event_rate.json", ItemRateFormula.class);
    }

    public int randomType() {
        float keyValue = evaluate(key);

        if (evaluate(renderKey(nullFormula, keyValue)) == 1.0f) {
            return TYPE_NULL;
        }

        if (evaluate(renderKey(eatAbleFormula, keyValue)) == 1.0f) {
            return TYPE_EATABLE;
        }

        if (evaluate(renderKey(equipAbleFormula, keyValue)) == 1.0f) {
            return TYPE_EQUIPABLE;
        }

        if (evaluate(renderKey(lengendaryFormula, keyValue)) == 1.0f) {
            return TYPE_LEGENDARY;
        }

        return TYPE_NULL;
    }

    private String renderKey(String formula, Float keyValue) {
        return formula.replace("{key}", keyValue.toString());
    }
}
