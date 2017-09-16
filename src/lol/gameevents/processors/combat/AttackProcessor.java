package lol.gameevents.processors.combat;

import lol.events.EventManager;
import lol.formulas.CombatFormula;
import lol.formulas.ItemRateFormula;
import lol.gameentities.State;
import lol.gameentities.items.GameItem;
import lol.gameentities.players.Player;
import lol.gameevents.CombatEvent;
import lol.gameevents.GameEvent;
import lol.gameevents.MainGameEvent;
import lol.gameevents.processors.Processor;
import lol.gameentities.monsters.Monster;

import lol.formulas.CombatFormula.PhysicsAttackResult;

import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public class AttackProcessor extends Processor {



    public AttackProcessor() {
    }

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
            if (player.getStat().hp <= 0) {
                return new MainGameEvent();
            }

            EventManager.pushUIMessage(";");
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            EventManager.pushUIMessage("Again, which one?");
            return null;
        }

        return null;
    }

    private void fight(Player player, Monster monster) {
        PhysicsAttackResult physicsAttackResult = CombatFormula.instance.physicsAttack(player);
        if (physicsAttackResult.isCriticalAttack) {
            EventManager.pushUIMessage("Tấn công với thương tổn cao");
        }
        if (CombatFormula.instance.doge(monster)) {
            EventManager.pushUIMessage(String.format("%s đã tránh khỏi đòn tấn công của bạn", monster.getName()));
        } else {
            monster.getHit(physicsAttackResult.damage);
            EventManager.pushUIMessage(String.format("Bạn tấn công %s với %s thương tổn và nó còn lại %s hp", monster.getName(),
                    physicsAttackResult.damage,
                    monster.getStat().getHp()));
        }
    }
}
