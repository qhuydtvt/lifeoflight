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

    private MapItemType type;

    public char getSymbol() {
        return symbol;
    }

    public MapItem setSymbol(char symbol) {
        this.symbol = symbol;
        this.type = MapItemType.EMPTY;
        return this;
    }

    public MapItem(MapItemType type, char symbol) {
        this.type = type;
        this.symbol = symbol;
    }

    public MapItem() {
        this(MapItemType.EMPTY, ' ');
    }

    public static MapItem parse(char c) {
        switch (c) {
            case CHAR_EMPTY: return new MapItem(MapItemType.EMPTY, ' ');
            case CHAR_WALL: return new MapItem(MapItemType.WALL, 'O');
            case CHAR_EVENT: return new MapItem(MapItemType.EVENT, '$');
            case CHAR_START: return new MapItem(MapItemType.START, ' ');
            case CHAR_EXIT: return new MapItem(MapItemType.EXIT, '#');
            case CHAR_MAIN: return new MapItem(MapItemType.MAIN, 'M');
            default:
                break;
        }

        return new MapItem(MapItemType.EMPTY, ' ');
    }

    public MapItemType getType() {
        return type;
    }

    public static MapItem newEmpty() {
        return new MapItem(MapItemType.EMPTY, ' ');
    }
}
