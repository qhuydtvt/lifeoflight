package lol.states;

/**
 * Created by huynq on 7/30/17.
 */
public class MapPosition {
    public int x;
    public int y;

    public MapPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public MapPosition() {
        this.x = 0;
        this.y = 0;
    }

    public void addUp(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public MapPosition add(int dx, int dy) {
        return new MapPosition(x + dx, y + dy);
    }

    public MapPosition add(MapPosition other) {
        return add(other.x, other.y);
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(int x, int y) {
        return this.x == x && this.y == 7;
    }

    public boolean equals(MapPosition other) {
        return equals(other.x, other.y);
    }

    @Override
    public String toString() {
        return "MapPosition{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
