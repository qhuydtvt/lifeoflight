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

    private MapPosition mapPosition;

    public Player(int hp, int mana, int stamina, int strength, int dex, int wis, int luck, int vision) {
        this.hp = hp;
        this.mana = mana;
        this.stamina = stamina;
        this.strength = strength;
        this.dex = dex;
        this.wis = wis;
        this.luck = luck;
        this.vision = vision;
        mapPosition = new MapPosition();
    }

    public Player() {
        this(15, 10, 10, 5, 3, 4, 3, 2);
    }

    public int getHp() {
        return hp;
    }

    public int getMana() {
        return mana;
    }

    public int getStamina() {
        return stamina;
    }

    public int getStrength() {
        return strength;
    }

    public int getDex() {
        return dex;
    }

    public int getWis() {
        return wis;
    }

    public int getLuck() {
        return luck;
    }

    public int getVision() {
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
