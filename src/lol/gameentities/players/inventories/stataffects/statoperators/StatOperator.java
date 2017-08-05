package lol.gameentities.players.inventories.stataffects.statoperators;

/**
 * Created by huynq on 8/5/17.
 */
public interface StatOperator {
    float op(float value, float orginValue, float amount);
}
