package com.sky.foxlifstealcore.command.LifeStealCommand;

import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LifeStealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("Foxsteal.here")) {

                if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
                    try {
                        double value = Double.parseDouble(args[1]);
                        player.getAttribute(Attribute.MAX_HEALTH).setBaseValue(value);
                        player.sendMessage("§aMax health set to §e" + (value / 2) + " hearts.");
                    } catch (NumberFormatException e) {
                        player.sendMessage("§cInvalid number.");
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
