package lol.gameentities.players;

import lol.gameentities.MapPosition;

/**
 * Created by huynq on 7/30/17.
 */
public class Player {
    private int hp;
    private int mana;
    private int stamina;
    private int strength;
    private int dex;
    private int wis;
    private int luck;
    private int vision;
    private int maxHp;
    private float strRate;
    private int exp;

    private MapPosition mapPosition;

    public Player(int hp, int mana, int stamina, int strength, int dex, int wis, int luck, int vision, float strRate, int exp) {
        this.hp = hp;
        this.mana = mana;
        this.stamina = stamina;
        this.strength = strength;
        this.dex = dex;
        this.wis = wis;
        this.luck = luck;
        this.vision = vision;
        this.maxHp = hp;
        this.strRate = strRate;
        this.exp = exp;
        mapPosition = new MapPosition();
    }

    public Player() {
        this(15, 10, 10, 5, 3, 4, 5, 2, 0.5f,0);
    }

    public void changeMaxHP(int amount) {
        this.maxHp += amount;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getMana() {
        return mana;
    }

    public Integer getStamina() {
        return stamina;
    }

    public Integer getStrength() {
        return strength;
    }

    public Integer getDex() {
        return dex;
    }

    public Integer getWis() {
        return wis;
    }

    public Integer getLuck() {
        return luck;
    }

    public Integer getVision() {
        return vision;
    }

    public void move(int dx, int dy) {
        this.mapPosition.addUp(dx, dy);
    }

    public void setPosition(int x, int y) {
        this.mapPosition.set(x, y);
    }

    public MapPosition getPosition() {
        return mapPosition;
    }

    public Integer getMaxHp() {
        return maxHp;
    }

    public Integer getExp() {
        return exp;
    }

    public Float getStrRate() {
        return strRate;
    }

    public void changeExp(int amount) {
        this.exp += amount;
    }

    @Override
    public String toString() {
        return "Player{" +
                "hp=" + hp +
                ", mana=" + mana +
                ", stamina=" + stamina +
                ", strength=" + strength +
                ", dex=" + dex +
                ", wis=" + wis +
                ", luck=" + luck +
                ", mapPosition=" + mapPosition +
                '}';
    }

    public void getHit(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
    }
}
