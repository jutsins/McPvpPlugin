package baglisted;


import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.*;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CommandHandler implements CommandExecutor {

    private final Main plugin;


    public CommandHandler(Main plugin) {
        this.plugin = plugin;
    }

    Area pvpArea1 = new Area(-50, 21, -7, 9, 49, 50);
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
            if (command.getName().equalsIgnoreCase("ping")) {
                commandSender.sendMessage("pong");
                return true;

            }

            if (command.getName().equalsIgnoreCase("playerlist")) {
                int onlinePlayers = Bukkit.getOnlinePlayers().size();
                if (onlinePlayers == 0) {
                    commandSender.sendMessage("Current online Players:\n" +
                            "None");
                } else
                    commandSender.sendMessage("Current online Players:\n" + Bukkit.getOnlinePlayers());
            }

            if (command.getName().equalsIgnoreCase("spreadPlayer")) {
                world = Bukkit.getServer().getWorlds().get(0);
                Player p = null;
                if (commandSender instanceof Player) {
                    p = (Player) commandSender;
                }
                if (commandSender instanceof BlockCommandSender){
                    BlockCommandSender blockCommandSender = (BlockCommandSender) commandSender;
                    Block block = blockCommandSender.getBlock();
                    p = utils.getNearestPlayer(block);
                }

                Position spawnLocation= pvpArea1.findSpawns(pvpArea1.getY1(), pvpArea1.getY2(), pvpArea1);
                Location l = new Location(world, spawnLocation.getX() + 0.5, spawnLocation.getY() + 1, spawnLocation.getZ() + 0.5);
                p.teleport(l);
                return true;
            }
        } else {
            commandSender.sendMessage("You have to be OP to use Baglisted commands.");
            return false;
        }
        return true;
    }

}
