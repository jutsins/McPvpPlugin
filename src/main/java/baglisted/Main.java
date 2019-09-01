package baglisted;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class Main extends JavaPlugin {

    int taskId;
    CommandHandler commandHandler = new CommandHandler(this);

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    int playerForChestCount = utils.playersInArea();
    ChestDecay decay = new ChestDecay();
    ClearingAllChests clearChests = new ClearingAllChests();
    int decayingTimeInSeconds = 60;
    int maxAmountChests = 5;
    ChestFill chestFiller = new ChestFill();
    @Override
    public void onEnable() {

        PluginManager pluginManager = getServer().getPluginManager();
        DeathListener deathListener = new DeathListener();
        pluginManager.registerEvents(deathListener, this);
        getLogger().info("Baglisted has successfully started.");
        Plugin plugin = this;
        BukkitScheduler chestSpawnTimerTask = Bukkit.getScheduler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                chestFiller.Chests(maxAmountChests, decayingTimeInSeconds);
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
                        taskId = chestSpawnTimerTask.scheduleSyncRepeatingTask(plugin, runnable, 20, 100);
                        System.out.println("There's a difference in player count on the field.");

                    } else if (taskId != 0 && playerForChestCount == utils.playersInArea()) {
                        System.out.println("Players in area: " + utils.playersInArea());
                        System.out.println("There was no difference in player count on the field.");
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

        String xmlChestFilePath = "SpawnChest.xml";
        File file = new File(xmlChestFilePath);
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            int chestToDelete = document.getElementsByTagName("chest").getLength();
            System.out.println("Chests to delete: " + chestToDelete);
            System.out.println("Server shutting down. Clearing chests...");
           clearChests.timerChests(1);
            System.out.println("Succeeded.");


        } catch (Exception e) {
            System.out.println(e);
        }
        getLogger().info("Baglisted has successfully shut down.");
    }
}
