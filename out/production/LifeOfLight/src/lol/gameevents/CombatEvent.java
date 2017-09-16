package lol.gameevents;

import lol.bases.Utils;
import lol.events.EventManager;
import lol.formulas.CombatConfigFormula;
import lol.formulas.CombatFormula;
import lol.formulas.ItemRateFormula;
import lol.gameentities.State;
import lol.gameentities.items.GameItem;
import lol.gameentities.players.Player;
import lol.gameevents.processors.combat.AttackProcessor;
import lol.gameevents.processors.Processor;
import lol.gameevents.processors.combat.FleeProcessor;
import lol.gameentities.monsters.Monster;
import lol.gameevents.processors.combat.SkillProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public class CombatEvent implements GameEvent {

    private List<Monster> monsters;
    private ItemRateFormula itemRateFormula = ItemRateFormula.combatInstance;

    HashMap<String, Processor> commandProcessors = new HashMap<String, Processor>() {{
        put("ATK", new AttackProcessor());
        put("SKILL", new SkillProcessor());
        put("FLEE", new FleeProcessor());
    }};

    public CombatEvent() {
        monsters = new ArrayList<>();
        CombatConfigFormula combatConfigFormula = CombatConfigFormula.instance;
        for (int i = 0; i < combatConfigFormula.randomMonstersNumber(); i++) {
            Monster monster = State.instance.randomMonster(combatConfigFormula.randomMonsterLevel());
            if (monster != null)
                monsters.add(monster);
        }

        if (monsters.size() == 0) {
            System.out.println("There are no monsters to generate");
        } else {
            EventManager.pushUIMessage("Bạn đang bước vào một trậnga chiến  ");
            EventManager.pushUIMessage(String.format("Bạn gặp %s địch thủ", monsters.size()));
            for (Monster monster : monsters) {
                EventManager.pushUIMessage(monster.getInfo());
            }
            EventManager.pushUIMessage("Bạn có thể tấn công (;#FF0000atk, skill;) hoặc chạy trốn (;#FFC0CBflee;)");
        }
    }

    @Override
    public GameEvent preProcess() {
        return null;
    }

    @Override
    public GameEvent process(List<String> commands) {
        if (commands.size() == 0) return null;
        String mainCommand = commands.get(0);
        if (!commandProcessors.containsKey(mainCommand)) {
            EventManager.pushHelpMessage();
            return null;
        }

        GameEvent resultEvent = Processor.forward(commands, commandProcessors.get(mainCommand), this);

        monsters.removeIf(monster -> {
            if(monster.getStat().hp <= 0) {
                EventManager.pushUIMessage(String.format("%s đã bị tiêu diệt", monster.getName()));
                addExp(monster);
                generateRandomItem();
                return true;
            }
            return false;
        });

        if (monsters.size() == 0) {
            EventManager.pushUIMessage("Địch thủ đã bị quét sạch, bạn đã thắng trận chiến");
        }

        if (resultEvent == null) {
            if (monsters.size() == 0) {
                return new MainGameEvent();
            } else {
                return monsterFightBack(State.instance.getPlayer(), monsters);
            }
        } else {
            return resultEvent;
        }
    }

    private GameEvent monsterFightBack(Player player, List<Monster> monsters) {
        for (Monster monster : monsters) {
            if (monster.getStat().hp > 0) {
                if (!CombatFormula.instance.doge(player)) {
                    player.getHit(monster.getStat().str);
                    EventManager.pushUIMessage(String.format("%s vừa đánh trúng bạn, bạn còn lại %s HP", monster.getName(), player.getStat().hp));
                    if (player.getStat().hp <= 0) return new MainGameEvent();
                } else {
                    EventManager.pushUIMessage(String.format("%s vừa đánh trượt bạn", monster.getName()));
                }
            }
        }
        return null;
    }

    private void addExp(Monster monster) {
        Player player = State.instance.getPlayer();

        int expToIncrease = monster.getExp() - (monster.getLevel() + player.currentLevel);

        if (expToIncrease > 0) {
            player.changeExp(expToIncrease);
            EventManager.pushUIMessage(String.format("Your EXP just increased by %s", expToIncrease));
            levelUp();
        }
    }
    private void levelUp() {
        Player player = State.instance.getPlayer();
        boolean levelingUp = true;
        do {
            int levelUpStatus = player.levelUp();
            if (levelUpStatus == Player.NO_LEVEL_UP) {
                levelingUp = false;
            }
            else if (levelUpStatus == Player.LEVEL_REACHED_MAX) {
                EventManager.pushUIMessage("Bạn đã đạt level cao nhất");
                levelingUp = false;
            }
            else {
                EventManager.pushUIMessage(String.format("Chúc mừng bạn đã lên level %s", player.currentLevel + 1));
            }
        }
        while (levelingUp);
    }

    private void generateRandomItem() {
        State state = State.instance;
        Player player = state.getPlayer();
        int type = itemRateFormula.randomItemType();
        if (type != GameItem.TYPE_NULL) {
            GameItem gameItem = GameItem.random(type, state);
            if (gameItem != null) {
                player.collect(gameItem);
                EventManager.pushUIMessage(String.format("Bạn vừa nhặt được %s", gameItem.name));
                EventManager.pushUIMessage(gameItem.name);
                EventManager.pushUIMessage(gameItem.getDescription());
                EventManager.pushUIMessage(gameItem.statText());
            }
        }
    }


    @Override
    public GameEvent postProcess() {
        return null;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }
}
