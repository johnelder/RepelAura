package com.civservers.plugins.restrictedAura;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Listeners implements Listener {
	
	private RestrictedAura pl;
	
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event){    	
    	// Check if damage is pvp
    	if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
    		Player victim = (Player) event.getEntity();
    		Player culprit = (Player) event.getDamager();

    		//pl.debug(culprit.getDisplayName().toString() + " hit " + victim.getDisplayName().toString());
    		
    		//Cancel damage
    		if ((boolean) pl.config.get("cancelPVPDamage")) {
    			event.setCancelled(true);
    		}
    	}
    }
	
}