package com.rrayy.tpa;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class tpa extends JavaPlugin {
    public command command = new command(this);
    public tpa_list list = new tpa_list();
    public tap_completer completer = new tap_completer(this);
    public final String PREFIX = ChatColor.DARK_AQUA+"§l[TPA] ";

    @Override
    public void onEnable() { // Plugin startup logic
        PluginCommand tpa = this.getCommand("tpa");
        tpa.setExecutor(command);
        tpa.setTabCompleter(completer);
        getLogger().info("TPA Plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("TPA Plugin has been disabled!");
    }
}
