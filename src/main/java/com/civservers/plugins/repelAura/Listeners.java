package com.civservers.plugins.repelAura;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {
	
	private RepelAura plugin;
	
	public Listeners(RepelAura plugin) {
		this.plugin = plugin;
	}
	
	Utilities Util = new Utilities(plugin);
	
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
    	Player quitter = event.getPlayer();
    	String q_uuid = quitter.getUniqueId().toString();
    	if (plugin.config.contains("auras." + q_uuid)) {
    		plugin.config.set("auras." + q_uuid, null);
    		plugin.saveConfig();
    	}
    }	
}