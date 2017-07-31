package lol.states.maps;

import lol.states.MapPosition;
import lol.states.maps.mapitems.MapItem;
import lol.states.maps.mapitems.Start;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynq on 7/30/17.
 */
public class Map {
    private int width;
    private int height;

    private int characterStartX;
    private int getCharacterStartY;

    private ArrayList<ArrayList<MapItem>> data;

    public Map() {
        data = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPlayerStartX() {
        return characterStartX;
    }

    public int getPlayerStartY() {
        return getCharacterStartY;
    }

    public MapItem getMapItem(int x, int y) {
        if (x < 0 || x >= width) return null;
        if (y < 0 || y >= height) return null;
        return data.get(y).get(x);
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

        for (int y = startY; y <= endY; y ++) {
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

    public static Map parse(String mapContent) {
        String[] lines = mapContent.split("\r\n|\n|\r");
        Map map = new Map();
        map.height = lines.length;
        map.width = lines[0].length();
        for (int y = 0; y < map.height; y++) {
            ArrayList<MapItem> row = new ArrayList<>();
            for (int x = 0; x < map.width; x++) {
                MapItem mapItem = MapItem.parse(lines[y].charAt(x));
                if (mapItem instanceof Start) {
                    map.characterStartX = x;
                    map.getCharacterStartY = y;
                }
                row.add(mapItem);
            }
            map.data.add(row);
        }

        return map;
    }

    public static Map parseFile(String url) {
        File file = new File(url);
        FileInputStream fis = null;
        String content = null;
        try {
            fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            content = new String(data, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content == null ? null : parse(content);
    }

    public boolean isValidPosition(MapPosition position) {
        return 0 <= position.x && position.x < width &&
                0 <= position.y && position.y < height;
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
