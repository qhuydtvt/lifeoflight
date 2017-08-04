package lol.gameentities.players;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by huynq on 8/5/17.
 */
public class PlayerStat implements Cloneable {

    @SerializedName("name")
    public String name;
    @SerializedName("min_exp")
    public Integer minExp;
    @SerializedName("max_hp")
    public Integer maxHp;
    @SerializedName("hp")
    public Integer hp;
    @SerializedName("mana")
    public Integer mana;
    @SerializedName("stamina")
    public Integer stamina;
    @SerializedName("strRate")
    public Float strrate;
    @SerializedName("dexRate")
    public Float dexrate;
    @SerializedName("wisRate")
    public Float wisrate;
    @SerializedName("str")
    public Integer str;
    @SerializedName("dex")
    public Integer dex;
    @SerializedName("wis")
    public Integer wis;
    @SerializedName("luck")
    public Integer luck;
    @SerializedName("vision")
    public Integer vision;

    public void init() {
        hp = maxHp;
    }

    public PlayerStat clone() {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(this), this.getClass());
    }

    @Override
    public String toString() {
        return "PlayerStat{" +
                "name='" + name + '\'' +
                ", minExp='" + minExp + '\'' +
                ", maxHp=" + maxHp +
                ", hp=" + hp +
                ", mana=" + mana +
                ", stamina=" + stamina +
                ", strrate=" + strrate +
                ", dexrate=" + dexrate +
                ", wisrate=" + wisrate +
                ", str=" + str +
                ", dex=" + dex +
                ", wis=" + wis +
                ", luck=" + luck +
                ", vision=" + vision +
                '}';
    }
}
