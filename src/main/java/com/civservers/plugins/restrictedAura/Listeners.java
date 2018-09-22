package com.civservers.plugins.restrictedAura;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listeners implements Listener {
	
	private RestrictedAura plugin;
	
	public Listeners(RestrictedAura plugin) {
		this.plugin = plugin;
	}
	
	Utilities Util = new Utilities(plugin);
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		plugin.Util.debug("A Player has Joined");
	}
	
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event){    	
    	// Check if damage is pvp
    	plugin.Util.debug("Entity was damaged");
    }
	
}