package lol.formulas;

import com.google.gson.annotations.SerializedName;
import lol.bases.Utils;

/**
 * Created by huynq on 8/9/17.
 */
public class CombatConfigFormula extends Formula {

    @SerializedName("monstersNumber")
    private String monstersNumberFormula;
    @SerializedName("monstersLevel")
    private String monstersLevelFormula;

    public static final CombatConfigFormula instance =
            Utils.parseJSON("assets/combat/battle_config.json", CombatConfigFormula.class);

    private CombatConfigFormula() {
    }

    public int randomMonstersNumber() {
        int result = (int)(float)evaluate(monstersNumberFormula);
        if (result == 0) result = 1;
        return result;
    }

    public int randomMonsterLevel() {
        int result = (int)(float)evaluate(monstersLevelFormula);
        if (result == 0) result = 1;
        return result;
    }
}
