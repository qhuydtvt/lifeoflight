package lol.gameentities.monsters;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lol.bases.Utils;
import lol.gameentities.CombatUnit;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import java.util.Random;

/**
 * Created by huynq on 8/1/17.
 */
public class Monster extends CombatUnit implements Cloneable {

    private static List<Monster> allMonsters = null;

    static {
        String monsterContent = Utils.loadFileContent("assets/monster/monsters.json");
        if (monsterContent != null) {
            Type monsterListType = new TypeToken<List<Monster>>(){}.getType();
            allMonsters = new Gson().fromJson(monsterContent, monsterListType);
        }
    }

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("level")
    private int level;

    @SerializedName("exp")
    private int exp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static List<Monster> monsterInLevel(int level) {
        List<Monster> returnList = new ArrayList<>();

        System.out.println(allMonsters);

        for (Monster monster : allMonsters) {
            if (monster.level == level) {
                returnList.add(monster);
            }
        }

        return returnList;
    }

    protected Monster clone() throws CloneNotSupportedException {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(this), Monster.class);
    }

    public void getHit(int damage) {
        stat.hp -= damage;
        if (stat.hp < 0) stat.hp = 0;
    }

    public int getExp() {
        return exp;
    }

    public String getInfo() {
        return String.format("%s, hp=%s, str=%s", name, stat.hp, stat.str);
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", level=" + level +
                ", exp=" + exp +
                '}';
    }

    public int getLevel() {
        return level;
    }
}
