package com.sky.foxlifstealcore.command;

import com.sky.foxlifstealcore.util.HeartUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RemoveHeartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;

        if (!player.hasPermission("foxlifesteal.removeheart")) {
            player.sendMessage("§cYou don't have permission.");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("§cUsage: /removeheart <amount>");
            return true;
        }

        try {
            int amount = Integer.parseInt(args[0]);
            double loss = amount * 2.0;

            double current = player.getAttribute(Attribute.MAX_HEALTH).getBaseValue();
            if (current - loss < 2.0) {
                player.sendMessage("§cYou can't reduce your health below 1 heart.");
                return true;
            }

            player.getAttribute(Attribute.MAX_HEALTH).setBaseValue(current - loss);

            for (int i = 0; i < amount; i++) {
                player.getInventory().addItem(HeartUtils.createHeartItem());
            }

            player.sendMessage("§eYou removed §c" + amount + "§e heart(s) and received item(s).");

        } catch (NumberFormatException e) {
            player.sendMessage("§cInvalid number.");
        }

        return true;
    }
}
