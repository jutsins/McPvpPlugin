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

    Area pvpArea1 = new Area(-50, 49, 50, 9, 22, -7);
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
                //yet to implement forloop to determine height.
                return true;
            }
        } else {
            commandSender.sendMessage("You have to be OP to use Baglisted commands.");
            return false;
        }
        return true;
    }

}
