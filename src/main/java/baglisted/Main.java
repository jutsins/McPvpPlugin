package baglisted;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    CommandHandler commandHandler = new CommandHandler(this);

    @Override
    public void onEnable() {

        getLogger().info("onEnable invoked");

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
