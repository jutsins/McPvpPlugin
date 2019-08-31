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
    ItemStack itemStack = new ItemStack(Material.BOW);
    ChestItem chestItem = new ChestItem();


    public void createChests() {
        String xmlChestFilePath = "SpawnChest.xml";
        world = Bukkit.getServer().getWorlds().get(0);
        int maxAmountChests = 3;
        String test = "test" + "1";
        String empty = "";
        int po;
        int lp;
        System.out.println("Max Chests Count: " + (maxAmountChests));
        ArrayList testyB = new ArrayList<>();
        File file = new File(xmlChestFilePath);
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
//            Attr chestIdGrabber = document.createAttribute("id");
//            String stringK = String.valueOf(1);
//            chestIdGrabber.setValue(stringK);
//            chest.setAttributeNode(chestIdGrabber);


//            Element chest2 = document.createElement("chest");
//            chestlist.appendChild(chest2);
//            Attr chestIdGrabber2 = document.createAttribute("id");
//            String stringK2 = String.valueOf(0);
//            chestIdGrabber2.setValue(stringK2);
//            chest2.setAttributeNode(chestIdGrabber2);
//
//            Element coords2 = document.createElement("coords");
//            chest2.appendChild(coords2);
//
//            Element x2 = document.createElement("x");
//            coords2.appendChild(x2);
//            x2.appendChild(document.createTextNode("-5.0"));
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(xmlChestFilePath);

                transformer.transform(domSource, streamResult);


                NodeList chestAmount = document.getElementsByTagName("chest");
                int intChestAmount = chestAmount.getLength();
//
//            String attrValue = ((Element)chestAmount).getAttribute("id");
//            System.out.println("RawrValue: " + attrValue);
                System.out.println("This is the amount of chests on the map: " + intChestAmount);
                if (intChestAmount < maxAmountChests) {

                    Element chest = document.createElement("chest");
                    document.getElementsByTagName("list").item(0).appendChild(chest);

//                    Attr chestIdGrabber = document.createAttribute("id");
//                    String stringK = String.valueOf(intChestAmount);
//                    chestIdGrabber.setValue(stringK);
//                    chest.setAttributeNode(chestIdGrabber);


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

                    System.out.println("Ik heb een chest gemaakt");


//                for (int s = 0; s < chestAmount.getLength(); s++) {
//                    Node fstNode = chestAmount.item(s);
//                    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
//                        Element fstElmnt = (Element) fstNode;
//                        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("x");
//                        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
//                        NodeList fstNm = fstNmElmnt.getChildNodes();
//                        Element timeStamp = (Element) fstNmElmntLst.item(0);
//                        testyB.add(timeStamp);
//                        System.out.println("x : " + (fstNm.item(0)).getNodeValue());
////                    Element testyC = (Element)  document.getElementsByTagName("coords").item(0);
////                    testyB.add(testyC);
////                    System.out.println(testyC);
//                    }
//                }
//            System.out.println("Max amount of chests spawned");


                    Document doc1 = documentBuilder.parse(new File(xmlChestFilePath));
//            System.out.println(doc1.getElementsByTagName("coords").item(0).getChildNodes().item(0).getTextContent());
//            System.out.println(doc1.getElementsByTagName("coords").item(0).getChildNodes().item(1).getTextContent());
//            System.out.println(doc1.getElementsByTagName("coords").item(0).getChildNodes().item(2).getTextContent());
//            String readStringX = doc1.getElementsByTagName("coords").item(0).getChildNodes().item(0).getTextContent();
                    DOMSource domSource1 = new DOMSource(document);
//            System.out.println(testyB);
//            Element testyB = document.createElement("chest");
//            chestlist.appendChild(chest);
                    StreamResult streamResult1 = new StreamResult(new File(xmlChestFilePath));
                    transformer.transform(domSource1, streamResult1);


                    System.out.println("Done editing XML File");
                } else {
                    System.out.println("Max amount of chests.");
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


//        world = Bukkit.getServer().getWorlds().get(0);
//        Position p = pvpArea1.findSpawns(pvpArea1.getY1(), pvpArea1.getY2() + 1, pvpArea1);
//        Location l = new Location(world, p.getX(), p.getY() + 1, p.getZ());
////        Block b = l.getBlock();
//        String content = l.getX() + "," + l.getY() + "," + l.getZ();
//        int readLine;
//        try (FileWriter writer = new FileWriter("SpawnChests.txt", true);
//             FileReader reader = new FileReader("SpawnChests.txt");
//             BufferedReader buffReader = new BufferedReader(reader)) {
//            BufferedWriter buffWriter = new BufferedWriter(writer);
//            buffWriter.write(content);
//            buffWriter.newLine();
//            buffWriter.close();
//            String spawnedChests;
//            ArrayList<Position> listOfChests;
//            String deletedChests;
//            String currentLine;
//            while ((spawnedChests = buffReader.readLine()) != null) {
//
//                String coordsChests = spawnedChests;
//                String[] singleChestFullCoords = coordsChests.split("\n");
//                String stringChests = Arrays.toString(singleChestFullCoords);
//
//                stringChests = stringChests.replace("[", "");
//                stringChests = stringChests.replace("]", "");
//
//                String[] o = stringChests.split(",");
//
//                double dblChestX = Double.parseDouble(o[0]);
//                int intChestX = (int) dblChestX;
//
//                double dblChestY = Double.parseDouble(o[1]);
//                int intChestY = (int) dblChestY;
//
//                double dblChestZ = Double.parseDouble(o[2]);
//                int intChestZ = (int) dblChestZ;
//
//                Position posChests = new Position(intChestX, intChestY, intChestZ);
//
////                int i;
////                for (i = 0; i < o.length; i++) {
////                    System.out.println(o[i]);
//            }
//
//
//
//        System.out.println(content);
//    } catch (IOException e) {
//            System.err.format("Nice error", e);
//        }
//
//
//        {
////        b.setType(Material.CHEST);
////        Chest chest = (Chest) b.getState();
////        Inventory inv = chest.getBlockInventory();
////        for (ChestItem item:chestItem.getItemList()) {
////            inv.addItem(item.itemStack);
////        }
//
////    public void removeChests() {
////        world = Bukkit.getServer().getWorlds().get(0);
////
////        Block b = l.getBlock();
////        System.out.println("Removed chest at " + l.getX() + "X, " + l.getY() + "Y, " + l.getZ() + "Z");
////        b.setType(Material.CHEST);
////        Chest chest = (Chest) b.getState();
////        Inventory inv = chest.getBlockInventory();
////        for (ChestItem item:chestItem.getItemList()) {
////            inv.addItem(item.itemStack);
////        }
//
////YET TO IMPLEMENT AN ARRAYLIST WITH POSSIBLE CHEST ITEMS + CHANCE THAT THEY'LL BE IN A CHEST.
//
//
////        DateTimeFormatter customFormatDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH mm");
////        LocalDateTime currentDate = LocalDateTime.now();
////        String dateHoursMinutes = customFormatDate.format(currentDate);
////        File inputFile = new File("C:\\Users\\Daan\\Desktop\\Overig\\bukkit-1.8.9\\server\\chests\\Chest spawning started on " + dateHoursMinutes);
//        }
//    }
//}
//        Position p = pvpArea1.findSpawns(pvpArea1.getY1(), pvpArea1.getY2() + 1, pvpArea1);
//        Location l = new Location(world, p.getX(), p.getY() + 1, p.getZ());
//        String stringX = String.valueOf(l.getX());
//        String stringY = String.valueOf(l.getY());
//        String stringZ = String.valueOf(l.getZ());