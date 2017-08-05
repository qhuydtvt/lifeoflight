package lol.gameentities.monsters;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lol.bases.Utils;
import lol.gameentities.CombatUnit;

import java.util.List;
import java.lang.reflect.Type;
import java.util.Random;

/**
 * Created by huynq on 8/1/17.
 */
public class Monster extends CombatUnit implements Cloneable {

    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private int id;

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

    protected Monster clone() throws CloneNotSupportedException {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(this), Monster.class);
    }

    public void getHit(int damage) {
        stat.hp -= damage;
        if (stat.hp < 0) stat.hp = 0;
    }

    private static List<Monster> allMonsters = null;
    static {
        String monsterContent = Utils.loadFileContent("assets/monster/monsters.json");
        if (monsterContent != null) {
            Type monsterListType = new TypeToken<List<Monster>>(){}.getType();
            allMonsters = new Gson().fromJson(monsterContent, monsterListType);
        }
    }

    public static Monster randomMonster() {
        try {
            return allMonsters.get(new Random().nextInt(allMonsters.size())).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getInfo() {
        return String.format("%s, hp=%s, str=%s", name, stat.hp, stat.str);
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", stat=" + stat +
                '}';
    }
}
