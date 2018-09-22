package com.civservers.plugins.restrictedAura;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;



public class Utilities {	
	private RestrictedAura plugin;
	
	public Utilities(RestrictedAura plugin) {
		this.plugin = plugin;
	}
	
    public boolean isValidNumber(String value) {
        // Loop over characters and check if digits
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public String getOnlineUUID(String username) {
    	String out = "not found";
    	Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
		for (Player opl : onlinePlayers) {
			if (opl.getName().equalsIgnoreCase(username)) {
				out = opl.getUniqueId().toString();
			}
		}
    	
    	return out;
    	
    }
    
    public boolean configListAdd(String path, String value) {
    	List<String> confList = new ArrayList<String>();
    	confList = plugin.config.getStringList(path);
    	if (!confList.contains(value)) {
    		confList.add(value);
    		plugin.config.set(path, confList);
    		plugin.saveConfig();
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean configListRemove(String path, String value) {
    	List<String> confList = new ArrayList<String>();
    	confList = plugin.config.getStringList(path);
    	if (confList.contains(value)) {
    		confList.remove(value);
    		plugin.config.set(path, confList);
    		plugin.saveConfig();
    		return true;
    	} else {
    		return false;
    	}
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