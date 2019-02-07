package baglisted;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;

public class ChestItem {
    int chance;
    ItemStack itemStack;

    public ChestItem(ItemStack itemStack, int chance) {
        this.chance = chance;
        this.itemStack = itemStack;
    }

    public ChestItem(){

    }

    public ArrayList<ChestItem> getItemList(){
        ArrayList<ChestItem> itemStacks = new ArrayList<>();

        ItemStack beefStack = new ItemStack(Material.COOKED_BEEF, 2);

       /* ItemStack harmPotionStack = new ItemStack(Material.SPLASH_POTION, 1);
        PotionMeta harmPotionMeta = (PotionMeta) harmPotionStack.getItemMeta();
        harmPotionMeta.setBasePotionData(new PotionData(PotionType.INSTANT_DAMAGE));*/

        ItemStack appleStack = new ItemStack(Material.APPLE, 8);

        //ItemStack woodSwordStack = new ItemStack(Material.WOODEN_SWORD, 1);

        ItemStack bowStack = new ItemStack(Material.BOW, 1);
        bowStack.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        bowStack.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
        ItemMeta bowMeta = bowStack.getItemMeta();
        bowMeta.setDisplayName("schieter3000");
        bowStack.setDurability((short) 385);

        ItemStack hoeStack = new ItemStack(Material.STONE_HOE, 1);
        ItemMeta hoeMeta = hoeStack.getItemMeta();
        hoeMeta.setDisplayName("je moeder");


        //ItemStack stewStack = new ItemStack(Material.MUSHROOM_STEW, 1);

        ItemStack carrotStack = new ItemStack(Material.CARROT, 12);

        ItemStack breadStack = new ItemStack(Material.BREAD, 2);

        ItemStack snowBallStack = new ItemStack(Material.SNOWBALL, 5);
        ItemMeta snowBallMeta = snowBallStack.getItemMeta();
        snowBallMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 5, true);
        snowBallMeta.setDisplayName("Weggooi sneeuw");

        ItemStack eggStack = new ItemStack(Material.EGG, 3);
        ItemMeta eggMeta = eggStack.getItemMeta();
        eggMeta.setDisplayName("Gekookt eitje");
        eggMeta.addEnchant(Enchantment.ARROW_FIRE, 1, true);

        ItemStack stickStack = new ItemStack(Material.STICK, 1);
        ItemMeta stickMeta = stickStack.getItemMeta();
        stickMeta.setDisplayName("Oog prikker");
        stickMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);




        itemStacks.add(new ChestItem(beefStack, 5));
       // itemStacks.add(new ChestItem(harmPotionStack, 2));
        itemStacks.add(new ChestItem(appleStack, 7));
       // itemStacks.add(new ChestItem(woodSwordStack, 2));
        itemStacks.add(new ChestItem(bowStack, 2));
        itemStacks.add(new ChestItem(hoeStack, 1));
       // itemStacks.add(new ChestItem(stewStack , 4));
        itemStacks.add(new ChestItem(carrotStack, 4));
        itemStacks.add(new ChestItem(breadStack, 4));
        itemStacks.add(new ChestItem(snowBallStack, 3));
        itemStacks.add(new ChestItem(eggStack, 2));
        itemStacks.add(new ChestItem(stickStack, 1));

        return itemStacks;
    }
}
