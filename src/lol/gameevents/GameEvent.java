package lol.gameevents;

import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public interface GameEvent {
    GameEvent preProcess();
    GameEvent process(List<String> commands);
    GameEvent postProcess();
}
