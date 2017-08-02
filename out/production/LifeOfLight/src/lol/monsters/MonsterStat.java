package lol.monsters;

import com.google.gson.annotations.SerializedName;

public class MonsterStat {
    @SerializedName("hp")
    public int hp;
    @SerializedName("mana")
    public int mana;
    @SerializedName("stamina")
    public int stamina;
    @SerializedName("str")
    public int strength;
    @SerializedName("dex")
    public int dex;
    @SerializedName("wis")
    public int wisdom;
    @SerializedName("luck")
    public int luck;
    @SerializedName("exp")
    public int exp;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDex() {
        return dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "MonsterStat{" +
                "hp=" + hp +
                ", mana=" + mana +
                ", stamina=" + stamina +
                ", strength=" + strength +
                ", dex=" + dex +
                ", wisdom=" + wisdom +
                ", luck=" + luck +
                '}';
    }
}
