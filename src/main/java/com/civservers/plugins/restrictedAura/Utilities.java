package com.civservers.plugins.restrictedAura;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;



public class Utilities {	
	private RestrictedAreaAura plugin;
	
	public Utilities(RestrictedAreaAura plugin) {
		this.plugin = plugin;
	}
	
    public static boolean isValidNumber(String value) {
        // Loop over characters and check if digits
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public void sendPlayer(Player player, String msg) {
    	player.sendMessage(ChatColor.YELLOW + plugin.msgs.get("prefix").toString() + " " + msg);
    }
    
    public void sendConsole(String msg) {
    	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + plugin.msgs.get("prefix").toString() + " " + msg);
    }
    
    public void debug(String dString) {
    	if (plugin.config.getBoolean("debug")) {
    		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + plugin.msgs.get("prefix").toString() + " [DEBUG] " + dString);
    	}
    }
}