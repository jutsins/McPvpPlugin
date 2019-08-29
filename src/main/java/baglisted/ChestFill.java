package baglisted;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class ChestFill {
    Area pvpArea1 = new Area(-50, 21, -7, 9, 49, 50);
    World world;
    ItemStack itemStack = new ItemStack(Material.BOW);
    ChestItem chestItem = new ChestItem();


    public void createChests() {
        world = Bukkit.getServer().getWorlds().get(0);
        Position p = pvpArea1.findSpawns(pvpArea1.getY1(), pvpArea1.getY2() + 1, pvpArea1);
        Location l = new Location(world, p.getX(), p.getY() + 1, p.getZ());
//        Block b = l.getBlock();
        String content = l.getX() + "," + l.getY() + "," + l.getZ();
        int readLine;
        try (FileWriter writer = new FileWriter("SpawnChests.txt", true);
             FileReader reader = new FileReader("SpawnChests.txt");
             BufferedReader buffReader = new BufferedReader(reader)) {
            BufferedWriter buffWriter = new BufferedWriter(writer);
            buffWriter.write(content);
            buffWriter.newLine();
            buffWriter.close();
            String spawnedChests;
            ArrayList<Position> listOfChests = new ArrayList<Position>();
            String deletedChests;
            String currentLine;
            while ((spawnedChests = buffReader.readLine()) != null) {
                String coordsChests = spawnedChests;
                String[] singleChestFullCoords = coordsChests.split("\n");
                String stringChests = Arrays.toString(singleChestFullCoords);
                stringChests = stringChests.replace("[", "");
                stringChests = stringChests.replace("]", "");
                String[] o = stringChests.split(",");
                double dblChestX = Double.parseDouble(o[0]);
                int intChestX = (int) dblChestX;
                double dblChestY = Double.parseDouble(o[1]);
                int intChestY = (int) dblChestY;
                double dblChestZ = Double.parseDouble(o[2]);
                int intChestZ = (int) dblChestZ;
                Position posChests = new Position(intChestX, intChestY, intChestZ);


//                int i;
//                for (i = 0; i < o.length; i++) {
//                    System.out.println(o[i]);
            }


        System.out.println(content);
    } catch (IOException e) {
            System.err.format("Nice error", e);
        }


        {
//        b.setType(Material.CHEST);
//        Chest chest = (Chest) b.getState();
//        Inventory inv = chest.getBlockInventory();
//        for (ChestItem item:chestItem.getItemList()) {
//            inv.addItem(item.itemStack);
//        }

//    public void removeChests() {
//        world = Bukkit.getServer().getWorlds().get(0);
//
//        Block b = l.getBlock();
//        System.out.println("Removed chest at " + l.getX() + "X, " + l.getY() + "Y, " + l.getZ() + "Z");
//        b.setType(Material.CHEST);
//        Chest chest = (Chest) b.getState();
//        Inventory inv = chest.getBlockInventory();
//        for (ChestItem item:chestItem.getItemList()) {
//            inv.addItem(item.itemStack);
//        }

//YET TO IMPLEMENT AN ARRAYLIST WITH POSSIBLE CHEST ITEMS + CHANCE THAT THEY'LL BE IN A CHEST.


//        DateTimeFormatter customFormatDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH mm");
//        LocalDateTime currentDate = LocalDateTime.now();
//        String dateHoursMinutes = customFormatDate.format(currentDate);
//        File inputFile = new File("C:\\Users\\Daan\\Desktop\\Overig\\bukkit-1.8.9\\server\\chests\\Chest spawning started on " + dateHoursMinutes);
        }
    }
}