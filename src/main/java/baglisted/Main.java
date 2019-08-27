package baglisted;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class Main extends JavaPlugin {
    int taskId;
    CommandHandler commandHandler = new CommandHandler(this);

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    @Override
    public void onEnable() {

        PluginManager pluginManager = getServer().getPluginManager();
        DeathListener deathListener = new DeathListener();
        pluginManager.registerEvents(deathListener, this);
        getLogger().info("onEnable invoked");
        ChestFill chestFiller = new ChestFill();
        Plugin plugin = this;
        BukkitScheduler chestSpawnTimerTask = Bukkit.getScheduler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("yeet");
            }
        };





        int playerCheckTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (utils.playersInArea() != 0) {
                    System.out.println("Players in area: " + utils.playersInArea());
                    taskId = chestSpawnTimerTask.scheduleSyncRepeatingTask(plugin, runnable, 20, 20);


                } else {
                    if (taskId != 0)
                     chestSpawnTimerTask.cancelTask(taskId);
                }
            }
        }, 20, 20);
    }




        /*(.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                System.out.println("tyest");
            }
        }, 200, 200);*/




    //double time = (1 / (long) Math.sqrt(utils.playersInArea()) * 50L)
    //if (utils.playersInArea() != 0) {

    //BukkitScheduler scheduler = getServer().getScheduler();
//        if (utils.playersInArea() == 0) {
//            new BukkitRunnable() {
//                @Override
//                public void run() {
//                    if (utils.playersInArea() != 0) {
//                        System.out.println("Players in area: " + utils.playersInArea());
//                    } else {
//                        System.out.println("No players found.");
//                    }
//                }
//            }.runTaskTimer(this, 1000L, 200L); //1 sec == 20 ticks
//        } else new BukkitRunnable() {
//                @Override
//                public void run() {
//                    if (utils.playersInArea() != 0) {
//                        System.out.println("Players in area: " + utils.playersInArea());
//                    } else {
//                        System.out.println("No players found.");
//
//                    }
//                }
//            }.runTaskTimer(this, 1000L,(1 / (long) Math.sqrt(utils.playersInArea()) * 100L)); //1 sec == 20 ticks
//        }


    //
    //,1000L, (utils.playersInArea() == 0 ? 100L : (50L * (1 / (long) Math.sqrt(utils.playersInArea()))))); //1 sec == 20 ticks
    //else System.out.println("No Players in area");
    //}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return commandHandler.onCommand(sender, command, label, args);
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable invoked");
    }
}
