package lol.gameentities.items.stataffects.operators;

/**
 * Created by huynq on 8/5/17.
 */
public class AddStatOperator implements StatOperator {

    @Override
    public float op(float currentValue, float originValue, float amount) {
        return currentValue + amount;
    }
}
