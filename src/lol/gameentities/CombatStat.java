package lol.gameentities;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by huynq on 8/5/17.
 */
public class CombatStat implements Cloneable {

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

    public CombatStat clone() {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(this), this.getClass());
    }

    @Override
    public String toString() {
        return "CombatStat{" +
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

    public Integer getNextLevelMinExp() {
        return nextLevelMinExp;
    }

    public void setNextLevelMinExp(Integer nextLevelMinExp) {
        this.nextLevelMinExp = nextLevelMinExp;
    }

    public Integer getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(Integer maxHp) {
        this.maxHp = maxHp;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getMana() {
        return mana;
    }

    public void setMana(Integer mana) {
        this.mana = mana;
    }

    public Integer getStamina() {
        return stamina;
    }

    public void setStamina(Integer stamina) {
        this.stamina = stamina;
    }

    public Float getStrRate() {
        return strRate;
    }

    public void setStrRate(Float strRate) {
        this.strRate = strRate;
    }

    public Float getDexRate() {
        return dexRate;
    }

    public void setDexRate(Float dexRate) {
        this.dexRate = dexRate;
    }

    public Float getWisRate() {
        return wisRate;
    }

    public void setWisRate(Float wisRate) {
        this.wisRate = wisRate;
    }

    public Integer getStr() {
        return str;
    }

    public void setStr(Integer str) {
        this.str = str;
    }

    public Integer getDex() {
        return dex;
    }

    public void setDex(Integer dex) {
        this.dex = dex;
    }

    public Integer getWis() {
        return wis;
    }

    public void setWis(Integer wis) {
        this.wis = wis;
    }

    public Integer getLuck() {
        return luck;
    }

    public void setLuck(Integer luck) {
        this.luck = luck;
    }

    public Integer getVision() {
        return vision;
    }

    public void setVision(Integer vision) {
        this.vision = vision;
    }
}
