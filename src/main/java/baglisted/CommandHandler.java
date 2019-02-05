package baglisted;


import org.bukkit.*;

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
            if (command.getName().equalsIgnoreCase("players")) {
                int onlinePlayers = Bukkit.getOnlinePlayers().size();
                if (onlinePlayers == 1) {
                    commandSender.sendMessage("There is currently " + onlinePlayers + " player online");
                } else
                    commandSender.sendMessage("There are currently " + onlinePlayers + " players online");
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

                Player p = (Player) commandSender;

                ArrayList<Position> spawnPositions = new ArrayList<Position>();

                while (spawnPositions.size() == 0) {
                    spawnPositions = findSpawns(pvpArea1.getY1(), pvpArea1.getY2(), pvpArea1);
                }
                int index = ThreadLocalRandom.current().nextInt(0, spawnPositions.size());
                int height = spawnPositions.get(index).getY();
                Location l = new Location(world, positionX + 0.5, height + 1, positionZ + 0.5);
                p.teleport(l);
                return true;
            }
        } else {
            commandSender.sendMessage("You have to be OP to use Baglisted commands.");
            return false;
        }
        return true;
    }


    public ArrayList<Position> findSpawns(int posY1, int posY2, Area pvpArea) {
        world = Bukkit.getServer().getWorlds().get(0);
        Position randomPosition = GetRandomPosition(pvpArea);
        ArrayList<Position> spawnPositions = new ArrayList<>();
        //gets the y position
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

    public Position GetRandomPosition(Area pvpArea) {
        int positionX = ThreadLocalRandom.current().nextInt(pvpArea1.getX1(), pvpArea1.getX2());
        int positionZ = ThreadLocalRandom.current().nextInt(pvpArea1.getZ1(), pvpArea1.getZ2());
        Position pos = new Position(positionX, positionZ);
        return pos;
    }

}
