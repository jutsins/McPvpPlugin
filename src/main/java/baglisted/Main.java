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
    int playerForChestCount = utils.playersInArea();

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
                    if (playerForChestCount != utils.playersInArea()) {
                        chestSpawnTimerTask.cancelTask(taskId);
                        playerForChestCount = utils.playersInArea();
                        System.out.println("Players in area: " + utils.playersInArea());
                        taskId = chestSpawnTimerTask.scheduleSyncRepeatingTask(plugin, runnable, 20, 20);
                        System.out.println(taskId + " er zat  wel verschil");

                    } else if (taskId != 0 && playerForChestCount == utils.playersInArea()) {
                        chestSpawnTimerTask.cancelTask(taskId);
                        taskId = chestSpawnTimerTask.scheduleSyncRepeatingTask(plugin, runnable, 20, 20);
                        System.out.println("Players in area: " + utils.playersInArea());
                        System.out.println(taskId + " er zat geen verschil");
                    }

                } else {
                    chestSpawnTimerTask.cancelTask(taskId);
                    System.out.println(taskId + " cancelled ");
                    playerForChestCount = 0;
                    taskId = 0;
                }
            }
        }, 20, 20);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return commandHandler.onCommand(sender, command, label, args);
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable invoked");
    }
}
