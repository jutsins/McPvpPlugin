package baglisted;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.CommandBlock;
import org.bukkit.entity.Player;

import java.util.Collection;


public class utils {
    private static Collection<? extends Player> onlinePlayers;

    public static Player getNearestPlayer(CommandBlock block, Bukkit bukkit) {
        Location blockLocation = block.getLocation();
        onlinePlayers = bukkit.getServer().getOnlinePlayers();
        for (Player player: onlinePlayers) {
            findDistance(player.getLocation(), blockLocation);
        }
    }

    public static double getDifference(double int1, double int2) {
        return Math.abs((int1) - (int2));
    }

    public static double findDistance(Location objectLocation, Location compareLocation){
        double differenceX = getDifference(objectLocation.getX(), compareLocation.getX());
        double differenceY = getDifference(objectLocation.getY(), compareLocation.getY());
        double differenceZ = getDifference(objectLocation.getZ(), compareLocation.getZ());
        double distanceSquared = Math.pow(differenceX, 2) + Math.pow(differenceY,2) + Math.pow(differenceZ,2);
        double distance = Math.sqrt(distanceSquared);
        return distance;
    }
}
