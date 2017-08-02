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
        GameEventManager.instance.loadData();
        for (int i = 0; i < 100; i++) {
            System.out.println(CombatFormula.instance.physicsAttack());
            System.out.println("*********************");
        }


//        Runtime.getRuntime().addShutdownHook(new Thread(State.instance::save));
//
//        GameWindow gameWindow = new GameWindow();
//        gameWindow.gameLoop();
    }
}
