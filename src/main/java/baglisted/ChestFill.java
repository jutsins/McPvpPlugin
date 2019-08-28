package baglisted;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class ChestFill {
    Area pvpArea1 = new Area(-50, 21, -7, 9, 49, 50);
    World world;
    ItemStack itemStack = new ItemStack(Material.BOW);
    ChestItem chestItem = new ChestItem();


    public String fillChests() {

        world = Bukkit.getServer().getWorlds().get(0);
        Position p = pvpArea1.findSpawns(pvpArea1.getY1(), pvpArea1.getY2() + 1, pvpArea1);
        itemStack.setDurability((short) 384);
        itemStack.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        Location l = new Location(world, p.getX(), p.getY() + 1, p.getZ());
        Block b = l.getBlock();
        b.setType(Material.CHEST);
        Chest chest = (Chest) b.getState();
        Inventory inv = chest.getBlockInventory();
        for (ChestItem item : chestItem.getItemList()) {
            inv.addItem(item.itemStack);
        }
        System.out.println(l.getX() + " " + l.getY() + "  " + l.getZ());

        return null;
    }
    //TODO IMPLEMENT AN ARRAYLIST WITH POSSIBLE CHEST ITEMS + CHANCE THAT THEY'LL BE IN A CHEST.
}

