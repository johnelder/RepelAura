package com.civservers.plugins.restrictedAreaAura;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utilities {
	
	private RestrictedAreaAura pl;
	
	
    public boolean isValidNumber(String value) {
        // Loop over characters and check if digits
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public void sendPlayer(Player player, String msg) {
    	player.sendMessage(pl.msgs.get("prefix").toString() + " " + msg);
    }
    
    public void sendConsole(String msg) {
    	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + pl.msgs.get("prefix").toString() + " " + msg);
    }
    
    public void debug(String dString) {
    	if (pl.config.getBoolean("debug")) {
    		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + pl.msgs.get("prefix").toString() + " " + dString);
    	}
    }
}