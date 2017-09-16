package lol.gameevents;

import lol.bases.Utils;
import lol.events.EventManager;
import lol.formulas.CombatConfigFormula;
import lol.formulas.CombatFormula;
import lol.formulas.ItemRateFormula;
import lol.gameentities.State;
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

    HashMap<String, Processor> commandProcessors = new HashMap<String, Processor>() {{
        put("ATK", new AttackProcessor(ItemRateFormula.combatInstance));
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
            EventManager.pushUIMessage("You have just entered a combat");
            EventManager.pushUIMessage(String.format("You met %s monsters", monsters.size()));
            for (Monster monster : monsters) {
                EventManager.pushUIMessage(monster.getInfo());
            }
            EventManager.pushUIMessage("You can ;#FF0000atk; or you can ;#FFC0CBflee;");
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

        monsters.removeIf(monster -> monster.getStat().hp <= 0);

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


    @Override
    public GameEvent postProcess() {
        return null;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }
}
