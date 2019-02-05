package baglisted;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class Main extends JavaPlugin {
    CommandHandler commandHandler = new CommandHandler(this);

    @Override
    public void onEnable() {
        getLogger().info("onEnable invoked");
        ChestFill chestFiller = new ChestFill();
        new BukkitRunnable() {

            @Override
            public void run() {
                chestFiller.fillChests();
            }
        }.runTaskTimer(this, 0L, 120L); //1 sec == 20 ticks
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
