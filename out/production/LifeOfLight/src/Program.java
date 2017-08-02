import com.udojava.evalex.Expression;
import lol.GameWindow;
import lol.formulas.CombatFormula;
import lol.gameentities.State;
import lol.gameevents.GameEventManager;

/**
 * Created by huynq on 7/28/17.
 */
public class Program {
    public static void main(String[] args) {

        CombatFormula.instance.physicsAttack();

        GameEventManager.instance.loadData();

        Runtime.getRuntime().addShutdownHook(new Thread(State.instance::save));

        GameWindow gameWindow = new GameWindow();
        gameWindow.gameLoop();
    }
}
