package com.sky.foxlifstealcore.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HeartUtils {

    public static ItemStack createHeartItem() {
        ItemStack heart = new ItemStack(Material.RED_DYE); // Change to HEART_OF_THE_SEA if preferred
        ItemMeta meta = heart.getItemMeta();

        meta.setDisplayName(ChatColor.RED + "Heart");
        meta.setLore(java.util.Collections.singletonList(ChatColor.GRAY + "Right-click to gain 1 heart."));
        heart.setItemMeta(meta);

        return heart;
    }

    public static boolean isHeartItem(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        return meta.hasDisplayName() && meta.getDisplayName().equals(ChatColor.RED + "Heart");
    }
}
