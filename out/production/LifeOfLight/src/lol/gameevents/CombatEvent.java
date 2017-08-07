package lol.gameevents;

import lol.bases.Utils;
import lol.events.EventManager;
import lol.formulas.ItemRateFormula;
import lol.gameentities.State;
import lol.gameevents.processors.combat.AttackProcessor;
import lol.gameevents.processors.Processor;
import lol.gameevents.processors.combat.FleeProcessor;
import lol.gameentities.monsters.Monster;

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
        put("FLEE", new FleeProcessor());
    }};

    public CombatEvent() {
        monsters = new ArrayList<>();
        for(int i = 0; i < Utils.rollDice() % 2 + 1; i++) {
            Monster monster = State.instance.randomMonster();
            if (monster != null)
                monsters.add(monster);
        }
        if (monsters.size() == 0) {
            System.out.println("There are no monsters to generate");
        }
        EventManager.pushUIMessage("You have just entered a combat");
        EventManager.pushUIMessage(String.format("You met %s monsters", monsters.size()));
        for (Monster monster : monsters) {
            EventManager.pushUIMessage(monster.getInfo());
        }
        EventManager.pushUIMessage("You can ;#FF0000atk; or you can ;#FFC0CBflee;");
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
        return Processor.forward(commands, commandProcessors.get(mainCommand), this);
    }


    @Override
    public GameEvent postProcess() {
        return null;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }
}
