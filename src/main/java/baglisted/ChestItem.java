package baglisted;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class ChestItem {
    int chance;
    ItemStack itemStack;

    public ChestItem(ItemStack itemStack, int chance) {
        this.chance = chance;
        this.itemStack = itemStack;
    }

    //TODO Implement randomised chest content
    public ChestItem() {

    }

    public ArrayList<ChestItem> getItemList() {
        ArrayList<ChestItem> itemStacks = new ArrayList<>();

        ItemStack beefStack = new ItemStack(Material.COOKED_BEEF, 2);

        ItemStack harmPotionStack = new ItemStack(Material.POTION, 1);
        PotionMeta harmPotionMeta = (PotionMeta) harmPotionStack.getItemMeta();
        harmPotionMeta.setMainEffect(PotionEffectType.INCREASE_DAMAGE);
        harmPotionStack.setItemMeta(harmPotionMeta);

        ItemStack appleStack = new ItemStack(Material.APPLE, 8);

        ItemStack woodSwordStack = new ItemStack(Material.WOOD_SWORD, 1);

        ItemStack bowStack = new ItemStack(Material.BOW, 1);
        bowStack.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        bowStack.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
        ItemMeta bowMeta = bowStack.getItemMeta();
        bowMeta.setDisplayName("schieter3000");
        bowStack.setDurability((short) 385);
        bowStack.setItemMeta(bowMeta);

        ItemStack hoeStack = new ItemStack(Material.STONE_HOE, 1);
        ItemMeta hoeMeta = hoeStack.getItemMeta();
        hoeStack.setDurability((short) 132);
        hoeMeta.addEnchant(Enchantment.KNOCKBACK, 50, true);
        hoeMeta.setDisplayName("xD JE MOEDER XDDD");
        hoeStack.setItemMeta(hoeMeta);


        ItemStack stewStack = new ItemStack(Material.MUSHROOM_SOUP, 1);

        ItemStack carrotStack = new ItemStack(Material.CARROT, 12);

        ItemStack breadStack = new ItemStack(Material.BREAD, 2);

        ItemStack snowBallStack = new ItemStack(Material.SNOW_BALL, 5);
        ItemMeta snowBallMeta = snowBallStack.getItemMeta();
        snowBallMeta.setDisplayName("Weggooi sneeuw");
        snowBallStack.setItemMeta(snowBallMeta);

        ItemStack eggStack = new ItemStack(Material.EGG, 3);
        ItemMeta eggMeta = eggStack.getItemMeta();
        eggMeta.setDisplayName("Gekookt eitje");
        eggMeta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
        eggStack.setItemMeta(eggMeta);

        ItemStack stickStack = new ItemStack(Material.STICK, 1);
        ItemMeta stickMeta = stickStack.getItemMeta();
        stickMeta.setDisplayName("Oog prikker");
        stickMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
        stickStack.setItemMeta(stickMeta);


        itemStacks.add(new ChestItem(beefStack, 5));
        itemStacks.add(new ChestItem(harmPotionStack, 2));
        itemStacks.add(new ChestItem(appleStack, 7));
        itemStacks.add(new ChestItem(woodSwordStack, 2));
        itemStacks.add(new ChestItem(bowStack, 2));
        itemStacks.add(new ChestItem(hoeStack, 1));
        itemStacks.add(new ChestItem(stewStack, 4));
        itemStacks.add(new ChestItem(carrotStack, 4));
        itemStacks.add(new ChestItem(breadStack, 4));
        itemStacks.add(new ChestItem(snowBallStack, 3));
        itemStacks.add(new ChestItem(eggStack, 2));
        itemStacks.add(new ChestItem(stickStack, 1));

        return itemStacks;
    }
}
