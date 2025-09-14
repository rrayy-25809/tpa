package com.rrayy.tpa;

import org.bukkit.plugin.java.JavaPlugin;
import com.rrayy.tpa.command;
import com.rrayy.tpa.tpa_list;

import net.md_5.bungee.api.ChatColor;

public class tpa extends JavaPlugin {
    public command command = new command(this);
    public final String PREFIX = ChatColor.DARK_AQUA+"[TPA] ";

    @Override
    public void onEnable() { // Plugin startup logic
        this.getCommand("tpa").setExecutor(command);
        getLogger().info("TPA Plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("TPA Plugin has been disabled!");
    }
}
