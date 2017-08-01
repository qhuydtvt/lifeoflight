package lol.gameevents;

import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public class LostEvent implements GameEvent {
    @Override
    public GameEvent process(List<String> commands) {
        return null;
    }

    @Override
    public GameEvent postProcess() {
        return null;
    }
}
