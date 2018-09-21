package com.civservers.plugins.restrictedAreaAura;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Utilities {
	
	private RestrictedAreaAura pl;
	
    public void debug(String dString) {
    	if (pl.config.getBoolean("debug")) {
    		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "["+pl.pluginName+" DEBUG]" + " " + dString);
    	}
    }
}