package baglisted;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ChestFill {
    Area pvpArea1 = new Area(-50, 21, -7, 9, 49, 50);
    World world;

    public void Chests(int maxAmountChests, int decayingTimeInSeconds) {
        String xmlChestFilePath = "SpawnChest.xml";
        File file = new File(xmlChestFilePath);
        world = Bukkit.getServer().getWorlds().get(0);
        System.out.println("Max Chests Count: " + (maxAmountChests));
        ChestDecay decay = new ChestDecay();

        if (file.exists()) {
            try {
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(file);


                Position p = pvpArea1.findSpawns(pvpArea1.getY1(), pvpArea1.getY2() + 1, pvpArea1);
                Location l = new Location(world, p.getX(), p.getY() + 1, p.getZ());
                String stringX = String.valueOf(l.getX());
                String stringY = String.valueOf(l.getY());
                String stringZ = String.valueOf(l.getZ());

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(xmlChestFilePath);

                transformer.transform(domSource, streamResult);


                NodeList chestAmount = document.getElementsByTagName("chest");
                int intChestAmount = chestAmount.getLength();

                System.out.println("This is the amount of chests on the map: " + intChestAmount);
                if (intChestAmount < maxAmountChests) {

                    Element chest = document.createElement("chest");
                    document.getElementsByTagName("list").item(0).appendChild(chest);

                    Element coords = document.createElement("coords");
                    chest.appendChild(coords);

                    Element x = document.createElement("x");
                    coords.appendChild(x);
                    x.appendChild(document.createTextNode(stringX));

                    Element y = document.createElement("y");
                    coords.appendChild(y);
                    y.appendChild(document.createTextNode(stringY));

                    Element z = document.createElement("z");
                    coords.appendChild(z);
                    z.appendChild(document.createTextNode(stringZ));

                    Document doc1 = documentBuilder.parse(new File(xmlChestFilePath));
                    DOMSource domSource1 = new DOMSource(document);

                    StreamResult streamResult1 = new StreamResult(new File(xmlChestFilePath));
                    transformer.transform(domSource1, streamResult1);
                    int chestDelete = document.getElementsByTagName("chest").getLength();
                    if (chestDelete != 0) {
                        decay.timerChests(decayingTimeInSeconds);
                        System.out.println(ConsoleColors.YELLOW + "A new chest has spawned!\nDecaying in " + decayingTimeInSeconds + " seconds..." + ConsoleColors.RESET);
                    } else {
                        System.out.println(ConsoleColors.RED + "No chests to delete - this is an error. Please check the code." + ConsoleColors.RESET);
                    }
                } else {
                    System.out.println("Max amount of chests. Waiting for chests to decay.");
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            try {
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();
                Element chestlist = document.createElement("list");
                document.appendChild(chestlist);
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(file);
                transformer.transform(domSource, streamResult);
                System.out.println("Done creating XML File");
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

}
