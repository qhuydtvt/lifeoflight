package lol.gameentities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huynq on 8/6/17.
 */
public class CombatUnit {
    @SerializedName("stat")
    protected CombatStat stat;

    @SerializedName("name")
    protected String name;

    public CombatStat getStat() {
        return stat;
    }

    public void setStat(CombatStat stat) {
        this.stat = stat;
    }
}
