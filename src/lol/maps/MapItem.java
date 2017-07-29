package lol.maps;

/**
 * Created by huynq on 7/30/17.
 */
public class MapItem {
    private static final int CHAR_WALL  = '/';
    private static final int CHAR_EMPTY = ' ';
    private static final int CHAR_EVENT = '$';
    private static final int CHAR_START = '@';
    private static final int CHAR_EXIT = '#';
    private static final int CHAR_MAIN = 'M';

    public static MapItem parse(char c) {
        switch (c) {
            case CHAR_EMPTY: return new Empty();
            case CHAR_WALL: return new Wall();
            case CHAR_EVENT: return new Event();
            case CHAR_START: return new Start();
            case CHAR_EXIT: return new Exit();
            case CHAR_MAIN: return new Main();
            default:
                break;
        }

        return new Empty();
    }
}
