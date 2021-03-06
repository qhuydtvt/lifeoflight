package lol.gameentities.maps;

import lol.bases.Utils;
import lol.gameentities.MapPosition;
import lol.gameentities.maps.mapitems.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static lol.gameentities.maps.mapitems.MapItemType.*;

/**
 * Created by huynq on 7/30/17.
 */
public class Map {
    private int width;
    private int height;

    private int characterStartX;
    private int getCharacterStartY;

    private ArrayList<ArrayList<MapItem>> data;

    private int mainItemLeft;
    private Object currentLevel;

    public Map() {
        data = new ArrayList<>();
        mainItemLeft = 0;
    }

    public int getPlayerStartX() {
        return characterStartX;
    }

    public int getPlayerStartY() {
        return getCharacterStartY;
    }

    public int getMainItemLeft() {
        return mainItemLeft;
    }

    public void removeItem(int x, int y) {
        MapItem mapItem = getMapItem(x, y);
        if (mapItem.getType() == MapItemType.MAIN) {
            mainItemLeft--;
            setMapItem(x, y, MapItem.newEmpty());
        }
        else if(mapItem.getType() == MapItemType.EVENT) {
            setMapItem(x, y, MapItem.newEmpty());
        }
    }

    public void removeItem(MapPosition position) {
        removeItem(position.x, position.y);
    }

    public MapItem getMapItem(int x, int y) {
        if (!isValidPosition(x, y)) return null;
        return data.get(y).get(x);
    }

    public void setMapItem(int x, int y, MapItem item) {
        if (!isValidPosition(x, y)) return;
        data.get(y).set(x, item);
    }

    public MapItem getMapItem(MapPosition mapPosition) {
        return getMapItem(mapPosition.x, mapPosition.y);
    }

    public List<List<MapItem>> getMapItems(int playerX, int playerY, int range) {
        int startX = playerX - range;
        int endX = playerX + range;
        int startY = playerY - range;
        int endY = playerY + range;

        List<List<MapItem>> block = new ArrayList<>();

        for (int y = startY; y <= endY; y++) {
            List<MapItem> row = new ArrayList<>();
            for (int x = startX; x <= endX; x++) {
                row.add(getMapItem(x, y));
            }
            block.add(row);
        }

        return block;
    }

    public List<List<MapItem>> getMapItems(MapPosition playerPosition, int range) {
        return getMapItems(playerPosition.x, playerPosition.y, range);
    }

    private boolean isValidPosition(int x, int y) {
        if (x < 0 || x >= width) return false;
        if (y < 0 || y >= height) return false;
        return true;
    }

    public static Map parse(String mapContent) {
        String[] lines = mapContent.split("\r\n|\n|\r");
        Map map = new Map();
        map.height = lines.length;
        map.width = lines[0].length();
        for (int y = 0; y < map.height; y++) {
            ArrayList<MapItem> row = new ArrayList<>();
            for (int x = 0; x < map.width; x++) {
                MapItem mapItem = MapItem.parse(lines[y].charAt(x));
                if (mapItem.getType() == START) {
                    map.characterStartX = x;
                    map.getCharacterStartY = y;
                } else if (mapItem.getType() == MAIN) {
                    map.mainItemLeft++;
                }
                row.add(mapItem);
            }
            map.data.add(row);
        }

        return map;
    }

    public static Map parseFile(String url) {
        return parse(Utils.loadFileContent(url));
    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                content.append(data.get(y).get(x).toString());
            }
            content.append("\n");
        }
        return content.toString();
    }
}
