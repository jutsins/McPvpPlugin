package baglisted;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Area {
    private int x1;
    private int y1;
    private int z1;

    private int x2;
    private int y2;
    private int z2;

    World world;

    public Area(int x1, int y1, int z1, int x2, int y2, int z2) {
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getZ1() {
        return z1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getZ2() {
        return z2;
    }

    public ArrayList<Position> findSpawns(int posY1, int posY2, Area pvpArea) {
        world = Bukkit.getServer().getWorlds().get(0);
        Position randomPosition = getRandomPosition(pvpArea);
        ArrayList<Position> spawnPositions = new ArrayList<>();
        for (int i = posY1; i < posY2; i++) {
            Block b = world.getBlockAt(randomPosition.getX(), i, randomPosition.getZ());
            if (b.getType().toString() != "AIR") {
                if (world.getBlockAt(randomPosition.getX(), i + 1, randomPosition.getZ()).getType().toString() == "AIR" && world.getBlockAt(randomPosition.getX(), i + 2, randomPosition.getZ()).getType().toString() == "AIR") {
                    Position spawnPosition = new Position(randomPosition.getX(), i, randomPosition.getZ());
                    spawnPositions.add(spawnPosition);
                }
            }
        }
        return spawnPositions;
    }

    public Position getRandomPosition(Area pvpArea) {
        int positionX = ThreadLocalRandom.current().nextInt(pvpArea.getX1(), pvpArea.getX2());
        int positionZ = ThreadLocalRandom.current().nextInt(pvpArea.getZ1(), pvpArea.getZ2());
        Position pos = new Position(positionX, positionZ);
        return pos;
    }
}
