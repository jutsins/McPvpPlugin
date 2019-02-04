package baglisted;

public class Position {

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    private int x;
    private int z;

    public Position(int x, int z) {
        this.x = x;
        this.z = z;
    }
}
