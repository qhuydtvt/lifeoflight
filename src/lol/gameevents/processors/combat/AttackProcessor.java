package lol.gameevents.processors.combat;

import lol.bases.Utils;
import lol.events.EventManager;
import lol.gameentities.State;
import lol.gameentities.players.Player;
import lol.gameevents.CombatEvent;
import lol.gameevents.GameEvent;
import lol.gameevents.LostEvent;
import lol.gameevents.MainGameEvent;
import lol.gameevents.processors.Processor;
import lol.monsters.Monster;

import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public class AttackProcessor extends Processor {

    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        CombatEvent combatEvent = (CombatEvent) currentEvent;
        List<Monster> monsters = combatEvent.getMonsters();
        if (commands.size() == 0) {
            EventManager.pushUIMessage("You must choose a monster to attack");
            return null;
        }

        String monsterIdxString = commands.get(0);

        try {
            EventManager.pushUIMessage(";");
            int monsterIdx = Integer.parseInt(monsterIdxString);
            Monster monster = monsters.get(monsterIdx);
            Player player = State.instance.getPlayer();
            fight(player, monster);
            monsterFightBack(player, monsters);
            if (player.getHp() <= 0) {
                return new LostEvent();
            }
            if (monster.getStat().getHp() <= 0) {
                EventManager.pushUIMessage(String.format("%s just died", monster.getName()));
                monsters.remove(monster);
                if (monsters.size() == 0) {
                    EventManager.pushUIMessage("All monsters were killed, you won the combat");
                    return new MainGameEvent();
                }
                return null;
            }

            EventManager.pushUIMessage(";");
        }
        catch (NumberFormatException | IndexOutOfBoundsException ex) {
            EventManager.pushUIMessage("Again, which one?");
            return null;
        }

        return null;
    }

    private void fight(Player player, Monster monster) {
        // TODO: Calculate fight stats based on formula
        if (Utils.rollDice() < player.getLuck()) {
            monster.getHit(player.getStrength());
            EventManager.pushUIMessage(String.format("You hit %s and now it has %s hp left", monster.getName(),
                    monster.getStat().getHp()));
        } else {
            EventManager.pushUIMessage(String.format("You attacked %s but missed", monster.getName()));
        }
    }

    private void monsterFightBack(Player player, List<Monster> monsters) {
        for (Monster monster : monsters) {
            if (monster.getStat().hp > 0) {
                if (monster.getStat().getHp() > 0 && Utils.rollDice() < monster.getStat().getLuck()) {
                    player.getHit(monster.getStat().getStrength());
                    EventManager.pushUIMessage(String.format("%s hit you, you now has %s hp left", monster.getName(), player.getHp()));
                } else {
                    EventManager.pushUIMessage(String.format("%s attacked you but missed", monster.getName()));
                }
            }
        }
    }
}
