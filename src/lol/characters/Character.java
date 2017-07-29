package lol.characters;

/**
 * Created by huynq on 7/30/17.
 */
public class Character {
    private int hp;
    private int mana;
    private int stamina;
    private int strength;
    private int dex;
    private int wis;
    private int luck;

    private int x;
    private int y;

    public Character(int hp, int mana, int stamina, int strength, int dex, int wis, int luck) {
        this.hp = hp;
        this.mana = mana;
        this.stamina = stamina;
        this.strength = strength;
        this.dex = dex;
        this.wis = wis;
        this.luck = luck;
    }

    public Character() {
        this(15,10,10,5,3,4, 1);
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

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}
