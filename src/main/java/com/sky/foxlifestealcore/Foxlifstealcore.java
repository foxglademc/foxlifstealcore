package com.sky.foxlifestealcore;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public final class Foxlifstealcore extends JavaPlugin implements Listener {

    private final double HEARTS_GAINED = 2.0; // 1 heart = 2 health points
    private final double HEARTS_LOST = 2.0;
    private final double MAX_HEALTH_CAP = 40.0; // 20 hearts
    private final double MIN_HEALTH_CAP = 2.0;  // 1 heart

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getCommand("lifestealHealth").setExecutor(new com.sky.foxlifstealcore.command.LifeStealCommand.LifeStealCommand());

        // ASCII heart
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "      ******       ******      ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "    **********   **********    ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "  **************************** ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + " ******************************");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "*******************************");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "*******************************");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "*******************************");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "************* " + ChatColor.RED + "FL" + ChatColor.LIGHT_PURPLE + " *************");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + " ******************************");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "   **************************  ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "     **********************    ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "       ******************      ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "         **************        ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "           **********          ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "             ******            ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "               **              ");

        getLogger().info("FoxLifeStealCore has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("FoxLifeStealCore has been disabled.");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        // Remove health from victim
        AttributeInstance victimHealth = victim.getAttribute(Attribute.MAX_HEALTH);
        if (victimHealth != null) {
            double currentHealth = victimHealth.getBaseValue();
            double newHealth = Math.max(currentHealth - HEARTS_LOST, MIN_HEALTH_CAP);
            victimHealth.setBaseValue(newHealth);
            victim.sendMessage("§cYou lost §e" + (HEARTS_LOST / 2) + " §cheart(s)! Max Health: §e" + (newHealth / 2));
        }

        // Add health to killer
        if (killer != null && !killer.equals(victim)) {
            AttributeInstance killerHealth = killer.getAttribute(Attribute.MAX_HEALTH);
            if (killerHealth != null) {
                double currentHealth = killerHealth.getBaseValue();
                double newHealth = Math.min(currentHealth + HEARTS_GAINED, MAX_HEALTH_CAP);
                killerHealth.setBaseValue(newHealth);
                killer.sendMessage("§aYou gained §c" + (HEARTS_GAINED / 2) + " §aheart(s)! Max Health: §e" + (newHealth / 2));
            }
        }
    }
}
