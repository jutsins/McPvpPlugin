package baglisted;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class ChestDecay {
    Timer timer;

    public void timerChests(int seconds) {
        timer = new Timer();
        timer.schedule(new timerJ(), seconds * 1000);
    }

    public class timerJ extends TimerTask {
        String xmlChestFilePath = "SpawnChest.xml";
        File file = new File(xmlChestFilePath);
        World world;

        @Override
        public void run() {
            try {
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(file);

                Element chestDelete = (Element) document.getElementsByTagName("chest").item(0);
                int chestToDelete = document.getElementsByTagName("chest").getLength();
                if (chestToDelete == 0) {
                    timer.cancel();
                    System.out.println("No chests to decay.");
                } else {

                    world = Bukkit.getServer().getWorlds().get(0);

                    String deleteChestX = document.getElementsByTagName("x").item(0).getTextContent();
                    String deleteChestY = document.getElementsByTagName("y").item(0).getTextContent();
                    String deleteChestZ = document.getElementsByTagName("z").item(0).getTextContent();
                    int intDeleteChestX = (int) Double.parseDouble(deleteChestX);
                    int intDeleteChestY = (int) Double.parseDouble(deleteChestY);
                    int intDeleteChestZ = (int) Double.parseDouble(deleteChestZ);
                    System.out.println("Coordinates of chest that is about to decay: " + intDeleteChestX + " X, " + intDeleteChestY + " Y, " + intDeleteChestZ + " Z");



                    Location locDecayedChest = new Location(world, intDeleteChestX, intDeleteChestY, intDeleteChestZ);
                    Block bDecayedChest = locDecayedChest.getBlock();
                    if (bDecayedChest.getType() == Material.EMERALD_BLOCK) {
                        bDecayedChest.setType(Material.AIR);
//                        System.out.println("Replaced chest with Air");
                    } else {
                        System.out.println("This wasn't a chest!");
                    }



                    chestDelete.getParentNode().removeChild(chestDelete);
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();

                    DOMSource domSource1 = new DOMSource(document);
                    StreamResult streamResult1 = new StreamResult(new File(xmlChestFilePath));
                    transformer.transform(domSource1, streamResult1);
                    System.out.println("A chest has decayed.");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            timer.cancel();
        }
    }
}
