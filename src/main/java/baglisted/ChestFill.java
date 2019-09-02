package baglisted;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
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
    Area pvpArea1 = new Area(-29, 21, -5, 9, 49, 29);
    World world;
    Area pvpOOR1 = new Area(-48, 29, -5, -27, 29, 3);
    Area pvpOOR2 = new Area(-31, 31, -5, -20, 31, 9);
    Area pvpOOR3 = new Area(0, 31, -5, 9, 26, 2);
    Area pvpOOR4 = new Area(2, 27, 30, 9, 29, 34);

    public void Chests(int maxAmountChests, int decayingTimeInSeconds) {
        String xmlChestFilePath = "SpawnChest.xml";
        File file = new File(xmlChestFilePath);
        world = Bukkit.getServer().getWorlds().get(0);
//        System.out.println("Max Chests Count: " + (maxAmountChests));
        ChestDecay decay = new ChestDecay();
        if (file.exists()) {
            try {
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(file);


                Position p = pvpArea1.findSpawns(pvpArea1.getY1(), pvpArea1.getY2() + 1, pvpArea1);
                Location loc = new Location(world, p.getX(), p.getY() + 1, p.getZ());
                String stringX = String.valueOf(loc.getX());
                String stringY = String.valueOf(loc.getY());
                String stringZ = String.valueOf(loc.getZ());
                if (utils.areaInArea(pvpArea1, pvpOOR3, p)) {

                    System.out.println(ConsoleColors.CYAN + "Same coordinates" + ConsoleColors.RESET);
                    Bukkit.broadcastMessage("These are the coordinates of a chest I made: " + stringX + " X, " + stringY + " Y, " + stringZ + " Z.");

                }
                System.out.println(ConsoleColors.GREEN + "These are the coordinates of a chest I made: " + stringX + " X, " + stringY + " Y, " + stringZ + " Z." + ConsoleColors.RESET);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(xmlChestFilePath);

                transformer.transform(domSource, streamResult);


                NodeList chestAmount = document.getElementsByTagName("chest");
                int intChestAmount = chestAmount.getLength();

                System.out.println("This is the amount of chests on the map: " + intChestAmount);
                if (intChestAmount < maxAmountChests) {
                        Block b = loc.getBlock();

                        b.setType(Material.EMERALD_BLOCK);

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

                    String deleteChestX = document.getElementsByTagName("x").item(0).getTextContent();
                    String deleteChestY = document.getElementsByTagName("y").item(0).getTextContent();
                    String deleteChestZ = document.getElementsByTagName("z").item(0).getTextContent();
                    int intDeleteChestX = (int) Double.parseDouble(deleteChestX);
                    int intDeleteChestY = (int) Double.parseDouble(deleteChestY);
                    int intDeleteChestZ = (int) Double.parseDouble(deleteChestZ);


                    Document doc1 = documentBuilder.parse(new File(xmlChestFilePath));
                    DOMSource domSource1 = new DOMSource(document);

                    StreamResult streamResult1 = new StreamResult(new File(xmlChestFilePath));
                    transformer.transform(domSource1, streamResult1);
                    int chestDelete = document.getElementsByTagName("chest").getLength();
//                    System.out.println("Chests to delete: " + chestDelete);
//                    if (chestDelete != 0) {
                    decay.timerChests(decayingTimeInSeconds);
                    System.out.println(ConsoleColors.YELLOW + "A new chest has spawned!\nDecaying in " + decayingTimeInSeconds + " seconds..." + ConsoleColors.RESET);
//                    } else {
//                        System.out.println(ConsoleColors.RED + "No chests to delete - this is an error. Please check the code." + ConsoleColors.RESET);
//                    }
                } else {
                    System.out.println("Max amount of chests. Waiting for chests to decay.");
                }

            } catch (Exception e) {
                System.out.println(e.getStackTrace()[0].getLineNumber());
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
