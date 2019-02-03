package baglisted;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("onEnable invoked");
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable invoked");
    }


}
