package baglisted;


import javafx.geometry.Point3D;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

    private final Main plugin;

    public CommandHandler(Main plugin) {
        this.plugin = plugin;
    }

    //World w = Bukkit.getServer().getWorld("pvp");
    Area pvpArea1 = new Area(-50, 49, 50, 9, 22, -7);


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender.isOp()) {
            if (command.getName().equalsIgnoreCase("gm")) {
                Player p = (Player) commandSender;

                for (World world : Bukkit.getServer().getWorlds()) {
                    p.sendMessage("World: " + world.getName());
                }
                p.sendMessage("jawel" + Bukkit.getServer().getWorlds().get(0));

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
            /*if (command.getName().equalsIgnoreCase("spreadPlayer")){
                System.out.println(w +" world");
                Block block = w.getBlockAt(pvpArea1.getX1(), pvpArea1.getY1(), pvpArea1.getZ1());
                System.out.println(block + " block");

                commandSender.sendMessage(block.getType().toString());
            }*/
        } else {
            commandSender.sendMessage("You have to be OP to use Baglisted commands.");
            return false;
        }
        return true;
    }
}
