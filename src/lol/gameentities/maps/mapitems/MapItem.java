package lol.gameentities.maps.mapitems;

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

    private char symbol;

    public char getSymbol() {
        return symbol;
    }

    public MapItem setSymbol(char symbol) {
        this.symbol = symbol;
        return this;
    }

    public static MapItem parse(char c) {
        switch (c) {
            case CHAR_EMPTY: return new Empty().setSymbol(c);
            case CHAR_WALL: return new Wall().setSymbol(c);
            case CHAR_EVENT: return new Event().setSymbol(c);
            case CHAR_START: return new Start().setSymbol(' ');
            case CHAR_EXIT: return new Exit().setSymbol(c);
            case CHAR_MAIN: return new Main().setSymbol(c);
            default:
                break;
        }

        return new Empty().setSymbol(' ');
    }
}
