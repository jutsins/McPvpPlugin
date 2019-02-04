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
                int positionX = ThreadLocalRandom.current().nextInt(pvpArea1.getX1(), pvpArea1.getX2());
                int positionZ = ThreadLocalRandom.current().nextInt(pvpArea1.getZ1(), pvpArea1.getZ2());
                ArrayList<Integer> possibleHeight = new ArrayList<Integer>();
                Block b = world.getBlockAt(pvpArea1.getX1(), pvpArea1.getY1(), pvpArea1.getZ1());
                commandSender.sendMessage(b.getType().toString() + " " + world.getName());
                //yet to implement forloop to determine height.
                for (int i = pvpArea1.getY1(); i <= pvpArea1.getY2(); i++) {
                    Block spawnBlock = world.getBlockAt(positionX, i, positionZ);
                    if (spawnBlock.getType().toString() != "AIR" && world.getBlockAt(positionX, i + 1, positionZ).getType().toString() == "AIR" && world.getBlockAt(positionX, i + 2, positionZ).getType().toString() == "AIR") {
                        commandSender.sendMessage("JAWOEL");
                    }
                }
                return true;
            }
        } else {
            commandSender.sendMessage("You have to be OP to use Baglisted commands.");
            return false;
        }
        return true;
    }

}
