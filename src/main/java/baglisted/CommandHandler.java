package baglisted;


import javafx.geometry.Point3D;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;

import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CommandHandler implements CommandExecutor {

    private final Main plugin;

    public CommandHandler(Main plugin) {
        this.plugin = plugin;
    }

    Area pvpArea1 = new Area(-50, 22, -7, 9, 49, 50);
    World world;


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender.isOp()) {
            if (command.getName().equalsIgnoreCase("gm")) {
                Player p = (Player) commandSender;
                switch (args[0]) {
                    case "0":
                    case "s":
                        p.setGameMode(GameMode.SURVIVAL);
                        return true;
                    case "1":
                    case "c":
                        p.setGameMode(GameMode.CREATIVE);
                        return true;
                    case "2":
                    case "a":
                        p.setGameMode(GameMode.ADVENTURE);
                        return true;
                    case "3":
                    case "sp":
                        p.setGameMode(GameMode.SPECTATOR);
                        return true;
                    default:
                        p.sendMessage("invalid argument, possible arguments are: 0, 1, 2, 3, s, c, a, sp");
                        return false;
                }
            }
            if (command.getName().equalsIgnoreCase("spreadPlayer")) {
                world = Bukkit.getServer().getWorlds().get(0);
                ArrayList<Integer> positions = new ArrayList<>();
                for (int i = positions.size(); i > 0;) {
                    positions = findSpawns(pvpArea1.getY1(), pvpArea1.getY2());
                    commandSender.sendMessage("i");
                }




                return true;
            }
        } else {
            commandSender.sendMessage("You have to be OP to use Baglisted commands.");
            return false;
        }
        return true;
    }


    public ArrayList<Integer> findSpawns(int posY1, int posY2) {
        world = Bukkit.getServer().getWorlds().get(0);
        Position randomPosition = GetRandomPosition();
        ArrayList<Integer> spawnHeights = new ArrayList<Integer>();
        for (int i = posY1; i < posY2; i++) {
            Block b = world.getBlockAt(randomPosition.getX(), i, randomPosition.getZ());
            if (b.getType().toString() != "AIR") {
                if (world.getBlockAt(randomPosition.getX(), i + 1, randomPosition.getZ()).getType().toString() == "AIR" && world.getBlockAt(randomPosition.getX(), i + 2, randomPosition.getZ()).getType().toString() == "AIR") {
                    spawnHeights.add(i);
                }
            }
        }
        return spawnHeights;
    }

    public Position GetRandomPosition(){
        int positionX = ThreadLocalRandom.current().nextInt(pvpArea1.getX1(), pvpArea1.getX2());
        int positionZ = ThreadLocalRandom.current().nextInt(pvpArea1.getZ1(), pvpArea1.getZ2());
        Position pos = new Position(positionX , positionZ);
        return pos;
    }

}
