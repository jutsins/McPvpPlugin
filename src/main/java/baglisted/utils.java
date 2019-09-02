package baglisted;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.World;

import java.io.Console;
import java.util.Collection;


public class utils {
    private static Collection<? extends Player> onlinePlayers;

    /**
     * Allows a commandblock to use a command on the nearest player
     *
     * @param block The commandblock
     * @return nearest player to the commandblock
     */
    public static Player getNearestPlayer(Block block) {
        Location blockLocation = block.getLocation();
        onlinePlayers = Bukkit.getServer().getOnlinePlayers();
        double nearestDistance = 0;
        Player nearestPlayer = null;
        for (Player player : onlinePlayers) {
            double distance = findDistance(player.getLocation(), blockLocation);
            if (nearestDistance < distance) {
                nearestDistance = distance;
                nearestPlayer = player;
            }

        }
        return nearestPlayer;
    }

    public static double getDifference(double int1, double int2) {
        return Math.abs((int1) - (int2));
    }

    /**
     * calculates the distance between two locations
     *
     * @param objectLocation
     * @param compareLocation
     * @return the distance between locations
     */
    public static double findDistance(Location objectLocation, Location compareLocation) {
        double differenceX = getDifference(objectLocation.getX(), compareLocation.getX());
        double differenceY = getDifference(objectLocation.getY(), compareLocation.getY());
        double differenceZ = getDifference(objectLocation.getZ(), compareLocation.getZ());
        double distanceSquared = Math.pow(differenceX, 2) + Math.pow(differenceY, 2) + Math.pow(differenceZ, 2);
        double distance = Math.sqrt(distanceSquared);
        return distance;
    }
//TODO improve the function to allow other area's to work with this function as well.
    //This can be done by adding the area as a parameter in the function
    //Example: public static int playersInarea (Area area)

    /**
     * function to calculate the amount of players in an area.
     *
     * @return The amount of players in the area
     */
    public static int playersInArea() {
        Area pvpArea1 = new Area(-49, 21, -5, 9, 49, 49);
        onlinePlayers = Bukkit.getServer().getOnlinePlayers();
        int playerCount = 0;
        for (Player player : onlinePlayers) {
            if (player.getLocation().getX() >= pvpArea1.getX1() &&
                    player.getLocation().getX() <= pvpArea1.getX2() &&
                    player.getLocation().getY() >= pvpArea1.getY1() &&
                    player.getLocation().getY() <= pvpArea1.getY2()) {
                playerCount++;
            }

        }
        return playerCount;
    }

    public static int stringToInt(String number) {
        return (int) Double.parseDouble(number);
    }

    public static boolean areaInArea(Area areaOfficial, Area areaOutOfBounds, Position position) {
        World world;
        Area pvpAreaOfficial = new Area(-49, 21, -5, 9, 49, 49);
        world = Bukkit.getServer().getWorlds().get(0);
        Area pvpOOR1 = new Area(-48, 29, -5, -27, 29, 3);
        Area pvpOOR2 = new Area(-31, 31, -5, -20, 31, 9);
        Area pvpOOR3 = new Area(0, 31, -5, 9, 26, 2);
        Area pvpOOR4 = new Area(2, 27, 30, 9, 29, 34);

        if (position.getX() >= areaOutOfBounds.getX1() &&
                position.getX() <= areaOutOfBounds.getX2() &&
                position.getZ() >= areaOutOfBounds.getZ1() &&
                position.getZ() <= areaOutOfBounds.getZ2()) {
            return true;
        }


        return false;


    }

}