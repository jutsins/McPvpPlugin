package baglisted;

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

    }
}
