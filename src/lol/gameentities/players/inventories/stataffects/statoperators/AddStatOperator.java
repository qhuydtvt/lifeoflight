package lol.gameentities.players.inventories.stataffects.statoperators;

/**
 * Created by huynq on 8/5/17.
 */
public class AddStatOperator implements StatOperator {

    @Override
    public float op(float value, float originValue, float amount) {
        return value + amount;
    }
}
