package lol.gameentities.players;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by huynq on 8/5/17.
 */
public class PlayerStat implements Cloneable {

    @SerializedName("nextLevelMinExp")
    public Integer nextLevelMinExp;
    @SerializedName("maxHp")
    public Integer maxHp;
    @SerializedName("hp")
    public Integer hp;
    @SerializedName("mana")
    public Integer mana;
    @SerializedName("stamina")
    public Integer stamina;
    @SerializedName("strRate")
    public Float strRate;
    @SerializedName("dexRate")
    public Float dexRate;
    @SerializedName("wisRate")
    public Float wisRate;
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
                "nextLevelMinExp=" + nextLevelMinExp +
                ", maxHp=" + maxHp +
                ", hp=" + hp +
                ", mana=" + mana +
                ", stamina=" + stamina +
                ", strRate=" + strRate +
                ", dexRate=" + dexRate +
                ", wisRate=" + wisRate +
                ", str=" + str +
                ", dex=" + dex +
                ", wis=" + wis +
                ", luck=" + luck +
                ", vision=" + vision +
                '}';
    }
}
