package baglisted;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Position {

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public int getY() { return y; }

    private int x;
    private int y;
    private int z;

    public Position(int x, int z) {
        this.x = x;
        this.z = z;
    }
    public Position(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
