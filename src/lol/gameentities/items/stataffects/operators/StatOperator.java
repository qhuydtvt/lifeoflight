package lol.gameentities.items.stataffects.operators;

/**
 * Created by huynq on 8/5/17.
 */
public interface StatOperator {
    float op(float currentValue, final float originValue, float amount);
}
