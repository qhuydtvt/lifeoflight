package lol.gameentities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huynq on 8/6/17.
 */
public class CombatUnit {
    @SerializedName("stat")
    public CombatStat stat;

    public CombatStat getStat() {
        return stat;
    }
}
